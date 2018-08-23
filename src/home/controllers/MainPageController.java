package home.controllers;

import home.models.User;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPageController{
    @FXML
    private AnchorPane mainAnchorPane;

    private AnchorPane anchorPane;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private Pane switchPane;

    @FXML
    private JFXButton plansButton;

    @FXML
    private JFXButton inProgressButton;

    @FXML
    private JFXButton doneButton;

    @FXML
    private JFXButton aboutButton;

    @FXML
    private JFXButton exitButton;

    private FXMLLoader loader;
    private Pane newPane;
    private String status = "";
    private User currentUser;

    private void initializeTasksPage(String status){
        TasksPageController tasksPageController = loader.getController();
        tasksPageController.init(currentUser, status);
    }

    private void logout(){
        status = "";
        currentUser = null;
    }

    @FXML
    private void onAboutButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/aboutPage.fxml"));
        switchPane.getChildren().add(newPane);
        status = "";
    }

    @FXML
    private void onDoneButtonClicked(ActionEvent event) throws IOException {
        if(status.isEmpty())
            showTasksPage();

        status = "DONE";
        initializeTasksPage(status);
    }

    @FXML
    private void onExitButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onInProgressButtonClicked(ActionEvent event) throws IOException {
        if(status.isEmpty())
            showTasksPage();

        status = "INPROGRESS";
        initializeTasksPage(status);
    }

    @FXML
    private void onLogOutButtonClicked(ActionEvent event) throws IOException {
        logout();

        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/loginPage.fxml"));
        mainAnchorPane.getChildren().add(anchorPane);
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        System.out.printf("enter");
        //inProgressButton.setTranslateX(50);
        //inProgressButton.setOpacity(0.8);

    }

    @FXML
    void onMouseExited(MouseEvent event) {
        System.out.println("exit");
        //inProgressButton.setOpacity(1);
    }

    @FXML
    private void onPlansButtonClicked(ActionEvent event) throws IOException {
        if(status.isEmpty())
            showTasksPage();

        status = "PLAN";
        initializeTasksPage(status);
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
        usernameField.setText(currentUser.getUsername());
    }

    private void showTasksPage() throws IOException{
        loader = new FXMLLoader(getClass().getResource("/home/views/tasksPage.fxml"));
        newPane = loader.load();
        switchPane.getChildren().add(newPane);
    }

}
