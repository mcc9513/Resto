package com.restaurant.service;

import com.restaurant.model.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the MenuService class.
 * It tests various scenarios related to menu item management.
 */
class MenuServiceTest {
    private MenuService menuService;

    /**
     * This method is run before each test.
     * It sets up a fresh MenuService instance for each test to ensure isolation.
     */
    @BeforeEach
    void setUp() {
        menuService = new MenuService();
    }

    /**
     * Tests adding items to the menu and retrieving all items.
     * Verifies that added items are correctly stored and can be retrieved.
     */
    @Test
    void testAddAndGetAllItems() {
        MenuItem item1 = new MenuItem("Pasta", "Delicious pasta", 15, 12.99, Arrays.asList("Pasta", "Sauce"));
        MenuItem item2 = new MenuItem("Salad", "Fresh salad", 5, 8.99, Arrays.asList("Lettuce", "Tomato"));

        menuService.addItem(item1);
        menuService.addItem(item2);

        List<MenuItem> items = menuService.getAllItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    /**
     * Tests removing an item from the menu.
     * Verifies that the item is successfully removed after addition.
     */
    @Test
    void testRemoveItem() {
        MenuItem item = new MenuItem("Burger", "Juicy burger", 10, 9.99, Arrays.asList("Bun", "Patty"));
        menuService.addItem(item);
        menuService.removeItem("Burger");

        List<MenuItem> items = menuService.getAllItems();
        assertEquals(0, items.size());
    }

    /**
     * Tests editing an existing menu item.
     * Verifies that the item's details are correctly updated.
     */
    @Test
    void testEditItem() {
        MenuItem oldItem = new MenuItem("Pizza", "Cheesy pizza", 20, 14.99, Arrays.asList("Dough", "Cheese"));
        menuService.addItem(oldItem);

        MenuItem newItem = new MenuItem("Pizza", "Veggie pizza", 25, 15.99, Arrays.asList("Dough", "Vegetables"));
        menuService.editItem("Pizza", newItem);

        MenuItem updatedItem = menuService.findItemByName("Pizza");
        assertEquals("Veggie pizza", updatedItem.getDescription());
        assertEquals(25, updatedItem.getPreparationTime());
        assertEquals(15.99, updatedItem.getPrice(), 0.01);
    }

    /**
     * Tests finding a menu item by its name.
     * Verifies that the correct item is retrieved based on the name.
     */
    @Test
    void testFindItemByName() {
        MenuItem item = new MenuItem("Sushi", "Fresh sushi", 30, 18.99, Arrays.asList("Rice", "Fish"));
        menuService.addItem(item);

        MenuItem foundItem = menuService.findItemByName("Sushi");
        assertNotNull(foundItem);
        assertEquals("Sushi", foundItem.getName());
    }

    /**
     * Tests saving the menu to a file and then loading it.
     * Verifies that the loaded menu contains the same items as the saved menu.
     */
    @Test
    void testSaveAndLoadMenu() {
        MenuItem item = new MenuItem("Steak", "Grilled steak", 25, 24.99, Arrays.asList("Beef", "Spices"));
        menuService.addItem(item);

        menuService.saveMenu();

        MenuService newMenuService = new MenuService();
        newMenuService.loadMenu();

        List<MenuItem> loadedItems = newMenuService.getAllItems();
        assertEquals(1, loadedItems.size());
        assertEquals("Steak", loadedItems.get(0).getName());
    }
}