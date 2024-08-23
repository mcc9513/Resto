package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dish on the restaurant's menu.
 */
public class MenuItem {
    private String name;
    private String description;
    private int preparationTime;  // Assumed to be in minutes
    private double price;
    private List<String> ingredients;

    // Constructor to create a new MenuItem
    public MenuItem(String name, String description, int preparationTime, double price, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public MenuItem(String name, double price, List<String> ingredients ) {
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<>(ingredients);
    }

    // Getter methods
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
        return new ArrayList<>(ingredients);
    }

    // Setter methods
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
        this.ingredients = new ArrayList<>(ingredients);
    }
}
