package home.database;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DatabaseHandler extends Config {
    Connection dbConnection = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, databaseUser, databasePassword);

        return dbConnection;
    }

    public void closeConnection() throws SQLException{
        dbConnection.close();
    }

    public boolean login(String username, String password) {
        String selectString = "SELECT * FROM " + DatabaseFields.USERS_TABLE + " WHERE " +
                        DatabaseFields.USERS_USERNAME + "=?" + " AND " + DatabaseFields.USERS_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(selectString);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
           ResultSet resultSet = preparedStatement.executeQuery();

           if(resultSet.next())
               return true;
           else
               return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean  register(String username, String password, String email){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = simpleDateFormat.format(dt);


        String insertIntoString = "INSERT INTO " + DatabaseFields.USERS_TABLE + "(" + DatabaseFields.USERS_USERNAME + ", " + DatabaseFields.USERS_PASSWORD
                                + ", " + DatabaseFields.USERS_EMAIL + ", " + DatabaseFields.USERS_DATE + ")" + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertIntoString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, date);

            preparedStatement.execute();

            closeConnection();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
