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

    public Encounter(String name, String descript) {
        this.name = name;
        this.descript = descript + " What will you do?";
    }

    public void choose(Player player, int i) { //i = index of the choice in the Choices Array
        choices[i].setChosen(true);
        player.changeHp(choices[i].getHpImpact());
        player.changeHunger(choices[i].getHungerImpact());
        player.changeThirst(choices[i].getThirstImpact());
        player.changeHeat(choices[i].getHeatImpact());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }
}
