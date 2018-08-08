package home.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainPageController {

    @FXML
    private AnchorPane mainContainer;

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

    @FXML
    private Pane switchPane;

    private Pane newPane;

    @FXML
    void onAboutButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/aboutPage.fxml"));
        switchPane.getChildren().add(newPane);
    }

    @FXML
    void onDoneButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/donePage.fxml"));
        switchPane.getChildren().add(newPane);
    }

    @FXML
    void onExitButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) mainContainer.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onInProgressButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/inProgressPage.fxml"));
        switchPane.getChildren().add(newPane);
    }

    @FXML
    void onPlansButtonClicked(ActionEvent event) throws IOException {
        newPane = FXMLLoader.load(getClass().getResource("/home/views/planesPage.fxml"));
        switchPane.getChildren().add(newPane);
    }

}
