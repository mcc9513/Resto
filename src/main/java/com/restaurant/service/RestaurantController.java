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

        ensureCSVFilesExist();

        loadMenu();
        loadInventory();
    }

    private void ensureCSVFilesExist() {
        File menuFile = new File("menu.csv");
        if (!menuFile.exists()) {
            createMenuCSV();
        }

        File inventoryFile = new File("inventory.csv");
        if (!inventoryFile.exists()) {
            createInventoryCSV();
        }
    }

    private void createMenuCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("menu.csv"))) {
            bw.write("Item Name,Price,Ingredients");
            bw.newLine();
            System.out.println("menu.csv created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating menu.csv: " + e.getMessage());
        }
    }

    private void createInventoryCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {
            bw.write("Item Name,Quantity,Threshold,Price");
            bw.newLine();
            // Add default inventory items or leave it empty for further addition
            // Mock data is left empty for future implementation
            System.out.println("inventory.csv created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating inventory.csv: " + e.getMessage());
        }
    }

    public void loadMenu() {
        try (BufferedReader br = new BufferedReader(new FileReader("menu.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Item Name")) continue; // Skip header line
                String[] data = line.split(",");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                List<String> ingredients = Arrays.asList(data[2].split(";"));
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
                if (line.startsWith("Item Name")) continue; // Skip header line
                String[] data = line.split(",");
                String itemName = data[0];
                int quantity = Integer.parseInt(data[1]);
                int threshold = Integer.parseInt(data[2]);
                double price = Double.parseDouble(data[3]);
                inventory.put(itemName, new InventoryItem(itemName, quantity, threshold, price));
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
                bw.write(item.getItemName() + "," + item.getQuantity() + "," + item.getThreshold() + "," + item.getPrice());
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
            InventoryItem inventoryItem = inventory.get(menuItem.getName());
            if (inventoryItem != null) {
                if (!inventoryItem.reduceStock(1)) {
                    allItemsAvailable = false;
                }
            } else {
                System.out.println("Inventory item for " + menuItem.getName() + " not found.");
                allItemsAvailable = false;
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
