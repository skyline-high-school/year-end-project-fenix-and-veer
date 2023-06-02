package com.example.project;

public class FoodItem {
    private String name;
    private int hungerImp;

    public FoodItem(String name, int hungerImp) {
        this.name = name;
        this.hungerImp = hungerImp;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getHungerImp() {
        return hungerImp;
    }

    public void setHungerImp(int hungerImp) {
        this.hungerImp = hungerImp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
