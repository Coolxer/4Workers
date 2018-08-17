package home.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import home.database.DatabaseHandler;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegisterPageController {
    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField confirmPasswordField;

    @FXML
    private JFXTextField emailField;

    private AnchorPane anchorPane;

    private String usernameValue;
    private String passwordValue;
    private String confirmPasswordValue;
    private String emailValue;

    DatabaseHandler databaseHandler;

    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/loginPage.fxml"));
        registerAnchorPane.getChildren().add(anchorPane);
    }

    @FXML
    void onSignUpButtonClicked(ActionEvent event) throws IOException {
        if(checkFields())
        {
            databaseHandler = new DatabaseHandler();
            if(databaseHandler.register(usernameValue, passwordValue, emailValue)){
                anchorPane = FXMLLoader.load(getClass().getResource("/home/views/loginPage.fxml"));
                registerAnchorPane.getChildren().add(anchorPane);
            }
        }
    }

    void getValues(){
        usernameValue = usernameField.getText().trim();
        passwordValue = passwordField.getText().trim();
        confirmPasswordValue = confirmPasswordField.getText().trim();
        emailValue = emailField.getText().trim();
    }

    boolean checkFields(){
        getValues();
        if(usernameValue.isEmpty() || passwordValue.isEmpty() || confirmPasswordValue.isEmpty() || emailValue.isEmpty())
            return false;
        else
        {
            if(passwordValue == confirmPasswordValue)
                return true;
            else
                return false;
        }
    }
}
