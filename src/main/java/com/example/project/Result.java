package com.example.project;

public class Result extends Encounter {

    public Result(String name, String descript, Choice okay) {
        super.setName(name);
        super.setDescript(descript); //removes the + " What will you do?"
        super.setChoices(new Choice[] {
           okay, //only one option: accept the result of some choice for some encounter
           new Choice(""),
           new Choice("")
        });
    }

    public Result() {
        super.setName("");
        super.setDescript("");
    }

}
