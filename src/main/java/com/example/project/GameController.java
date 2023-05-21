package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    private Button opA, opB, opC;

    @FXML
    protected void onOpAClick() throws IOException {
        //loads learn mode scene from fxml file by changing the root node
        Stage stage = (Stage) opA.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onOpBClick(ActionEvent event) {
    }
    public void onOpCClick(ActionEvent event) {
    }
}
