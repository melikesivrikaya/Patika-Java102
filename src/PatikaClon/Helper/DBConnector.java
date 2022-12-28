package PatikaClon.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection connect ;
    public static Connection DBConnect() throws SQLException {
            try {
                connect = DriverManager.getConnection(Config.url, Config.user, Config.password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return connect;
    }
    public static void connectClose() throws SQLException {
        connect.close();
    }

    public static Connection getInstance() throws SQLException {
        DBConnector dbConnector = new DBConnector();
        return dbConnector.DBConnect();
    }
}
