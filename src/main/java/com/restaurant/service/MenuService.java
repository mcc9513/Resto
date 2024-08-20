package com.restaurant.service;
import com.restaurant.model.MenuItem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems;
    private static final String FILE_NAME = "menu.dat";

    public MenuService() {
        this.menuItems = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeItem(String itemName) {
        menuItems.removeIf(item -> item.getName().equals(itemName));
    }

    public void editItem(String itemName, MenuItem newItem) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getName().equals(itemName)) {
                menuItems.set(i, newItem);
                break;
            }
        }
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems);
    }

    public void saveMenu() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(menuItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public MenuItem findItemByName(String name) {
        return menuItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : menuItems) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}