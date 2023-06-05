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

    private Encounter firstEnc;
    private Encounter hpEnc;
    private Encounter hungerEnc;
    private Encounter thirstEnc = new Encounter();
    private Encounter hypothermiaEnc = new Encounter();
    private Encounter hyperthermiaEnc = new Encounter();
    private Encounter findFoodEnc = new Encounter();
    private Encounter foundAnItem = new Encounter();
    private Encounter storeAnItem = new Encounter();
    private Encounter currentEnc = new Encounter();
    private Encounter[] woodsSearchEncounters;
    private Encounter searchInvForFood;
    private Encounter noFoodInvEnc;
    private Encounter hungerEncNoFoodInv;
    private Encounter buildShelter;
    private Encounter whereToBuildShelter;
    private Encounter getWater;

    //TODO delete
    private Encounter testEnc;

    private Encounter startFire;
    private Player player = new Player("Feniz"); //TODO change this
    private Popup popup;
    private static final int triggerAmt = 10; //the value at which hp, hunger, or thirst will be considered low enough to warrant an encounter
    private Random rand = new Random();
    private Encounter bearSight;



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

        //array for multiple encounter outcomes from a previous encounter
        woodsSearchEncounters = new Encounter[]{new Encounter("Food found in the woods", "You found some unfamiliar red berries in the woods.", new Choice[]{
                new Choice("Eat the berries"),
                new Choice("Don't eat the berries", "hunger", -4),
                new Choice("Don't eat, keep looking for food", null)
        }),
                new Encounter("Food found in the woods", "You found a wild chicken in the woods.", new Choice[]{
                        new Choice("Cook and eat the chicken", "hunger", 20),
                        new Choice("Just take the egg from its nest", new FoodItem("egg", 10)),
                        new Choice("Leave it and take nothing", "hunger", 0)
                }),
                new Encounter("Food found in the woods", "You found some tree bark in the woods.", new Choice[]{
                        new Choice("Eat the tree bark", "hunger", 10),
                        new Choice("Don't eat the tree bark", "hunger", -4),
                        new Choice("Don't eat, keep looking for food", null)
                }),
                new Encounter("Food found in the woods", "You found a couple worms in the woods.", new Choice[]{
                        new Choice("Eat the worms", "hunger", 10),
                        new Choice("Don't eat the worms", "hunger", -4),
                        new Choice("Don't eat, keep looking for food", null)
                })};

        unknownFoodImp(woodsSearchEncounters[0].getChoices()[0]); //sets whether the berries are poisonous

        woodsSearchEncounters[0].getChoices()[2].setEncounterImpact(woodsSearchEncounters[rand.nextInt(woodsSearchEncounters.length)]);
        woodsSearchEncounters[2].getChoices()[2].setEncounterImpact(woodsSearchEncounters[rand.nextInt(woodsSearchEncounters.length)]);
        woodsSearchEncounters[3].getChoices()[2].setEncounterImpact(woodsSearchEncounters[rand.nextInt(woodsSearchEncounters.length)]);


        findFoodEnc = new Encounter("Find food", "You want to look for food.", new Choice[]{
                new Choice("Look in the woods", woodsSearchEncounters[rand.nextInt(woodsSearchEncounters.length)]),
                new Choice("Go fishing with a makeshift spear", "hunger", 19),
                new Choice("Eat something from your inventory", searchInvForFood(findFoodEnc))
        });

        hungerEnc = new Encounter("Hungry", "You are getting hungry.");
        hungerEnc.setChoices(new Choice[]{
                new Choice("Eat something from your inventory", searchInvForFood(hungerEnc)),
                new Choice("Find something to eat", findFoodEnc),
                new Choice("Wait", "hunger", 0)
        });

        thirstEnc = new Encounter("Dehydration", "You are thirsty and dehydrated.", new Choice[]{
                new Choice("Drink some water straight from the sea"),
                new Choice("Get water from the sea, boil it in a makeshift pot, and then drink it", new Encounter(
                        "A makeshift pot", "You want to boil some water. What will you use to make a pot?",
                        new Choice[]{
                                new Choice("A large seashell", new Result("Success", "Your makeshift pot worked",
                                        new Choice("Okay", "thirst", 19))),
                                new Choice("Some hollowed-out wood that you will put fire-heated rocks in", new Result("Success",
                                        "Your makeshift pot worked", new Choice("Okay", "thirst", 19))),
                                new Choice("A glass bottle you found on the beach", new Result("Bottle shattered",
                                        "Upon getting too hot, the glass bottle shattered. Not only did your water spill into the fire pit," +
                                                "but some splashed onto you, burning your skin, and some shards of glass shot out and pierced you.",
                                        new Choice("Okay", "hp", -14))
                                )
                        })
                ),
                new Choice("Wait", "thirst", 0)
        });

        //almost the same as thirstEnc, but with differences in the encounter name and description and one of the choice texts
        // this one is voluntary and optional, not a necessity
        getWater = new Encounter("Where to get water", "You want to get some water. What will you do?", new Choice[]{
                new Choice("Drink some water straight from the sea"),
                new Choice("Get water from the sea, boil it in a makeshift pot, and then drink it", new Encounter(
                        "A makeshift pot", "You want to boil some water. What will you use to make a pot?",
                        new Choice[]{
                                new Choice("A large seashell", new Result("Success", "Your makeshift pot worked",
                                        new Choice("Okay", "thirst", 19))),
                                new Choice("Some hollowed-out wood that you will put fire-heated rocks in", new Result("Success",
                                        "Your makeshift pot worked", new Choice("Okay", "thirst", 19))),
                                new Choice("A glass bottle you found on the beach", new Result("Bottle shattered",
                                        "Upon getting too hot, the glass bottle shattered. Not only did your water spill into the fire pit," +
                                                "but some splashed onto you, burning your skin, and some shards of glass shot out and pierced you.",
                                        new Choice("Okay", "hp", -14))
                                )
                        })
                ),
                new Choice("Nevermind, do nothing", "thirst", 0)
        });

        whereToBuildShelter = new Encounter("Where to build a shelter or sleep", "Where will you build your new shelter and/or sleep?", new Choice[]{
                new Choice("Under a cliff ledge"),
                new Choice("Close to the beach", new Result("Shelter destroyed", "You build your shelter by the beach, " +
                        "but the tide rises, and waves and wind knock it down.", new Choice("Okay", buildShelter))),
                new Choice("In the woods")
        });
        whereToBuildShelter.setDescript("Where will you build your shelter and/or sleep?");

        buildShelter = new Encounter("Build a shelter", "What will you build your new shelter with?", new Choice[]{
                new Choice("Nothing, you don't need a shelter"),
                new Choice("Sticks and leaves", whereToBuildShelter),
                new Choice("Mud, sticks, and leaves", whereToBuildShelter)
        });
        buildShelter.setDescript("What will you build your shelter with?");

        hyperthermiaEnc = new Encounter("hot", "It is very hot out. What will you do?", new Choice[]{
                new Choice("Stay in the water", "heat", -10),
                new Choice("Stay in the shady woods", "heat", -5),
                new Choice("Go about your day as usual", "heat", 50)
        });
        startFire = new Encounter("startFire", "How will you try to start a fire?", new Choice[]{
                new Choice("Rubbing sticks together","heat",0),
                new Choice("Holding a glass bottle from the beach under the sunlight over some small sticks","heat",20),
                new Choice("Rubbing rocks together","heat",0)
        });
        hypothermiaEnc = new Encounter("cold", "It is very cold and raining. What should you do?", new Choice[]{
                new Choice("Try to start a fire under the cover of your shelter/the cliff ledge",startFire),
                new Choice("Get some big leaves to use as a blanket","heat",5),
                new Choice("Stay where you are and huddle in a tight ball","heat",-10)
        });



        bearSight = new Encounter("Bear", "A bear sees you. It has a cub. What will you do?", new Choice[]{
                new Choice("Try to quietly back away","hp",0),
                new Choice("Yell loudly at it, waving your arms","hp",0),
                new Choice("Play dead","hp",-50)
        });
        //TODO delete and adjust the nextEnc() method to select a random enc from the irregEnc arraylist (once it is filled)
        testEnc = new Encounter("omg", "I figured it out", new Choice[]{
                new Choice("1", "hunger", -5),
                new Choice("2", "hunger", -5),
                new Choice("3", "hunger", -5)
        });

        irregEnc.add(bearSight);
        irregEnc.add(hyperthermiaEnc);
        irregEnc.add(hypothermiaEnc);
        irregEnc.add(hungerEnc);
        irregEnc.add(thirstEnc);

        //this looks pretty crazy, but it is just the first encounter of the game, asking what you want to do first.
        firstEnc = new Encounter("Stranded", "You were traveling over the sea when your ship sunk. " +
                "You swam until you reached land, and you now find yourself stranded alone on the beach of an unknown island.",
                new Choice[]{
                        new Choice("Build a shelter", buildShelter),
                        new Choice("Get water", getWater),
                        new Choice("Sit around on the beach and wait for a rescuer", new Result(
                                "Waiting...", "You wait on the beach for a long while, but nobody comes by.",
                                new Choice("Get up", new Encounter("What to do next",
                                        "It doesn't look like anybody is going to come anytime soon.",
                                        new Choice[]{
                                                new Choice("Build a shelter", buildShelter),
                                                new Choice("Get water", getWater),
                                                new Choice("Get food", findFoodEnc)
                                        }
                                ))
                        ))
                });

        nextEnc(firstEnc);
        popup = new Popup(dialogPane);
        popup.display(currentEnc.getName(), currentEnc.getDescript());
    }

    public void onOpAClick() {
        currentEnc.choose(player, 0);
        Choice[] tempChoices = currentEnc.getChoices();

        /*
        if (currentEnc.getChoices()[0].getEncounterImpact() == null) {
            nextEnc();

         */

        if (tempChoices.length > 0 && tempChoices[0].getEncounterImpact() == null) {
            nextEnc();
        } else {
            nextEnc(currentEnc.getChoices()[0].getEncounterImpact());
        }
    }

    public void onOpBClick(ActionEvent event) {
        /*
        currentEnc.choose(player, 1);
        if (currentEnc.getChoices()[1].getEncounterImpact() == null) {
            nextEnc();
        } else {
            nextEnc(currentEnc.getChoices()[1].getEncounterImpact());
        }

         */

        currentEnc.choose(player, 1);
        Choice[] tempChoices = currentEnc.getChoices();
        if (tempChoices.length > 0 && tempChoices[1].getEncounterImpact() == null) {
            nextEnc();
        } else {
            nextEnc(currentEnc.getChoices()[1].getEncounterImpact());
        }
    }

    public void onOpCClick(ActionEvent event) {
        /*currentEnc.choose(player, 2);
        if (currentEnc.getChoices()[2].getEncounterImpact() == null) {
            nextEnc();
        } else {
            nextEnc(currentEnc.getChoices()[2].getEncounterImpact());
        }

         */

        currentEnc.choose(player, 2);
        Choice[] tempChoices = currentEnc.getChoices();
        if (tempChoices.length > 0 && tempChoices[2].getEncounterImpact() == null) {
            nextEnc();
        } else {
            nextEnc(currentEnc.getChoices()[2].getEncounterImpact());
        }
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

        System.out.println(this.currentEnc);

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
        } else if (player.getHeat() >= 100) {
            currentEnc = hyperthermiaEnc; //aka overheating
        } else {
            currentEnc = irregEnc.get(rand.nextInt(irregEnc.size())); //picks a random encounter from the irregular encounters array
            // currentEnc = testEnc;
        }

        System.out.println(this.currentEnc);

        refreshBars();
        refreshOptions();
    }

    //for when one encounter leads to another encounter
    public void nextEnc(Encounter encounter) {
        /*
        if (encounter != null) {
            currentEnc = encounter;
            refreshBars();
            refreshOptions();
        }
         */
        System.out.println(this.currentEnc);

        currentEnc = encounter;
        refreshBars();
        refreshOptions();

        System.out.println(this.currentEnc);
    }

    public void refreshOptions() {
        Button[] options = {opA, opB, opC};
        Choice temp;
        for (int i = 0; i < 3; i++) {
            temp = currentEnc.getChoices()[i];
            options[i].setText(temp.getText());
        }
        popup = new Popup(dialogPane);
        popup.display(currentEnc.getName(), currentEnc.getDescript());
    }

    public void refreshBars() {
        hpBar.setProgress((double) player.getHp() / 20); //converts the hp ratio out of the max 20 to a number between 0 and 1, which the progressBar is based on
        hungerBar.setProgress((double) player.getHunger() / 20);
        thirstBar.setProgress((double) player.getThirst() / 20);
        bodyHeatBar.setProgress((double) player.getHeat() / 20);
    }


    public void unknownFoodImp(Choice choice) {
        if (rand.nextBoolean()) { //if safe to eat
            choice.setHungerImpact(10);
        } else { //if poisonous
            choice.setHpImpact(-15);
            player.setCauseOfDeath("food poisoning");
        }
    }

    //based on if the player has stored any food in their inventory, they will get one of two different Encounters responding
    // to a request to look for food in their inventory
    public Encounter searchInvForFood(Encounter originEnc) {
        if (originEnc == null) {
            throw new IllegalArgumentException();
        } else if (player.getFoodInv() == null || player.getFoodInv().isEmpty()) {
            noFoodInvEnc = new Encounter(originEnc.getName(), originEnc.getDescript(), new Choice[]{
                    originEnc.getChoices()[0],
                    originEnc.getChoices()[1],
                    new Choice("Leave it", "hunger", 0) //the replacement for the search inv choice
            });
            hungerEncNoFoodInv = new Result("No food in inventory", "It looks like you don't have any food stored in your inventory yet.",
                    new Choice("Okay", noFoodInvEnc) //returns the user to the original encounter so they can choose to do something else
            );

            /*
            return new Encounter("No food in inventory", "It looks like you don't have any food stored in your inventory yet.", new Choice[] {
                    new Choice("Okay", noFoodInvEnc), //returns the user to the original encounter so they can choose to do something else
                    new Choice(""),
                    new Choice("")
            });

             */
            return hungerEncNoFoodInv;
        } else {
            searchInvForFood = new Encounter("Search inventory", "Your inventory includes: " + player.getFoodInv().toString());
            Choice[] tempChoices = new Choice[3];
            for (int i = 0; i < 3; i++) {
                if (player.getFoodInv().get(i) != null) {
                    tempChoices[i] = new Choice("Eat" + player.getFoodInv().get(i).toString(), "hunger", player.getFoodInv().get(i).getHungerImp());
                } else {
                    tempChoices[i] = new Choice("");
                }
            }
            searchInvForFood.setChoices(tempChoices);
            return searchInvForFood;
        }
    }

}

