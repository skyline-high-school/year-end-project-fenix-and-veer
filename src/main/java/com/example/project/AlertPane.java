package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertPane {

    private Button closeButton;
    private Label message;
    private VBox vbox;
    private Stage stage;
    private Parent root;

    public AlertPane(Button button, String title, String text) throws IOException {
        stage = (Stage) button.getScene().getWindow(); //gets the stage
        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        closeButton = new Button();
        closeButton.setOnAction(e -> stage.close());

        message = new Label();
        message.setText(text);

        vbox = new VBox();
        vbox.getChildren().addAll(message, closeButton);

        stage.showAndWait();
    }

    /*
    public void display(String title, String text) {
        closeButton = new Button();
        closeButton.setOnAction(e -> stage.close());

        message = new Label();
        message.setText(text);

        vbox = new VBox();
        vbox.getChildren().addAll(message, closeButton);

        stage.showAndWait();
    }

     */

}
