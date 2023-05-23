package com.example.project;

public class Encounter {

    private String scenario;
    private String[] choices;
    private String[] impact;

    public Encounter(String scenario, String[] choices, String[] impact) {
        this.scenario = scenario;
        this.choices = choices;
        this.impact = impact;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String[] getImpact() {
        return impact;
    }

    public void setImpact(String[] impact) {
        this.impact = impact;
    }
}
