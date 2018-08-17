package home.controllers;

import home.database.DatabaseHandler;

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

    private AnchorPane anchorPane;

    private String usernameValue;
    private String passwordValue;

    private DatabaseHandler databaseHandler;

    @FXML
    void onSigUpButtonClicked(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/home/views/registerPage.fxml"));
        loginAnchorPane.getChildren().add(anchorPane);

    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {
        if (checkFields()) {
            databaseHandler = new DatabaseHandler();
            if (databaseHandler.login(usernameValue, passwordValue)) {
                //anchorPane = FXMLLoader.load(getClass().getResource("/home/views/mainPage.fxml"));
                //loginAnchorPane.getChildren().add(anchorPane);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/home/views/mainPage.fxml"));

                anchorPane = loader.load();

                MainPageController mainPageController = loader.getController();
                mainPageController.createUser(usernameValue, passwordValue);

                loginAnchorPane.getChildren().add(anchorPane);
            }
        }
    }

    private void getValues() {
        usernameValue = usernameField.getText().trim();
        passwordValue = passwordField.getText().trim();
    }

    private boolean checkFields() {
        getValues();
        if (usernameValue.isEmpty() || passwordValue.isEmpty())
            return false;
        else
            return true;
    }

}
