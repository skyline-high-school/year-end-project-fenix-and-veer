package com.example.project;

public class Choice {
    //choices for the Encounter class

    private String text;
    private int hpImpact;
    private int hungerImpact;
    private int thirstImpact;
    private int bodyTempImpact;
    private Object invAdd; //object to be added to the inventory as an impact of the choice (if selected)
    private boolean chosen; //true if the user chose this choice (for other choice consequences that depend on what they chose here)

    public Choice(String text) {
        this.text = text;
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

    public int getBodyTempImpact() {
        return bodyTempImpact;
    }

    public void setBodyTempImpact(int bodyTempImpact) {
        this.bodyTempImpact = bodyTempImpact;
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
}
