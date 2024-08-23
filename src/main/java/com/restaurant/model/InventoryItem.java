package com.restaurant.model;

public class InventoryItem {

    private int itemId;
    private String itemName;
    private int quantity;
    private int threshold;
    private double price;

    // Constructor
    public InventoryItem() {}

    public InventoryItem(int itemId, String itemName, int quantity, int threshold, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.threshold = threshold;
        this.price = price;
    }

    public InventoryItem(String itemName, int quantity, int threshold, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.threshold = threshold;
        this.price = price;
    } // Not tested. Need to find its usage to test fully

    // Method to add stock
    public void addStock(int amount) {
        this.quantity += amount;
    }

    // Method to reduce stock of the item.
    public boolean reduceStock(int amount) {
        if (amount > this.quantity) {
            System.out.println("Insufficient Stock for " + itemName);
            return false;
        }
        this.quantity -= amount;
        checkThreshold();
        return true;
    }

    private void checkThreshold() {
        if (this.quantity <= this.threshold) {
            alertLowStock();
        }
    }

    // Alert method when stock is low
    private void alertLowStock() {
        System.out.println("Alert: Stock low for " + itemName + ". Current stock: " + quantity);
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Convert InventoryItem to CSV format
    public String toCSV() {
        return itemId + "," + itemName + "," + quantity + "," + threshold + "," + price;
    }

    // Create InventoryItem from CSV
    public static InventoryItem fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        int qty = Integer.parseInt(fields[2]);
        int thresh = Integer.parseInt(fields[3]);
        double prc = Double.parseDouble(fields[4]);

        return new InventoryItem(id, name, qty, thresh, prc);
    }
}




