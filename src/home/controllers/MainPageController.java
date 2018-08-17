package home.controllers;

import home.models.User;
import home.database.DatabaseHandler;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import java.io.IOException;


public class MainPageController{
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private Pane switchPane;

    private Pane newPane;

    private AnchorPane anchorPane;

    private User user;
    private DatabaseHandler databaseHandler;

    public void createUser(String username, String password) {
        user = new User(username, password);
        usernameField.setText(user.getUsername());
    }

    @FXML
    void onAboutButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/aboutPage.fxml"));
        switchPane.getChildren().add(newPane);
    }

    @FXML
    void onDoneButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/donePage.fxml"));
        switchPane.getChildren().add(newPane);

        databaseHandler = new DatabaseHandler();
        databaseHandler.getDone(user.getUsername());
    }

    @FXML
    void onExitButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onInProgressButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/inProgressPage.fxml"));
        switchPane.getChildren().add(newPane);

        databaseHandler = new DatabaseHandler();
        databaseHandler.getInProgress(user.getUsername());
    }

    @FXML
    void onPlansButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/plansPage.fxml"));
        switchPane.getChildren().add(newPane);

        databaseHandler = new DatabaseHandler();
        databaseHandler.getPlans(user.getUsername());
    }

    @FXML
    void onLogOutButtonClicked(ActionEvent event) throws IOException {
        //log out system ...
        //...
        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/loginPage.fxml"));
        mainAnchorPane.getChildren().add(anchorPane);
    }

}
