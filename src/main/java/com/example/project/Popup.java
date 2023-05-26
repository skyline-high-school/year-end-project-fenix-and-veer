package com.example.project;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class Popup {

    //NOTE: requires an existing DialogPane with a CLOSE ButtonType already added to it in the FXML file
    //(to be extended later if there is time to make it so this is not a requirement)
    private Button closeButton;
    private DialogPane pane;

    //all in one
    public Popup(DialogPane dialogPane, String header, String content) {
        pane = dialogPane;

        pane.setHeaderText(header);
        pane.setContentText(content);
        pane.setVisible(true);

        closeButton = (Button) pane.lookupButton(ButtonType.CLOSE);
        closeButton.setOnAction(e -> pane.setVisible(false));
    }


    //separated

    public Popup(DialogPane dialogPane) {
        pane = dialogPane;
    }


    public void display(String header, String content) {
        pane.setHeaderText(header);
        pane.setContentText(content);
        pane.setVisible(true);

        //closeButton = (Button) pane.lookupButton(ButtonType.CLOSE);
        //closeButton.setOnAction(e -> pane.setVisible(false));
    }



}
