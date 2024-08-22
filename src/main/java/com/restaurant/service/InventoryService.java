package com.restaurant.service;

import com.restaurant.model.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final String csvFilePath = "Resto/inventory.csv";

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

    // Method to add an item directly to the CSV (for addItemToCSV functionality)
    public static boolean addItemToCSV(InventoryItem item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv", true))) {
            bw.write(item.toCSV());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

    // Get the total quantity of items in the inventory
    public int getTotalInventoryCount() {
        List<InventoryItem> items = getAllInventoryItems();
        int totalQuantity = 0;
        for (InventoryItem item : items) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    // Get the total value of the inventory (sum of price * quantity)
    public double getTotalInventoryValue() {
        List<InventoryItem> items = getAllInventoryItems();
        double totalValue = 0;
        for (InventoryItem item : items) {
            totalValue += item.getQuantity() * item.getPrice();
        }
        return totalValue;
    }

    // Get an inventory item by its name
    public InventoryItem getInventoryItemByName(String itemName) {
        List<InventoryItem> items = getAllInventoryItems();
        for (InventoryItem item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
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
}


