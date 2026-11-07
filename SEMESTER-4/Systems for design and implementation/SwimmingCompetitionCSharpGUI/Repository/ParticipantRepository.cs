using System.Data;
using System.Data.SqlClient;
using System.Data.SQLite;
using log4net;
using SwimmingCompetition.Domain;
using SwimmingCompetition.Validator;

namespace SwimmingCompetition.Repository;

public class ParticipantRepository: IParticipantRepository
{
    private IDictionary<string, string> _props;
    private IAbstractValidator<Participant> _validator;
    private static readonly log4net.ILog log = LogManager.GetLogger(typeof(ParticipantRepository));

    public ParticipantRepository(IAbstractValidator<Participant> validator, IDictionary<string,string> props)
    {
        log.Info("Creating Participant Repository");
        _props = props;
        _validator = validator;
    }

    public Participant? Add(Participant participant)
    {
        log.InfoFormat("Entering Add with participant: {0}",participant);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var insertCommand = connection.CreateCommand())
        {
            _validator.Validate(participant);
            
            insertCommand.CommandText =
                "insert into Participant(nume, prenume, varsta) values (@nume, @prenume, @varsta)";
            
            IDbDataParameter parameterNume = insertCommand.CreateParameter();
            parameterNume.ParameterName = "@nume";
            parameterNume.Value = participant.Nume;
            insertCommand.Parameters.Add(parameterNume);
            IDbDataParameter parameterPrenume = insertCommand.CreateParameter();
            parameterPrenume.ParameterName = "@prenume";
            parameterPrenume.Value = participant.Prenume;
            insertCommand.Parameters.Add(parameterPrenume);
            IDbDataParameter parameterVarsta = insertCommand.CreateParameter();
            parameterVarsta.ParameterName = "@varsta";
            parameterVarsta.Value = participant.Varsta;
            insertCommand.Parameters.Add(parameterVarsta);
            
            
            int rows = insertCommand.ExecuteNonQuery();
            log.InfoFormat("Inserting {0} rows", rows);
            if (rows == 0)
                return participant;
            return null;
        }
    }

    public Participant? Update(Participant participant)
    {
        log.InfoFormat("Entering Update with participant: {0}",participant);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var updateCommand = connection.CreateCommand())
        {
            _validator.Validate(participant);
            
            updateCommand.CommandText =
                "update Participant set nume=@nume, prenume=@prenume, varsta=@varsta  where id_participant= @id_participant";
            
            IDbDataParameter parameterNume = updateCommand.CreateParameter();
            parameterNume.ParameterName = "@nume";
            parameterNume.Value = participant.Nume;
            updateCommand.Parameters.Add(parameterNume);
            IDbDataParameter parameterPrenume = updateCommand.CreateParameter();
            parameterPrenume.ParameterName = "@prenume";
            parameterPrenume.Value = participant.Prenume;
            updateCommand.Parameters.Add(parameterPrenume);
            IDbDataParameter parameterVarsta = updateCommand.CreateParameter();
            parameterVarsta.ParameterName = "@varsta";
            parameterVarsta.Value = participant.Varsta;
            updateCommand.Parameters.Add(parameterVarsta);
            IDbDataParameter parameterId = updateCommand.CreateParameter();
            parameterId.ParameterName = "@id_participant";
            parameterId.Value = participant.Id;
            updateCommand.Parameters.Add(parameterId);
            
            int rows = updateCommand.ExecuteNonQuery();
            log.InfoFormat("Update {0} rows", rows);
            if (rows == 0)
                return participant;
            return null;
        }
    }

    public Participant? DeleteById(int id)
    {
        log.InfoFormat("Entering DeleteById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var deleteCommand = connection.CreateCommand())
        {
            Participant? participant = FindById(id);
            
            deleteCommand.CommandText =
                "delete from Participant where id_participant= @id_participant";
            
            IDbDataParameter parameterId = deleteCommand.CreateParameter();
            parameterId.ParameterName = "@id_participant";
            parameterId.Value = id;
            deleteCommand.Parameters.Add(parameterId);
            
            int rows = deleteCommand.ExecuteNonQuery();
            log.InfoFormat("Delete {0} rows", rows);
            return participant;
        }
    }

    public Participant? FindById(int id)
    {
        log.InfoFormat("Entering FindById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        using (var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText = "select * from Participant where id_participant = @id_participant";
            IDbDataParameter parameterId = selectCommand.CreateParameter();
            parameterId.ParameterName = "@id_participant";
            parameterId.Value = id;
            selectCommand.Parameters.Add(parameterId);

            using (var dataReader = selectCommand.ExecuteReader())
            {
                if(dataReader.Read())
                {
                    int idParticipant = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    int varsta = dataReader.GetInt32(3);

                    Participant participant = new Participant(nume, prenume, varsta);
                    participant.Id = idParticipant;
                    log.InfoFormat("Find participant: {0}", participant);
                    return participant;
                } 
            }
            log.Info("No participant found");
            return null;
        }
    }

    public List<Participant> FindAll()
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(_props);
        using (var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText = "select * from Participant";

            List<Participant> participanti = new List<Participant>();
            using (var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    int idParticipant = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    int varsta = dataReader.GetInt32(3);

                    Participant participant = new Participant(nume, prenume, varsta);
                    participant.Id = idParticipant;

                    participanti.Add(participant);
                } 
            }
            log.InfoFormat("Find participanti: {0}", participanti);
            return participanti;
        }
    }

    public List<ParticipantDTO> getAllParticipantiFromProba(int probaId)
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(_props);
        using (var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText = "select * from Participant where id_participant in (select id_participant from Participare where id_proba = @id_proba)";
            IDataParameter parameter = selectCommand.CreateParameter();
            parameter.ParameterName = "@id_proba";
            parameter.Value = probaId;
            selectCommand.Parameters.Add(parameter);
            List<ParticipantDTO> participanti = new List<ParticipantDTO>();
            using (var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    int idParticipant = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    int varsta = dataReader.GetInt32(3);
                    List<int> probe = getAllParticipariForParticipant(idParticipant);
                    ParticipantDTO participant = new ParticipantDTO(nume, prenume, varsta, probe);

                    participanti.Add(participant);
                } 
            }
            log.InfoFormat("Find participanti: {0}", participanti);
            return participanti;
        }
    }

    private List<int> getAllParticipariForParticipant(int idParticipant)
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(_props);
        using (var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText = "select id_proba from Participare where id_participant = @id_participant";
            IDataParameter parameter = selectCommand.CreateParameter();
            parameter.ParameterName = "@id_participant";
            parameter.Value = idParticipant;
            selectCommand.Parameters.Add(parameter);
            List<int> probe = new List< int>();
            using (var dataReader = selectCommand.ExecuteReader())
            {
                while(dataReader.Read())
                {
                    int idProba = dataReader.GetInt32(0);
                    probe.Add(idProba);
                } 
            }
            log.InfoFormat("Find participanti: {0}", probe);
            return probe;
        }
    }

    public Participant? findByFirstnameAndLastname(string firstname, string lastname)
    {
        log.InfoFormat("Entering FindById with name: {0}", firstname + " " + lastname);
        IDbConnection connection = DBUtils.getConnection(_props);
        using (var selectCommand = connection.CreateCommand())
        {
            selectCommand.CommandText = "select * from Participant where nume = @lastname and prenume = @firstname";
            IDbDataParameter parameterFirstname = selectCommand.CreateParameter();
            parameterFirstname.ParameterName = "@firstname";
            parameterFirstname.Value = firstname;
            IDbDataParameter parameterLastname = selectCommand.CreateParameter();
            parameterLastname.ParameterName = "@lastname";
            parameterLastname.Value = lastname;
            selectCommand.Parameters.Add(parameterFirstname);
            selectCommand.Parameters.Add(parameterLastname);

            using (var dataReader = selectCommand.ExecuteReader())
            {
                if (dataReader.Read())
                {
                    int idParticipant = dataReader.GetInt32(0);
                    string nume = dataReader.GetString(1);
                    string prenume = dataReader.GetString(2);
                    int varsta = dataReader.GetInt32(3);

                    Participant participant = new Participant(nume, prenume, varsta);
                    participant.Id = idParticipant;
                    log.InfoFormat("Find participant: {0}", participant);
                    return participant;
                }
            }
            log.Info("No participant found");
            return null;
        }
    }
}