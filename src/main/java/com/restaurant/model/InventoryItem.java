package com.restaurant.model;
import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Table(name = "Inventory")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(name = "itemName", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "threshold", nullable = false)
    private int threshold;

    @Column(name = "price", nullable = false)
    private double price;

    //Constructor changed to create new InventoryItem
    public static InventoryItem InventoryItem(String itemName, int quantity, int threshold, double price) {
        InventoryItem item = new InventoryItem();
        item.setItemName(itemName);
        item.setQuantity(quantity);
        item.setThreshold(threshold);
        item.setPrice(price);
        return item;
    }


    public InventoryItem() {}

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

    public int getItemId() { return itemId; }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getPrice() { return price; }

    private void setPrice(double price) { this.price = price; }
}

