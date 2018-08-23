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

public class LoginPageController {
    @FXML
    private AnchorPane loginAnchorPane;

    private AnchorPane anchorPane;

    @FXML
    private JFXPasswordField passwordField;
    private String passwordValue;

    @FXML
    private JFXTextField usernameField;
    private String usernameValue;

    private UsersList usersList;

    public LoginPageController(){
        usersList = new UsersList();
    }

    private boolean checkFields() {
        getValues();
        if (usernameValue.isEmpty() || passwordValue.isEmpty())
            return false;
        else
            return true;
    }

    private void getValues() {
        usernameValue = usernameField.getText().trim();
        passwordValue = passwordField.getText().trim();
    }

    @FXML
    void onLogInButtonClicked(ActionEvent event) throws IOException {
        if (checkFields()) {
            User user = new User(usernameValue, passwordValue);
            if(usersList.userExists(user)){
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/home/views/mainPage.fxml"));
                anchorPane = loader.load();
                MainPageController mainPageController = loader.getController();
                mainPageController.setCurrentUser(user);
                loginAnchorPane.getChildren().add(anchorPane);

                user = null;
                usersList = null;
            }
        }
    }

    @FXML
    void onSigUpButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/home/views/registerPage.fxml"));
        anchorPane = loader.load();
        RegisterPageController registerPageController = loader.getController();
        registerPageController.initUsersList(usersList);
        loginAnchorPane.getChildren().add(anchorPane);
    }


    public void updateUsersList(UsersList usersList){
        this.usersList = usersList;
    }

}
