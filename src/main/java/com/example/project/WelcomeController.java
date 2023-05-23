package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
    private VBox vbox;
    private Button b;

    @FXML
    protected void onStartButtonClick() throws IOException {
        //loads learn mode scene from fxml file by changing the root node
        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onHelpButtonClick(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle()


        /* helpPane.setContentText("This is a choose-your-own-adventure style survival game. " +
                "You will face a number of scenarios, or \"encounters\", with many possible responses. " +
                "Simply select the reaction you want to go with. Your choices matter! Now, go survive!");
        helpPane.setVisible(true);



        AlertPane helpPane = new AlertPane(helpButton, "Help", "This is a choose-your-own-adventure style survival game. " +
                "You will face a number of scenarios, or \"encounters\", with many possible responses. " +
                "Simply select the reaction you want to go with. Your choices matter! Now, go survive!");

         */

    }

}