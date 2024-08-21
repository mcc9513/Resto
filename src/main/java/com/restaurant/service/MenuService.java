package com.restaurant.service;
import com.restaurant.model.MenuItem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MenuService class manages the restaurant's menu items.
 * It provides functionality to add, remove, edit, and retrieve menu items,
 * as well as save and load the menu from a file.
 */
public class MenuService {
    private List<MenuItem> menuItems;
    private static final String FILE_NAME = "menu.dat";

    /**
     * Constructor initializes an empty list of menu items.
     */
    public MenuService() {
        this.menuItems = new ArrayList<>();
    }

    /**
     * Adds a new item to the menu.
     * @param item The MenuItem to be added
     */
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    /**
     * Removes an item from the menu based on its name.
     * @param itemName The name of the item to be removed
     */
    public void removeItem(String itemName) {
        menuItems.removeIf(item -> item.getName().equals(itemName));
    }

    /**
     * Edits an existing menu item.
     * @param itemName The name of the item to be edited
     * @param newItem The new MenuItem to replace the old one
     */
    public void editItem(String itemName, MenuItem newItem) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getName().equals(itemName)) {
                menuItems.set(i, newItem);
                break;
            }
        }
    }

    /**
     * Retrieves all menu items.
     * @return A new ArrayList containing all menu items
     */
    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems);
    }

    /**
     * Saves the current menu to a file.
     * Uses Java serialization to write the list of MenuItems.
     */
    public void saveMenu() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(menuItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the menu from a file.
     * If the file exists, it uses Java deserialization to read the list of MenuItems.
     */
    public void loadMenu() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                menuItems = (List<MenuItem>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Finds a menu item by its name.
     * @param name The name of the item to find
     * @return The MenuItem if found, or null if not found
     */
    public MenuItem findItemByName(String name) {
        return menuItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Provides a string representation of all menu items.
     * @return A string containing details of all menu items
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : menuItems) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}