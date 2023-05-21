package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button startButton;

    @FXML
    protected void onStartButtonClick() throws IOException {
        //loads learn mode scene from fxml file by changing the root node
        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }
}