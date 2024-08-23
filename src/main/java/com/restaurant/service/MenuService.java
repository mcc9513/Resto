package com.restaurant.service;

import com.restaurant.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the restaurant's menu items, stored in a CSV file.
 * Supports adding, editing, and deleting items, with changes saved between sessions.
 */
public class MenuService {
    private static final String MENU_FILE = "Menu.csv";

    // Adds a new MenuItem and saves it to the CSV file.
    public void addMenuItem(MenuItem item) throws IOException {
        List<MenuItem> items = loadMenuItems();
        items.add(item);
        saveMenuItems(items);
    }

    // Edits an existing MenuItem identified by its name and saves the changes.
    public void editMenuItem(String name, MenuItem updatedItem) throws IOException {
        List<MenuItem> items = loadMenuItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.set(i, updatedItem);
                break;
            }
        }
        saveMenuItems(items);
    }

    // Deletes a MenuItem identified by its name and updates the CSV file.
    public void deleteMenuItem(String name) throws IOException {
        List<MenuItem> items = loadMenuItems();
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
        saveMenuItems(items);
    }

    // Loads all MenuItem objects from the CSV file.
    public List<MenuItem> loadMenuItems() throws IOException {
        List<MenuItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(convertCsvToMenuItem(line));
            }
        }
        return items;
    }

    // Saves the list of MenuItem objects to the CSV file.
    private void saveMenuItems(List<MenuItem> items) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : items) {
                writer.write(convertMenuItemToCsv(item));
                writer.newLine();
            }
        }
    }

    // Converts a MenuItem object to a CSV format string.
    private String convertMenuItemToCsv(MenuItem item) {
        return String.format("%s,%s,%d,%.2f,%s",
                item.getName(),
                item.getDescription(),
                item.getPreparationTime(),
                item.getPrice(),
                String.join(";", item.getIngredients()));
    }

    // Converts a CSV format string to a MenuItem object.
    private MenuItem convertCsvToMenuItem(String csvLine) {
        String[] fields = csvLine.split(",");
        String name = fields[0].trim(); // Name of the item
        String description = fields[1].trim(); // Description of the item
        int preparationTime = Integer.parseInt(fields[2].trim()); // Preparation time should be an integer
        double price = Double.parseDouble(fields[3].trim()); // Price should be parsed as a double
        List<String> ingredients = List.of(fields[4].trim().split("\\s*;\\s*")); // Ingredients split by semicolon
        return new MenuItem(name, description, preparationTime, price, ingredients);
    }

}
