using SwimmingCompetition.Domain;
using System.Data;
using System.Data.SqlClient;
using System.Data.SQLite;
using log4net;
using SwimmingCompetition.Validator;

namespace SwimmingCompetition.Repository;

public class AngajatRepository: IAngajatRepository
{
    private IDictionary<string, string> _props;
    private IAbstractValidator<Angajat> _validator;
    private static readonly log4net.ILog log = LogManager.GetLogger(typeof(AngajatRepository));

    public AngajatRepository(IAbstractValidator<Angajat> validator, IDictionary<string,string> props)
    {
        log.Info("Creating Angajat Repository");
        this._props = props;
        this._validator = validator;
    }

    public Angajat? Add(Angajat angajat)
    {
        log.InfoFormat("Entering Add with angajat: {0}",angajat);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var insertCommand = connection.CreateCommand())
        {
            _validator.Validate(angajat);
            
            insertCommand.CommandText = "insert into Angajat(nume, prenume, locatie, parola) values (@nume, @prenume, @locatie, @parola)";
            
            IDbDataParameter parameterNume = insertCommand.CreateParameter();
            parameterNume.ParameterName = "@nume";
            parameterNume.Value = angajat.Nume;
            insertCommand.Parameters.Add(parameterNume);
            IDbDataParameter parameterPrenume = insertCommand.CreateParameter();
            parameterPrenume.ParameterName = "@prenume";
            parameterPrenume.Value = angajat.Prenume;
            insertCommand.Parameters.Add(parameterPrenume);
            IDbDataParameter parameterLocatie = insertCommand.CreateParameter();
            parameterLocatie.ParameterName = "@locatie";
            parameterLocatie.Value = angajat.Locatie;
            insertCommand.Parameters.Add(parameterLocatie);
            IDbDataParameter parameterParola = insertCommand.CreateParameter();
            parameterParola.ParameterName = "@parola";
            parameterParola.Value = angajat.Parola;
            insertCommand.Parameters.Add(parameterParola);
            
            int rows = insertCommand.ExecuteNonQuery();
            log.InfoFormat("Inserting {0} rows", rows);
            if (rows == 0)
                return angajat;
            return null;
        }
    }

    public Angajat? Update(Angajat angajat)
    {
        log.InfoFormat("Entering Update with angajat: {0}",angajat);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var updateCommand = connection.CreateCommand())
        {
            _validator.Validate(angajat);
            
            updateCommand.CommandText = "update Angajat set nume = @nume, prenume= @prenume, locatie= @locatie, parola=@parola where id_angajat= @id_angajat";
            
            IDbDataParameter parameterNume = updateCommand.CreateParameter();
            parameterNume.ParameterName = "@nume";
            parameterNume.Value = angajat.Nume;
            updateCommand.Parameters.Add(parameterNume);
            IDbDataParameter parameterPrenume = updateCommand.CreateParameter();
            parameterPrenume.ParameterName = "@prenume";
            parameterPrenume.Value = angajat.Prenume;
            updateCommand.Parameters.Add(parameterPrenume);
            IDbDataParameter parameterLocatie = updateCommand.CreateParameter();
            parameterLocatie.ParameterName = "@locatie";
            parameterLocatie.Value = angajat.Locatie;
            updateCommand.Parameters.Add(parameterLocatie);
            IDbDataParameter parameterParola = updateCommand.CreateParameter();
            parameterParola.ParameterName = "@parola";
            parameterParola.Value = angajat.Parola;
            updateCommand.Parameters.Add(parameterParola);
            IDbDataParameter parameterIdAngajat = updateCommand.CreateParameter();
            parameterIdAngajat.ParameterName = "@id_angajat";
            parameterIdAngajat.Value = angajat.Id;
            updateCommand.Parameters.Add(parameterIdAngajat);
            
            int rows = updateCommand.ExecuteNonQuery();
            log.InfoFormat("Update {0} rows", rows);
            if (rows == 0)
                return angajat;
            return null;
        }
    }

    public Angajat? DeleteById(int id)
    {
        log.InfoFormat("Entering DeleteById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var deleteCommand = connection.CreateCommand())
        {
            Angajat? angajat = FindById(id);
            
            deleteCommand.CommandText = "delete from Angajat where id_angajat= @id_angajat";
            
            IDbDataParameter parameterIdAngajat = deleteCommand.CreateParameter();
            parameterIdAngajat.ParameterName = "@id_angajat";
            parameterIdAngajat.Value = id;
            deleteCommand.Parameters.Add(parameterIdAngajat);
            
            int rows = deleteCommand.ExecuteNonQuery();
            log.InfoFormat("Delete {0} rows", rows);
            return angajat;
        }
    }

    public Angajat? FindById(int id)
    {
        log.InfoFormat("Entering FindById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select * from Angajat where id_angajat = @id_angajat";
            IDbDataParameter parameterIdAngajat = selectCommand.CreateParameter();
            parameterIdAngajat.ParameterName = "@id_angajat"; 
            parameterIdAngajat.Value = id;
            selectCommand.Parameters.Add(parameterIdAngajat);

            using (var dataReader = selectCommand.ExecuteReader())
            {
                if(dataReader.Read())
                {
                    int idAngajat = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    string locatie = dataReader.GetString(3);
                    string parola = dataReader.GetString(4);

                    Angajat angajat = new Angajat(nume, prenume, locatie, parola);
                    angajat.Id = idAngajat;
                    log.InfoFormat("Find angajat: {0}", angajat);
                    return angajat;
                }
            }
            log.Info("No angajat found");
            return null;
        }
    }

    public List<Angajat> FindAll()
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select * from Angajat";
            List<Angajat> angajati = new List<Angajat>();
            using (var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    int idAngajat = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    string locatie = dataReader.GetString(3);
                    string parola = dataReader.GetString(4);

                    Angajat angajat = new Angajat(nume, prenume, locatie, parola);
                    angajat.Id = idAngajat;

                    angajati.Add(angajat);
                }
            }
            log.InfoFormat("Find {0} angajati", angajati.Count);
            return angajati;
        }
    }

    public Angajat? findByFirstnameLastnamePassword(string firstname, string lastname, string password)
    {
        log.InfoFormat("Entering FindByFirstnameLastnamePassword with id: {0}",firstname + " " + lastname + " " + password);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select * from Angajat where prenume = @firstname and nume = @lastname and parola = @password";
            IDbDataParameter parameterFirstname = selectCommand.CreateParameter();
            parameterFirstname.ParameterName = "@firstname"; 
            parameterFirstname.Value = firstname;
            selectCommand.Parameters.Add(parameterFirstname);
            IDbDataParameter parameterLastname = selectCommand.CreateParameter();
            parameterLastname.ParameterName = "@lastname";
            parameterLastname.Value = lastname;
            selectCommand.Parameters.Add(parameterLastname);
            IDbDataParameter parameterPassword = selectCommand.CreateParameter();
            parameterPassword.ParameterName = "@password"; 
            parameterPassword.Value = password;
            selectCommand.Parameters.Add(parameterPassword);

            using (var dataReader = selectCommand.ExecuteReader())
            {
                if(dataReader.Read())
                {
                    int idAngajat = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    string locatie = dataReader.GetString(3);
                    string parola = dataReader.GetString(4);

                    Angajat angajat = new Angajat(nume, prenume, locatie, parola);
                    angajat.Id = idAngajat;
                    log.InfoFormat("Find angajat: {0}", angajat);
                    return angajat;
                }
            }
            log.Info("No angajat found");
            return null;
        }
    }
}