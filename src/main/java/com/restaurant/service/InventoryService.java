package com.restaurant.service;


import com.restaurant.model.InventoryItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class InventoryService {

    private SessionFactory sessionFactory;

    // Constructor to establish a Hibernate session factory
    public InventoryService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(InventoryItem.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    // Method to add a new inventory item
    public void addInventoryItem(InventoryItem item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            System.out.println("Inventory item added: " + item.getItemName());
        }
    }

    // Method to update an existing inventory item
    public void updateInventoryItem(InventoryItem item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
            System.out.println("Inventory item updated: " + item.getItemName());
        }
    }

    // Method to remove an inventory item by its ID
    public void removeInventoryItem(int itemId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            InventoryItem item = session.get(InventoryItem.class, itemId);
            if (item != null) {
                session.delete(item);
                transaction.commit();
                System.out.println("Inventory item removed: ID " + itemId);
            } else {
                System.out.println("Inventory item not found: ID " + itemId);
            }
        }
    }

    // Method to retrieve a specific inventory item by its ID
    public InventoryItem getInventoryItem(int itemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InventoryItem.class, itemId);
        }
    }

    // Method to retrieve all inventory items
    @SuppressWarnings("unchecked")
    public List<InventoryItem> getAllInventoryItems() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM InventoryItem").list();
        }
    }

    // Method to search for inventory items by keyword
    @SuppressWarnings("unchecked")
    public List<InventoryItem> searchInventoryItems(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM InventoryItem WHERE name LIKE :keyword")
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }

    // Method to reduce the quantity of an inventory item
    public void reduceInventoryItemQuantity(int itemId, int quantity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            InventoryItem item = session.get(InventoryItem.class, itemId);
            if (item != null && item.getQuantity() >= quantity) {
                item.setQuantity((int) (item.getQuantity() - quantity));
                session.update(item);
                transaction.commit();
                System.out.println("Inventory item quantity reduced: ID " + itemId + " by " + quantity);
            } else {
                System.out.println("Unable to reduce quantity. Inventory item not found or insufficient quantity: ID " + itemId);
            }
        }
    }

    // Method to increase the quantity of an inventory item
    public void increaseInventoryItemQuantity(int itemId, int quantity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            InventoryItem item = session.get(InventoryItem.class, itemId);
            if (item != null) {
                item.setQuantity((int) (item.getQuantity() + quantity));
                session.update(item);
                transaction.commit();
                System.out.println("Inventory item quantity increased: ID " + itemId + " by " + quantity);
            } else {
                System.out.println("Inventory item not found: ID " + itemId);
            }
        }
    }

    // Method to generate an inventory report (could be expanded to save to a file, etc.)
    public void generateInventoryReport() {
        List<InventoryItem> items = getAllInventoryItems();
        System.out.println("Inventory Report:");
        for (InventoryItem item : items) {
            System.out.println(item);
        }
    }

    // Close sessionFactory when done
    public void close() {
        sessionFactory.close();
    }


}

