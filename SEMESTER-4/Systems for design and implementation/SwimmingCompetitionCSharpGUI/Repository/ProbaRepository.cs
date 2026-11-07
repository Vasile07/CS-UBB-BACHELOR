using System.Data;
using System.Data.SqlClient;
using System.Data.SQLite;
using System.Security.Permissions;
using log4net;
using SwimmingCompetition.Domain;
using SwimmingCompetition.Validator;

namespace SwimmingCompetition.Repository;

public class ProbaRepository: IProbaRepository
{
    private IAbstractValidator<Proba> _validator;
    private IDictionary<string, string> props;
    private static readonly log4net.ILog log = LogManager.GetLogger(typeof(ProbaRepository));
    
    public ProbaRepository(IAbstractValidator<Proba> validator, IDictionary<string, string> props)
    {
        log.Info("Creating Proba Repository");
        this.props = props;
        _validator = validator;
    }

    public Proba? Add(Proba proba)
    {
        log.InfoFormat("Entering Add with proba: {0}",proba);
        IDbConnection connection = DBUtils.getConnection(props);
        using (var insertCommand = connection.CreateCommand())
        {
            _validator.Validate(proba);
            insertCommand.CommandText =
                "insert into Proba(distanta, stil, data_desfasurarii, locatie) values (@distanta, @stil, @data_desfasurarii, @locatie)";
            IDbDataParameter parameterDistanta = insertCommand.CreateParameter();
            IDbDataParameter parameterStil = insertCommand.CreateParameter();
            IDbDataParameter parameterDataDesfasurarii = insertCommand.CreateParameter();
            IDbDataParameter parameterLocatie = insertCommand.CreateParameter();

            parameterDistanta.ParameterName = "distanta";
            parameterDistanta.Value = proba.Distanta;
            
            parameterStil.ParameterName = "stil";
            parameterStil.Value = proba.Stil.ToString();
            
            parameterDataDesfasurarii.ParameterName = "data_desfasurarii";
            parameterDataDesfasurarii.Value = proba.DataDesfasurarii;
            
            parameterLocatie.ParameterName = "locatie";
            parameterLocatie.Value = proba.Locatie;

            insertCommand.Parameters.Add(parameterDistanta);
            insertCommand.Parameters.Add(parameterStil);
            insertCommand.Parameters.Add(parameterDataDesfasurarii);
            insertCommand.Parameters.Add(parameterLocatie);
            
            int rows = insertCommand.ExecuteNonQuery();
            log.InfoFormat("Inserting {0} rows", rows);
            if (rows == 0)
                return proba;
            return null;
        }
    }

    public Proba? Update(Proba proba)
    {
        log.InfoFormat("Entering Update with proba: {0}",proba);
        IDbConnection connection = DBUtils.getConnection(props);
        using(var updateCommand = connection.CreateCommand())
        {
            _validator.Validate(proba);
            
            updateCommand.CommandText = "update Proba distanta=@distanta, stil=@stil, data_desfasurarii=@data_desfasurarii, locatie=@locatie  where id_proba= @id_proba";

            IDbDataParameter parameterDistanta = updateCommand.CreateParameter();
            IDbDataParameter parameterStil = updateCommand.CreateParameter();
            IDbDataParameter parameterDataDesfasurarii = updateCommand.CreateParameter();
            IDbDataParameter parameterLocatie = updateCommand.CreateParameter();
            IDbDataParameter parameterIdProba = updateCommand.CreateParameter();

            parameterDistanta.ParameterName = "@distanta"; parameterDistanta.Value =  proba.Distanta;
            parameterStil.ParameterName = "@stil"; parameterStil.Value = proba.Stil.ToString();
            parameterDataDesfasurarii.ParameterName = "@data_desfasurarii"; parameterDataDesfasurarii.Value = proba.DataDesfasurarii;
            parameterLocatie.ParameterName = "@locatie"; parameterLocatie.Value = proba.Locatie;
            parameterIdProba.ParameterName = "@id_proba"; parameterIdProba.Value = proba.Id;

            updateCommand.Parameters.Add(parameterDistanta);
            updateCommand.Parameters.Add(parameterStil);
            updateCommand.Parameters.Add(parameterDataDesfasurarii);
            updateCommand.Parameters.Add(parameterLocatie);
            updateCommand.Parameters.Add(parameterIdProba);
            
            int rows = updateCommand.ExecuteNonQuery();
            log.InfoFormat("Update {0} rows", rows);
            if (rows == 0)
                return proba;
            return null;
        }
    }

