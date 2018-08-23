package home.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import home.models.Task;
import home.models.TasksList;
import home.models.User;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TasksPageController {

    @FXML
    private AnchorPane mainTasksPane;

    @FXML
    private Pane addPanel;

    @FXML
    private Pane tasksPagePane;

    @FXML
    private JFXTextField taskStateLine;

    @FXML
    private JFXTextField taskNameTextField;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, String> tableViewNameCol;

    @FXML
    private TableColumn<Task, Task> tableViewDeleteCol;

    @FXML
    private TableColumn<Task, Task> tableViewStartCol;

    private FXMLLoader loader;
    private String status;
    private TasksList tasksList;
    private User currentUser;

    public void init(User user, String status){
        this.currentUser = user;
        this.status = status;
        tasksList = new TasksList(currentUser);

        initTable();
        setHeader(status);

        if(status.equals("PLAN"))
            addPanel.setVisible(true);
        else
            addPanel.setVisible(false);

    }

    private void initColumns(){
        tableViewNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewDeleteCol.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        tableViewDeleteCol.setCellFactory(param -> new TableCell<Task, Task>() {
            private final JFXButton deleteButton = new JFXButton("delete");

            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);

                if (task == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);

                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tasksList.removeTask(getTableRow().getIndex(), status);
                        loadData();
                    }
                });
            }
        });

        if(status.equals("PLAN")) {
            tableViewStartCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            tableViewStartCol.setCellFactory(param -> new TableCell<Task, Task>() {
                private final JFXButton startButton = new JFXButton("start");

                @Override
                protected void updateItem(Task task, boolean empty) {
                    super.updateItem(task, empty);

                    if (task == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(startButton);

                    startButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            tasksList.changeTaskStatus(getTableRow().getIndex(), "PLAN", "INPROGRESS");
                            loadData();
                        }
                    });
                }
            });
        }

        if(status.equals("INPROGRESS")) {
            tableViewStartCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            tableViewStartCol.setCellFactory(param -> new TableCell<Task, Task>() {
                private final JFXButton startButton = new JFXButton("done");

                @Override
                protected void updateItem(Task task, boolean empty) {
                    super.updateItem(task, empty);

                    if (task == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(startButton);

                    startButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            tasksList.changeTaskStatus(getTableRow().getIndex(), "INPROGRESS", "DONE");
                            loadData();
                        }
                    });
                }
            });
        }
    }

    private void initTable(){
        initColumns();
        loadData();
    }

    private void loadData(){
        tableView.setItems(tasksList.getList(status));
    }

    @FXML    void onAddPlanButtonClicked(ActionEvent event) {
        String taskName = taskNameTextField.getText().trim();
        if(!taskName.isEmpty()){
            Task task = new Task(taskName, "PLAN");
            if(!tasksList.taskExists(task)) {
                tasksList.addTask(taskName);
                initTable();
            }
        }
    }

    private void setHeader(String status){

        taskStateLine.setText(status);

        switch(status){
            case "PLAN":
                tasksPagePane.setStyle("-fx-background-color: #0099ff");
                mainTasksPane.setStyle("-fx-background-color: #0099ff");
                break;
            case "INPROGRESS":
                tasksPagePane.setStyle("-fx-background-color: #ff9900");
                mainTasksPane.setStyle("-fx-background-color: #ff9900");
                break;
            case "DONE":
                tasksPagePane.setStyle("-fx-background-color: #00b300");
                mainTasksPane.setStyle("-fx-background-color: #00b300");
                break;
        }

    }
}
