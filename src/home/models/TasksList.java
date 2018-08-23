package home.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TasksList {
    private ObservableList<Task> list = FXCollections.observableArrayList();;
    private TasksDatabase tasksDatabase;

    public TasksList(User user){
        tasksDatabase = new TasksDatabase(user);
    }

    public void addTask(String name){
        tasksDatabase.registerTask(name);
        updateList("PLAN");
    }

    public void changeTaskStatus(int id, String status,String newStatus){
        ObservableList<Task> helpList = tasksDatabase.getTasksList(status);

        Task task = helpList.get(id);

        tasksDatabase.changeTaskStatus(task.getName(), newStatus);
        updateList(status);
    }

    public ObservableList<Task> getList(String tasksStates) {
        updateList(tasksStates);
        return list;
    }

    public int getListSize(){
        return list.size();
    }

    public void removeTask(int id, String status) {
        ObservableList<Task> helpList = tasksDatabase.getTasksList(status);

        Task task = helpList.get(id);
        tasksDatabase.removeTask(task.getName());
        updateList(status);
    }

    public boolean taskExists(Task task){
        return list.contains(task);
    }

    private void updateList(String status){
        list = tasksDatabase.getTasksList(status);
    }
}
