package home.models;

import home.database.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TasksDatabase {
    private DatabaseHandler databaseHandler;
    private User user;

    public TasksDatabase(User user){
        this.user = user;
        databaseHandler = new DatabaseHandler();
    }

    public void changeTaskStatus(String name, String newStatus){
        String query = "UPDATE tasks SET status = ? WHERE taskName = ?";

        try{
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Task> getTasksList(String _status){
        String query = "SELECT * FROM tasks INNER JOIN users ON tasks.user_id = users.id WHERE users.username = '" + user.getUsername() + "'AND tasks.status = '" + _status + "'";

        ObservableList<Task> list = FXCollections.observableArrayList();;

        try {
            Statement statement = databaseHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("taskName");
                String status = resultSet.getString("status");

                list.add(new Task(name, status));
            }
            databaseHandler.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private int getUserId(){
        String query = "SELECT id FROM users WHERE username = " + user.getUsername();

        int id = 0;

        try{
            Statement statement = databaseHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next())
                id = resultSet.getInt("id");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void registerTask(String name){
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = simpleDateFormat.format(new java.util.Date());

        String query = "INSERT INTO tasks(taskName, status, dateOfPlanCreateOrChangeStatus, user_id)" + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, "PLAN");
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, getUserId());

            preparedStatement.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeTask(String name){
        String query = "DELETE FROM tasks WHERE taskName = ?";

        try{
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
