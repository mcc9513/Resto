package com.restaurant.ui;

import com.restaurant.model.InventoryItem;
import com.restaurant.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryManagementPanelTest {

    private InventoryService inventoryService;
    private InventoryManagementPanel inventoryManagementPanel;

    @BeforeEach
    void setUp() {
        // Mock InventoryService
        inventoryService = mock(InventoryService.class);

        // Initialize InventoryManagementPanel with the mocked service
        inventoryManagementPanel = new InventoryManagementPanel(inventoryService);
    }

    // Test loading inventory data into the table
    @Test
    void testLoadInventoryData() {
        // Set up mock inventory items
        InventoryItem item1 = new InventoryItem(1, "Apple", 100, 25, 1.99);
        InventoryItem item2 = new InventoryItem(2, "Orange", 50, 10, 0.99);
        List<InventoryItem> inventory = Arrays.asList(item1, item2);

        // Mock getAllInventoryItems method to return the mock inventory
        when(inventoryService.getAllInventoryItems()).thenReturn(inventory);

        // Load the data into the table
        inventoryManagementPanel.loadInventoryData();

        // Get the table model from the panel
        JTable table = (JTable) ((JScrollPane) inventoryManagementPanel.getComponent(0)).getViewport().getView();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        // Verify that the data was loaded correctly into the table
        assertEquals(2, tableModel.getRowCount(), "Table should have 2 rows.");
        assertEquals("Apple", tableModel.getValueAt(0, 1), "First item should be 'Apple'.");
        assertEquals("Orange", tableModel.getValueAt(1, 1), "Second item should be 'Orange'.");
    }

    // Test adding a new item
    @Test
    void testAddItem() {
        // Set up user input for adding a new item
        String itemName = "Banana";
        String quantityStr = "150";
        String thresholdStr = "30";
        String priceStr = "1.50";

        // Mock input dialogs
        mockStatic(JOptionPane.class);
        when(JOptionPane.showInputDialog("Enter item name:")).thenReturn(itemName);
        when(JOptionPane.showInputDialog("Enter quantity:")).thenReturn(quantityStr);
        when(JOptionPane.showInputDialog("Enter threshold:")).thenReturn(thresholdStr);
        when(JOptionPane.showInputDialog("Enter price:")).thenReturn(priceStr);

        // Trigger the add item action
        inventoryManagementPanel.addItem();

        // Verify that the new item was added to the inventory
        InventoryItem expectedItem = new InventoryItem(itemName, 150, 30, 1.50);
        verify(inventoryService, times(1)).addInventoryItem(expectedItem);

        // Verify that the table data was reloaded
        verify(inventoryService, times(1)).getAllInventoryItems();
    }

    // Test deleting an item
    @Test
    void testDeleteItem() {
        // Set up mock inventory with two items
        InventoryItem item1 = new InventoryItem(1, "Apple", 100, 25, 1.99);
        InventoryItem item2 = new InventoryItem(2, "Orange", 50, 10, 0.99);
        List<InventoryItem> inventory = Arrays.asList(item1, item2);

        // Mock getAllInventoryItems method to return the mock inventory
        when(inventoryService.getAllInventoryItems()).thenReturn(inventory);

        // Load data into the table and select the first row
        inventoryManagementPanel.loadInventoryData();
        JTable table = (JTable) ((JScrollPane) inventoryManagementPanel.getComponent(0)).getViewport().getView();
        table.setRowSelectionInterval(0, 0);  // Select the first row

        // Trigger the delete item action
        inventoryManagementPanel.deleteItem();

        // Verify that the item with ID 1 was removed from the inventory
        verify(inventoryService, times(1)).removeInventoryItem(1);

        // Verify that the table data was reloaded
        verify(inventoryService, times(2)).getAllInventoryItems();  // One for loading, one for reloading
    }
}

