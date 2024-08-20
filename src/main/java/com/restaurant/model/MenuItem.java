package com.restaurant.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItem implements Serializable {
    private String name;
    private String description;
    private int preparationTime;
    private double price;
    private List<String> ingredients;

    public MenuItem(String name, String description, int preparationTime, double price, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients); // Return a copy to preserve encapsulation
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = new ArrayList<>(ingredients); // Create a copy to preserve encapsulation
    }

}
