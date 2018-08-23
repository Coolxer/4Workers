package home.controllers;

import home.models.User;
import home.models.UsersList;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class RegisterPageController {
    @FXML
    private AnchorPane registerAnchorPane;

    private AnchorPane anchorPane;

    @FXML
    private JFXTextField usernameField;
    private String usernameValue;

    @FXML
    private JFXPasswordField passwordField;
    private String passwordValue;

    @FXML
    private JFXPasswordField confirmPasswordField;
    private String confirmPasswordValue;

    @FXML
    private JFXTextField emailField;
    private String emailValue;

    private UsersList usersList;

    boolean checkFields(){
        getValues();

        if(usernameValue.isEmpty() || passwordValue.isEmpty() || confirmPasswordValue.isEmpty() || emailValue.isEmpty())
            return false;

        if(!passwordValue.equals(confirmPasswordValue))
            return false;

        return true;
    }

    void getValues(){
        usernameValue = usernameField.getText().trim();
        passwordValue = passwordField.getText().trim();
        confirmPasswordValue = confirmPasswordField.getText().trim();
        emailValue = emailField.getText().trim();
    }

    public void initUsersList(UsersList usersList){
        this.usersList = usersList;
    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/loginPage.fxml"));
        registerAnchorPane.getChildren().add(anchorPane);
    }

    @FXML
    void onSignUpButtonClicked(ActionEvent event) throws IOException {
        if(checkFields()) {
            User user = new User(usernameValue, passwordValue);
            if(usersList.userExists(user) == false){
                usersList.addUser(usernameValue, passwordValue, emailValue);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/views/loginPage.fxml"));
                anchorPane = loader.load();
                LoginPageController loginPageController = loader.getController();
                loginPageController.updateUsersList(usersList);
                registerAnchorPane.getChildren().add(anchorPane);

                user = null;
                usersList = null;
            }
        }
    }
}
