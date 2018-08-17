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

    private void closeConnection() throws SQLException{
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

    public boolean register(String username, String password, String email){
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

    public void getPlans(String username) {

        String query = "SELECT " + DatabaseFields.PLANS_NAME + " FROM " + DatabaseFields.PLANS_TABLE + " INNER JOIN " + DatabaseFields.USERS_TABLE
                        + " ON " + DatabaseFields.PLANS_TABLE + "." + DatabaseFields.PLANS_USER_ID + " = " + DatabaseFields.USERS_TABLE + "."
                        + DatabaseFields.USERS_ID + " WHERE " + DatabaseFields.USERS_TABLE + "." + DatabaseFields.USERS_USERNAME + " = " + username;

        int i = 0;

        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                i++;
                System.out.println(resultSet.getString("planName"));
            }
            System.out.println(i);
            closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInProgress(String username) {

        String query = "SELECT " + DatabaseFields.INPROGRESS_NAME + " FROM " + DatabaseFields.INPROGRESS_TABLE + " INNER JOIN " + DatabaseFields.USERS_TABLE
                + " ON " + DatabaseFields.INPROGRESS_TABLE + "." + DatabaseFields.INPROGRESS_USER_ID + " = " + DatabaseFields.USERS_TABLE + "."
                + DatabaseFields.USERS_ID + " WHERE " + DatabaseFields.USERS_TABLE + "." + DatabaseFields.USERS_USERNAME + " = " + username;

        int i = 0;

        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                i++;
                System.out.println(resultSet.getString("planName"));
            }
            System.out.println(i);
            closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDone(String username) {

        String query = "SELECT " + DatabaseFields.DONE_NAME + " FROM " + DatabaseFields.DONE_TABLE + " INNER JOIN " + DatabaseFields.USERS_TABLE
                + " ON " + DatabaseFields.DONE_TABLE + "." + DatabaseFields.DONE_USER_ID + " = " + DatabaseFields.USERS_TABLE + "."
                + DatabaseFields.USERS_ID + " WHERE " + DatabaseFields.USERS_TABLE + "." + DatabaseFields.USERS_USERNAME + " = " + username;

        int i = 0;

        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                i++;
                System.out.println(resultSet.getString("planName"));
            }
            System.out.println(i);
            closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
