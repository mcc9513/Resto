package com.restaurant.model;

public class InventoryItem {
    private String itemName;
    private double quantity;
    private double alertThreshold;

    //Constructor
    public InventoryItem(String itemName, double quantity, double alertThreshold) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.alertThreshold = alertThreshold;
    }

    public InventoryItem() {

    }

    // Method to add stock
    public void addStock(double amount) {
        this.quantity += amount;
    }

    // Method to reduce stock of the item.
    public boolean reduceStock(double amount) {
        if (amount > this.quantity) {
            System.out.println("Insufficient Stock for " + itemName);
            return false;
        }
        this.quantity -= amount;
        checkThreshold();
        return true;
    }

    private void checkThreshold() {
        if (this.quantity <= this.alertThreshold) {
            alertLowStock();
        }
    }

    // Alert method when stock is low
    private void alertLowStock() {
        System.out.println("Alert: Stock low for " + itemName + ". Current stock: " + quantity);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(double alertThreshold) {
        this.alertThreshold = alertThreshold;
    }
}