    public Proba? DeleteById(int id)
    {
        log.InfoFormat("Entering DeleteById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(props);
        using (var deleteCommand = connection.CreateCommand())
        {
            Proba? proba = FindById(id);

            deleteCommand.CommandText =
                "delete from Proba where id_proba= @id_proba";

            IDbDataParameter parameterIdProba = deleteCommand.CreateParameter();

            parameterIdProba.ParameterName = "@id_proba";
            parameterIdProba.Value = id;

            deleteCommand.Parameters.Add(parameterIdProba);
            
            int rows = deleteCommand.ExecuteNonQuery();
            log.InfoFormat("Delete {0} rows", rows);
            return proba;
        }
    }

    public Proba? FindById(int id)
    {
        log.InfoFormat("Entering FindById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(props); 
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select * from Proba where id_proba= @id_proba";

            IDbDataParameter parameterIdProba = selectCommand.CreateParameter();
            parameterIdProba.ParameterName = "@id_proba";
            parameterIdProba.Value = id;
            selectCommand.Parameters.Add(parameterIdProba);
            
            using(var dataReader = selectCommand.ExecuteReader())
            {
                if(dataReader.Read())
                {
                    
                    int idProba = dataReader.GetInt32(0);
                    int distanta = dataReader.GetInt32(1);
                    StilInot stilInot = Enum.Parse<StilInot>(dataReader.GetString(2));
                    DateTime dataDesfasurarii = dataReader.GetDateTime(3);
                    string locatie = dataReader.GetString(4);

                    Proba proba = new Proba(distanta, stilInot, dataDesfasurarii, locatie);
                    proba.Id = idProba;
                    log.InfoFormat("Find proba: {0}", proba);
                    return proba;
                }
            }
            log.Info("No proba found");
            return null;
        }
    }

    public List<Proba> FindAll()
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(props); 
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select * from Proba";
            List<Proba> probe = new List<Proba>();
            using(var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    
                    int idProba = dataReader.GetInt32(0);
                    int distanta = dataReader.GetInt32(1);
                    StilInot stilInot = Enum.Parse<StilInot>(dataReader.GetString(2));
                    DateTime dataDesfasurarii = dataReader.GetDateTime(3);
                    string locatie = dataReader.GetString(4);

                    Proba proba = new Proba(distanta, stilInot, dataDesfasurarii, locatie);
                    proba.Id = idProba;
                    
                    probe.Add(proba);
                }
            }
            log.InfoFormat("Find probe: {0}", probe);
            return probe;
        }
    }

    public List<ProbaDTO> getAllProbaAfterToday()
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(props); 
        using(var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText =
                "select P.*,count(pa.id_participant) as nrInregistrari from Proba P left join Participare Pa on Pa.id_proba = P.id_proba where date(P.data_desfasurarii) > current_date group by P.id_proba";
            List<ProbaDTO> probe = new List<ProbaDTO>();
            using(var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    
                    int idProba = dataReader.GetInt32(0);
                    int distanta = dataReader.GetInt32(1);
                    StilInot stilInot = Enum.Parse<StilInot>(dataReader.GetString(2));
                    DateTime dataDesfasurarii = dataReader.GetDateTime(3);
                    string locatie = dataReader.GetString(4);
                    int nrInscrieri = dataReader.GetInt32(5);
                    ProbaDTO proba = new ProbaDTO(idProba,distanta, stilInot, dataDesfasurarii, locatie,nrInscrieri);
                    
                    probe.Add(proba);
                }
            }
            log.InfoFormat("Find probe: {0}", probe);
            return probe;
        }
    }
}