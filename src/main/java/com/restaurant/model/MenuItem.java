package com.restaurant.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MenuItem class represents a dish on the restaurant's menu.
 * It implements Serializable to allow for easy saving and loading of menu items.
 */
public class MenuItem implements Serializable {
    private String name;
    private String description;
    private int preparationTime;  // Assumed to be in minutes
    private double price;
    private List<String> ingredients;

    /**
     * Constructor for creating a new MenuItem.
     *
     * @param name The name of the dish
     * @param description A brief description of the dish
     * @param preparationTime The time it takes to prepare the dish (in minutes)
     * @param price The price of the dish
     * @param ingredients A list of ingredients used in the dish
     */
    public MenuItem(String name, String description, int preparationTime, double price, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getter methods

    /**
     * @return The name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of the dish
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The preparation time of the dish (in minutes)
     */
    public int getPreparationTime() {
        return preparationTime;
    }

    /**
     * @return The price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return A copy of the list of ingredients
     * Note: A new ArrayList is returned to preserve encapsulation
     */
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    // Setter methods

    /**
     * @param name The new name for the dish
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description The new description for the dish
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param preparationTime The new preparation time for the dish (in minutes)
     */
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * @param price The new price for the dish
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param ingredients The new list of ingredients for the dish
     * Note: A new ArrayList is created to preserve encapsulation
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }
}