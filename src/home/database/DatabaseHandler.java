package home.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Config {
    Connection dbConnection = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?serverTimezone=UTC&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, databaseUser, databasePassword);

        return dbConnection;
    }

    public void closeConnection() throws SQLException{
        dbConnection.close();
    }
}
