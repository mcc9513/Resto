package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import com.restaurant.model.MenuItem;

import java.io.*;
import java.util.*;

public class RestaurantController {
    private Map<String, InventoryItem> inventory;
    private List<MenuItem> menu;

    public RestaurantController() {
        this.inventory = new HashMap<>();
        this.menu = new ArrayList<>();

        // Ensure the necessary CSV files exist before loading data
        ensureCSVFilesExist();

        // Load the data after ensuring the files are created
        loadMenu();
        loadInventory();
    }

    private void ensureCSVFilesExist() {
        // Ensure menu.csv exists
        File menuFile = new File("Menu.csv");
        if (!menuFile.exists()) {
            System.out.println("Menu.csv does not exist, please create it.");
        }

        // Ensure inventory.csv exists
        File inventoryFile = new File("inventory.csv");
        if (!inventoryFile.exists()) {
            System.out.println("Inventory.csv does not exist, please create it.");
        }
    }

    public void loadMenu() {
        try (BufferedReader br = new BufferedReader(new FileReader("Menu.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Item Name")) continue; // Skip header line
                String[] data = line.split(",");
                String name = data[0];
                double price = Double.parseDouble(data[3]);
                List<String> ingredients = Arrays.asList(data[4].split(";"));
                menu.add(new MenuItem(name, price, ingredients));
            }
        } catch (IOException e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
    }

    public void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Item ID")) continue; // Skip header line
                InventoryItem item = InventoryItem.fromCSV(line);
                inventory.put(item.getItemName(), item);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void saveInventory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {
            // Write headers
            bw.write("Item Name,Quantity,Threshold,Price");
            bw.newLine();
            for (InventoryItem item : inventory.values()) {
                bw.write(item.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public InventoryItem getInventoryItem(String itemName) {
        return inventory.get(itemName);
    }

    public boolean placeOrder(List<MenuItem> orderedItems) {
        boolean allItemsAvailable = true;

        for (MenuItem menuItem : orderedItems) {
            for (String ingredient : menuItem.getIngredients()) {
                InventoryItem inventoryItem = inventory.get(ingredient);
                if (inventoryItem != null) {
                    if (!inventoryItem.reduceStock(1)) {
                        allItemsAvailable = false;
                    }
                } else {
                    System.out.println("Inventory item for " + ingredient + " not found.");
                    allItemsAvailable = false;
                }
            }
        }

        if (allItemsAvailable) {
            saveInventory();
        }

        return allItemsAvailable;
    }

    public void manageInventory(String itemName, int quantityToAdd) {
        InventoryItem inventoryItem = inventory.get(itemName);

        if (inventoryItem != null) {
            inventoryItem.addStock(quantityToAdd);
            saveInventory();
        } else {
            System.out.println("Inventory item not found.");
        }
    }
}
