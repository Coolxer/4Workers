package home.models;

import home.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersDatabase {
    private DatabaseHandler databaseHandler;

    public UsersDatabase(){
        databaseHandler = new DatabaseHandler();
    }

    public List<User> getUsersList(){
        String query = "SELECT username, password from users";

        List<User> list = new ArrayList<User>();

        try {
            Statement statement = databaseHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                list.add(new User(username, password));

            }
            databaseHandler.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void registerUser(String username, String password, String email){
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = simpleDateFormat.format(new java.util.Date());

        String query = "INSERT INTO users(username, password, email, dateOfAccountCreate)" + "VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, date);

            preparedStatement.execute();

            databaseHandler.closeConnection();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
