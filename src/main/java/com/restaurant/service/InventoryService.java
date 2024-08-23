package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import com.restaurant.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final String csvFilePath = "inventory.csv";

    public InventoryService() {
        // Create the CSV file if it doesn't exist
        File file = new File(csvFilePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new inventory item (writes to CSV)
    public boolean addInventoryItem(InventoryItem item) {
        List<InventoryItem> items = getAllInventoryItems(); // Load existing items
        item.setItemId(items.size() + 1); // Set a new ID
        items.add(item); // Add the new item

        return saveAllItemsToCSV(items); // Save the list back to CSV
    }

    // Method to update an existing inventory item (rewrites the CSV)
    public boolean updateInventoryItem(InventoryItem item) {
        List<InventoryItem> items = getAllInventoryItems();
        for (InventoryItem i : items) {
            if (i.getItemId() == item.getItemId()) {
                i.setItemName(item.getItemName());
                i.setQuantity(item.getQuantity());
                i.setThreshold(item.getThreshold());
                i.setPrice(item.getPrice());
                break;
            }
        }
        return saveAllItemsToCSV(items);
    }

    // Method to remove an inventory item by its ID
    public boolean removeInventoryItem(int itemId) {
        List<InventoryItem> items = getAllInventoryItems();
        items.removeIf(item -> item.getItemId() == itemId);
        return saveAllItemsToCSV(items);
    }

    // Method to retrieve all inventory items from CSV
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                InventoryItem item = InventoryItem.fromCSV(line);
                items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Save the list of inventory items back to CSV
    private boolean saveAllItemsToCSV(List<InventoryItem> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (InventoryItem item : items) {
                bw.write(item.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public InventoryItem getItemByName(String itemName) {
        List<InventoryItem> items = getAllInventoryItems();
        for (InventoryItem item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void reduceIngredientsForMenuItem(MenuItem menuItem) {
        List<InventoryItem> items = getAllInventoryItems();
        List<String> ingredients = menuItem.getIngredients();


        for (String ingredient : ingredients) {
            for (InventoryItem i : items) {
                if (i.getItemName().equalsIgnoreCase(ingredient)) {
                    i.setQuantity(i.getQuantity() - 1);
                    break;
                }
            }
//            InventoryItem inventoryItem = getItemByName(ingredient);
//
//            if (inventoryItem != null && inventoryItem.getQuantity() > 0) {
//                inventoryItem.setQuantity(inventoryItem.getQuantity() - 1);
//
//                // Optional: If quantity reaches 0, you could log it or notify the user
//                if (inventoryItem.getQuantity() <= 0) {
//                    System.out.println("Ingredient " + ingredient + " is out of stock.");
//                }
//            } else {
//                // Handle case where ingredient is not found or already at 0 quantity
//                System.out.println("Ingredient " + ingredient + " is not available or out of stock.");
//            }
        }

        // Save the updated inventory back to the CSV file
        saveAllItemsToCSV(items);
    }

}


