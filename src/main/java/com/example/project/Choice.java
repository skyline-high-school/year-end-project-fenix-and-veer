package com.example.project;

public class Choice {
    //choices for the Encounter class

    private String text = "";
    private int hpImpact = 0;
    private int hungerImpact = 0;
    private int thirstImpact = 0;
    private int heatImpact = 0;
    private Encounter encounterImpact; //for when selecting a choice sets another encounter into motion
    private Object invAdd; //object to be added to the inventory as an impact of the choice (if selected)
    private boolean chosen; //true if the user chose this choice (for other choice consequences that depend on what they chose here)

    public Choice(String text) {
        this.text = text;
    }

    public Choice(String text, String impact, int impactAmt) {
        this.text = text;

        if(impact.equals("hp")) {
            this.hpImpact = impactAmt;
        } else if(impact.equals("hunger")) {
            this.hungerImpact = impactAmt;
        } else if(impact.equals("thirst")) {
            this.thirstImpact = impactAmt;
        } else if(impact.equals("heat")) {
            this.heatImpact = impactAmt;
        } else {
            throw new IllegalArgumentException("Your impact String must be either hp, hunger, thirst, or heat");
        }
    }

    public Choice(String text, Object item) {
        this.text = text;
        this.invAdd = item; //item to add to inventory as a result of this choice
    }

    public Choice(String text, Encounter encounter) {
        this.text = text;
        this.encounterImpact = encounter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHpImpact() {
        return hpImpact;
    }

    public void setHpImpact(int hpImpact) {
        this.hpImpact = hpImpact;
    }

    public int getHungerImpact() {
        return hungerImpact;
    }

    public void setHungerImpact(int hungerImpact) {
        this.hungerImpact = hungerImpact;
    }

    public int getThirstImpact() {
        return thirstImpact;
    }

    public void setThirstImpact(int thirstImpact) {
        this.thirstImpact = thirstImpact;
    }

    public int getHeatImpact() {
        return heatImpact;
    }

    public void setHeatImpact(int heatImpact) {
        this.heatImpact = heatImpact;
    }

    public Object getInvAdd() {
        return invAdd;
    }

    public void setInvAdd(Object invAdd) {
        this.invAdd = invAdd;
    }

    public boolean wasChosen() {
        return chosen;
    }

    public void setChosen(boolean wasChosen) {
        this.chosen = wasChosen;
    }

    public Encounter getEncounterImpact() {
        return encounterImpact;
    }

    public void setEncounterImpact(Encounter encounterImpact) {
        this.encounterImpact = encounterImpact;
    }
}
