package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private DialogPane helpPane;
    @FXML
    private Button startButton;
    @FXML
    private Button helpButton;

    @FXML
    protected void onStartButtonClick() throws IOException {
        //loads learn mode scene from fxml file by changing the root node
        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onHelpButtonClick(ActionEvent event) {
        helpPane.setContentText("This is a choose-your-own-adventure style survival game. " +
                "You will face a number of scenarios, or \"encounters\", with many possible responses. Simply select the reaction you want to go with. Your choices matter! Now, go survive!");
        helpPane.setVisible(true);
    }

}