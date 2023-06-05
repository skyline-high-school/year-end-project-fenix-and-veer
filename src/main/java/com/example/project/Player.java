package com.example.project;

import javafx.scene.control.DialogPane;

import java.util.ArrayList;

public class Player {


    //private String name;
    private int hunger;
    private int thirst;
    private int hp; //health
    private ArrayList<Object> inv; //inventory
    private ArrayList<FoodItem> foodInv; //inv just for food
    private int heat; //body temperature
    private boolean dead;
    private String causeOfDeath;

    public Player() {
        //this.name = name;
        hunger = 20;
        thirst = 20;
        hp = 20;
        heat = 98; //in degrees F
        inv = new ArrayList<>(); //max items the user can carry = 10
        dead = false;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    /*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     */

    public ArrayList<Object> getInv() {
        return inv;
    }

    public void setInv(ArrayList<Object> inv) {
        this.inv = inv;
    }

    public ArrayList<FoodItem> getFoodInv() {
        return foodInv;
    }

    public void setFoodInv(ArrayList<FoodItem> foodInv) {
        this.foodInv = foodInv;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    //because it looks nicer than having to do "player.setHunger(player.getHp() - choices[i].getHpImpact())" every time
    public void changeHp(int change) {
        this.hp += change; //will decrease hp if the change passed for the parameter is negative
        if (this.hp > 20) {
            this.hp = 20; //max hp
        } else if (hp <= 0) {
            dead = true;
            causeOfDeath = "taking too much damage";
        }
    }

    public void changeHunger(int change) {
        this.hunger += change; //will decrease if the change passed for the parameter is negative
        if (this.hunger > 20) {
            this.hunger = 20; //max hunger
        } else if (hunger <= 0) {
            dead = true;
            causeOfDeath = "starvation";
        }
    }

    public void changeThirst(int change) {
        this.thirst += change; //will decrease if the change passed for the parameter is negative
        if (this.thirst > 20) {
            this.thirst = 20; //max thirst level
        } else if (thirst <= 0) {
            dead = true;
            causeOfDeath = "dehydration";
        }
    }

    public void changeHeat(int change) {
        this.heat += change; //will decrease if the change passed for the parameter is negative
        if (heat > 104) { //based on real life body temperature average limits
            dead = true;
            causeOfDeath = "heat stroke";
        } else if (heat < 95) {
            dead = true;
            causeOfDeath = "hypothermia";
        }
        //TODO decide if there should be an encounter for hypo/hyperthermia, or if there should just be a bar for this
        // and other stats, or both
    }

    public void addToInv(Object item) {
        Encounter encounter = new Encounter("Inventory", "Add " + item.toString() + " to your inventory?", new Choice[]{
                new Choice("Add", item),
                new Choice("Don't add"),
                new Choice("") //TODO change later, I just can't think of a third option right now
        });
        this.inv.add(item);
    }

    public void resetStats() {
        hunger = 20;
        thirst = 20;
        hp = 20;
        heat = 98; //in degrees F
        inv = new ArrayList<>(); //max items the user can carry = 10
        dead = false;
    }
}
