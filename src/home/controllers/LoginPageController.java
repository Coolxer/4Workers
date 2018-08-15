package home.controllers;
import home.database.DatabaseHandler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginPageController {
    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton signUpButton;

    private AnchorPane anchorPane;

    private String usernameValue;
    private String passwordValue;

    DatabaseHandler databaseHandler;

    @FXML
    void onSigUpButtonClicked(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/registerPage.fxml"));
        loginAnchorPane.getChildren().add(anchorPane);

    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {
       if(checkFields()){
           databaseHandler = new DatabaseHandler();
           if(databaseHandler.login(usernameValue, passwordValue)){
               anchorPane = FXMLLoader.load(getClass().getResource("/home/views/mainPage.fxml"));
               loginAnchorPane.getChildren().add(anchorPane);
           }
       }
    }

    void getValues(){
        usernameValue = usernameField.getText().trim();
        passwordValue = passwordField.getText().trim();
    }

    boolean checkFields() {
        getValues();
        if (usernameValue.isEmpty() || passwordValue.isEmpty())
            return false;
        else
            return true;
    }

}
