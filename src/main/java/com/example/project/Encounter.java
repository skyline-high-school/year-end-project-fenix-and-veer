package com.example.project;

public class Encounter {

    private String name; //a title/header for the dialogPane giving a very brief description of the encounter
    private String descript; //longer more detailed description of the scenario/encounter
    private Choice[] choices = new Choice[3];

    public Encounter(String name, String descript, Choice[] choices) {
        this.name = name;
        this.descript = descript + " What will you do?";
        this.choices = choices;
    }

    public Encounter() {
    }

    public void choose(Player player, int i) { //i = index of the choice in the Choices ArrayList
        choices[i].setChosen(true);
        player.changeHp(choices[i].getHpImpact());
        player.changeHunger(choices[i].getHungerImpact());
        player.changeThirst(choices[i].getThirstImpact());
        player.changeHeat(choices[i].getHeatImpact());
    }
}
