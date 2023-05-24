package com.example.project;

import java.util.ArrayList;

public class Encounter {

    private String name; //a title/header for the dialogPane giving a very brief description of the encounter
    private String descript; //longer more detailed description of the scenario/encounter
    private ArrayList<Choice> choices = new ArrayList<>();

    public Encounter(String name, String descript, ArrayList<Choice> choices) {
        this.name = name;
        this.descript = descript;
        this.choices = choices;
    }

    public void choose(Player player, int i) { //i = index of the choice in the Choices ArrayList
        choices.get(i).setChosen(true);
        player.changeHp(choices.get(i).getHpImpact());
        player.changeHunger(choices.get(i).getHungerImpact());
        player.changeThirst(choices.get(i).getThirstImpact());
        player.changeBodyTemp(choices.get(i).getBodyTempImpact());
    }
}
