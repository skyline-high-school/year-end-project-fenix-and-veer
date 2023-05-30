package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    @FXML
    private DialogPane dialogPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private ProgressBar hpBar, hungerBar, thirstBar, bodyHeatBar;

    private int numEncounters = 0; //will be a sort of record holder, like "you survived __ encounters", and a time measurement
    @FXML
    private Button opA, opB, opC;

    //Encounters
    private ArrayList<Encounter> irregEnc = new ArrayList<>(); //irregular encounters, so the game can randomly pick one

    private static String imp; //imp = impact, this is just for the below initializations to make it faster to code

    //the following each get their own Encounter variables, rather than being in the ArrayList irregEnc, because they will
    // be commonly used and will be used NON-randomly. They will activate under certain Player variable conditions
    // like low HP after some choice
    private Encounter hpEnc;
    private Encounter hungerEnc;
    private Encounter thirstEnc;
    private Encounter hypothermiaEnc;
    private Encounter hyperthermiaEnc;
    private Encounter findFoodEnc;
    private Encounter foundAnItem;
    private Encounter storeAnItem;
    private Encounter currentEnc;
    private Player player = new Player("Feniz"); //TODO change this
    private Popup popup;
    private static final int triggerAmt = 10; //the value at which hp, hunger, or thirst will be considered low enough to warrant an encounter
    private Random rand = new Random();

    @FXML
    public void initialize() throws InterruptedException {
        player.setHp(8);
        imp = "hp"; //again, imp = impact
        hpEnc = new Encounter("Low HP", "You have taken significant damage.", new Choice[]{
                new Choice("Make a bandage out of leaves", imp, 10),
                new Choice("Clean it with water", imp, 10),
                //technically just cleaning it or putting a bandage on wouldn't heal anything but let's just call it game magic
                new Choice("Wait", imp, 0)
        });

        /*
        findFoodEnc = new Encounter("Find food", "You want to look for food.", new Choice[]{
                new Choice("Look in the woods", )
                new Choice
                imp = "hunger";



        hungerEnc = new Encounter("Hungry", "You are getting hungry.", new Choice[]{
                new Choice("Eat something from your inventory", imp, 19), //19 because max is 20 and min they can be alive at is 1
                new Choice("Find something to eat",
                new Choice("Wait")
        });

         */

        nextEnc();
        popup = new Popup(dialogPane);
        popup.display(currentEnc.getName(), currentEnc.getDescript());
    }

    public void onOpAClick () {
        currentEnc.choose(player, 0);
        hpBar.setProgress((double) player.getHp()/20); //converts the hp ratio out of the max 20 to a number between 0 and 1, which the progressBar is based on
    }

    public void onOpBClick (ActionEvent event){
        currentEnc.choose(player, 1);
    }
    public void onOpCClick (ActionEvent event){
        currentEnc.choose(player, 2);
    }

    //TODO fix
    /*
    public boolean storeAnItemCheck (Object item){
        storeAnItem = new Encounter("Inventory", "Add " + item.toString() + " to your inventory?", new Choice[]{
                new Choice("Add", item),
                new Choice("Don't add"),
                new Choice("") //TODO change later, I just can't think of a third option right now
        });

    }

     */

    public void nextEnc() { //initiates next encounter

        //if the player is in some type of health condition, that will be there next encounter
        // otherwise, a random encounter from the irregular encounters ArrayList will be chosen instead

        //picks the next encounter
        if (player.getHp() <= triggerAmt) {
            currentEnc = hpEnc;
        } else if (player.getHunger() <= triggerAmt) {
            currentEnc = hungerEnc;
        } else if (player.getThirst() <= triggerAmt) {
            currentEnc = thirstEnc;
        } else if (player.getHeat() <= 95) {
            currentEnc = hypothermiaEnc;
        } else if (player.getHeat() <= 100) {
            currentEnc = hyperthermiaEnc; //aka overheating
        } else {
            currentEnc = irregEnc.get(rand.nextInt(irregEnc.size())); //picks a random encounter from the irregular encounters array
        }

        refreshBars();
        refreshOptions();

        /*
        //refreshes buttons
        Choice temp;
        temp = currentEnc.getChoices()[0];
        opA.setText(temp.getText());

        temp = currentEnc.getChoices()[1];
        opB.setText(temp.getText());

        temp = currentEnc.getChoices()[2];
        opC.setText(temp.getText());

         */
    }

    public void refreshOptions() {
        Button[] options = {opA, opB, opC};
        Choice temp;
        for (int i = 0; i < 3; i++) {
            temp = currentEnc.getChoices()[i];
            options[i].setText(temp.getText());
        }
    }

    public void refreshBars() {
        hpBar.setProgress((double) player.getHp()/20);
        hungerBar.setProgress((double) player.getHunger()/20);
        thirstBar.setProgress((double) player.getThirst()/20);
        bodyHeatBar.setProgress((double) player.getHeat()/20);
    }
}

