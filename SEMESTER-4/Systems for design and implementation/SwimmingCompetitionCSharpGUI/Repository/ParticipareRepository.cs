using System.Data;
using System.Data.SQLite;
using log4net;
using SwimmingCompetition.Domain;
using SwimmingCompetition.Validator;

namespace SwimmingCompetition.Repository;


public class ParticipareRepository: IParticipareRepository
{
    private IDictionary<string, string> _props;
    private IAbstractValidator<Participare> _validator;
    private static readonly log4net.ILog log = LogManager.GetLogger(typeof(ParticipareRepository));
    
    public ParticipareRepository(IAbstractValidator<Participare> validator, IDictionary<string,string> props)
    {
        log.Info("Creating Participare Repository");
        _props = props;
        _validator = validator;
    }

    public Participare? Add(Participare participare)
    {
        log.InfoFormat("Entering Add with participare: {0}",participare);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var insertCommand = connection.CreateCommand())
        {
            _validator.Validate(participare);
            
            insertCommand.CommandText =
                "insert into Participare(id_participant, id_proba, data_inscrierii) values (@id_participant, @id_proba, @data_inscrierii)";

            IDbDataParameter parameterIdParticipant = insertCommand.CreateParameter();
            parameterIdParticipant.ParameterName = "@id_participant";
            parameterIdParticipant.Value = participare.Id.Id1;
            insertCommand.Parameters.Add(parameterIdParticipant);
            IDbDataParameter parameterIdProba = insertCommand.CreateParameter();
            parameterIdProba.ParameterName = "@id_proba";
            parameterIdProba.Value = participare.Id.Id2;
            insertCommand.Parameters.Add(parameterIdProba);
            IDbDataParameter parameterDataInscrierii = insertCommand.CreateParameter();
            parameterDataInscrierii.ParameterName = "@data_inscrierii";
            DateOnly dataInscrierii = participare.DataInscrierii;
            DateTime dateTime = dataInscrierii.ToDateTime(TimeOnly.MinValue);
            parameterDataInscrierii.Value = dateTime;
            insertCommand.Parameters.Add(parameterDataInscrierii);
            
            int rows = insertCommand.ExecuteNonQuery();
            log.InfoFormat("Inserting {0} rows", rows);
            if (rows == 0)
                return participare;
            return null;
        }
    }

    public Participare? Update(Participare participare)
    {
        log.InfoFormat("Entering Update with participare: {0}",participare);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var updateCommand = connection.CreateCommand())
        {
            _validator.Validate(participare);
            
            updateCommand.CommandText =
                "update Participare set data_inscrierii=@data_inscrierii  where id_participant=@id_participant and id_proba=@id_proba";
            
            IDbDataParameter parameterDataInscrierii = updateCommand.CreateParameter();
            parameterDataInscrierii.ParameterName = "@data_inscrierii";
            parameterDataInscrierii.Value = participare.DataInscrierii;
            updateCommand.Parameters.Add(parameterDataInscrierii);
            IDbDataParameter parameterIdParticipant = updateCommand.CreateParameter();
            parameterIdParticipant.ParameterName = "@id_participant";
            parameterIdParticipant.Value = participare.Id.Id1;
            updateCommand.Parameters.Add(parameterIdParticipant);
            IDbDataParameter parameterIdProba = updateCommand.CreateParameter();
            parameterIdProba.ParameterName = "@id_proba";
            parameterIdProba.Value = participare.Id.Id2;
            updateCommand.Parameters.Add(parameterIdProba);
            
            int rows = updateCommand.ExecuteNonQuery();
            log.InfoFormat("Update {0} rows", rows);
            if (rows == 0)
                return participare;
            return null;
        }
    }

    public Participare? DeleteById(Pair<int, int> id)
    {
        log.InfoFormat("Entering DeleteById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        using(var deleteCommand = connection.CreateCommand())
        {
            Participare? participare = FindById(id);
            
            deleteCommand.CommandText =
                "delete from Participare where id_participant=@id_participant and id_proba=@id_proba";
            
            IDbDataParameter parameterIdParticipant = deleteCommand.CreateParameter();
            parameterIdParticipant.ParameterName = "@id_participant";
            parameterIdParticipant.Value = participare.Id.Id1;
            deleteCommand.Parameters.Add(parameterIdParticipant);
            IDbDataParameter parameterIdProba = deleteCommand.CreateParameter();
            parameterIdProba.ParameterName = "@id_proba";
            parameterIdProba.Value = participare.Id.Id2;
            deleteCommand.Parameters.Add(parameterIdProba);
            
            int rows = deleteCommand.ExecuteNonQuery();
            log.InfoFormat("Delete {0} rows", rows);
            return participare;
        }
    }

    public Participare? FindById(Pair<int, int> id)
    {
        log.InfoFormat("Entering FindById with id: {0}",id);
        IDbConnection connection = DBUtils.getConnection(_props);
        try
        {
            using(var selectCommand = connection.CreateCommand())
            {
                selectCommand.CommandText = "select * from Participare where id_participant=@id_participant and id_proba=@id_proba";
            
                IDbDataParameter parameterIdParticipant = selectCommand.CreateParameter();
                parameterIdParticipant.ParameterName = "@id_participant";
                parameterIdParticipant.Value = id.Id1;
                selectCommand.Parameters.Add(parameterIdParticipant);
                IDbDataParameter parameterIdProba = selectCommand.CreateParameter();
                parameterIdProba.ParameterName = "@id_proba";
                parameterIdProba.Value = id.Id2;
                selectCommand.Parameters.Add(parameterIdProba);

                using(var dataReader = selectCommand.ExecuteReader())
                {
                    if(dataReader.Read())
                    {
                        int idParticipant = dataReader.GetInt32(0);
                        int idProba = dataReader.GetInt32(1);
                        DateOnly dataInscrierii = DateOnly.FromDateTime(dataReader.GetDateTime(2));

                        Participare participare = new Participare(dataInscrierii);
                        participare.Id = new Pair<int, int>(idParticipant, idProba);
                        log.InfoFormat("Find participare: {0}", participare);
                        return participare;
                    }
                }
                log.Info("No participare found");
                return null;
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
        
    }

    public List<Participare> FindAll()
    {
        log.Info("Entering FindAll");
        IDbConnection connection = DBUtils.getConnection(_props);
        try
        {
            using (var selectCommand = connection.CreateCommand())
            {
                selectCommand.CommandText = "select * from Participare";

                List<Participare> participari = new List<Participare>();
                using (var dataReader = selectCommand.ExecuteReader())
                {
                    while (dataReader.Read())
                    {
                        int idParticipant = dataReader.GetInt32(0);
                        int idProba = dataReader.GetInt32(1);
                        DateOnly dataInscrierii = DateOnly.FromDateTime(dataReader.GetDateTime(2));

                        Participare participare = new Participare(dataInscrierii);
                        participare.Id = new Pair<int, int>(idParticipant, idProba);

                        participari.Add(participare);
                    }
                }

                log.InfoFormat("Find participari: {0}", participari);
                return participari;
            }
        }
        catch(Exception e)
        {
            Console.Error.WriteLine(e);
            return null;
        }
    }
}