using System.Data;
using System.Data.SqlClient;
using System.Data.SQLite;

namespace SwimmingCompetition.Repository;

public class DBUtils
{
    private static IDbConnection instance = null;

    public static IDbConnection getConnection(IDictionary<string, string> props)
    {
        if (instance == null || instance.State == System.Data.ConnectionState.Closed)
        {
            instance = getNewConnection(props);
            instance.Open();
        }

        return instance;
    }

    private static IDbConnection getNewConnection(IDictionary<string, string> props)
    {
        String connectionString = props["ConnectionString"];
        return new SQLiteConnection(connectionString);
    }
}