package com.restaurant.service;

import com.restaurant.model.MenuItem;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    private MenuService menuService;
    private static final String TEST_MENU_FILE = "TestMenu.csv";

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        menuService = new MenuService();
        // Use reflection to set the test file
        java.lang.reflect.Field field = MenuService.class.getDeclaredField("MENU_FILE");
        field.setAccessible(true);
        field.set(null, TEST_MENU_FILE);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_MENU_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddMenuItem() throws IOException {
        MenuItem item = new MenuItem("Test Item", "Test Description", 10, 9.99, Arrays.asList("Ingredient1", "Ingredient2"));
        menuService.addMenuItem(item);

        List<MenuItem> items = menuService.loadMenuItems();
        assertEquals(1, items.size());
        assertEquals("Test Item", items.get(0).getName());
    }

    @Test
    void testEditMenuItem() throws IOException {
        MenuItem item = new MenuItem("Original Item", "Original Description", 10, 9.99, Arrays.asList("Ingredient1", "Ingredient2"));
        menuService.addMenuItem(item);

        MenuItem updatedItem = new MenuItem("Original Item", "Updated Description", 15, 14.99, Arrays.asList("Ingredient1", "Ingredient2", "Ingredient3"));
        menuService.editMenuItem("Original Item", updatedItem);

        List<MenuItem> items = menuService.loadMenuItems();
        assertEquals(1, items.size());
        assertEquals("Updated Description", items.get(0).getDescription());
        assertEquals(15, items.get(0).getPreparationTime());
        assertEquals(14.99, items.get(0).getPrice(), 0.01);
    }

    @Test
    void testDeleteMenuItem() throws IOException {
        MenuItem item1 = new MenuItem("Item 1", "Description 1", 10, 9.99, Arrays.asList("Ingredient1", "Ingredient2"));
        MenuItem item2 = new MenuItem("Item 2", "Description 2", 15, 14.99, Arrays.asList("Ingredient3", "Ingredient4"));
        menuService.addMenuItem(item1);
        menuService.addMenuItem(item2);

        menuService.deleteMenuItem("Item 1");

        List<MenuItem> items = menuService.loadMenuItems();
        assertEquals(1, items.size());
        assertEquals("Item 2", items.get(0).getName());
    }

    @Test
    void testLoadMenuItems() throws IOException {
        MenuItem item1 = new MenuItem("Item 1", "Description 1", 10, 9.99, Arrays.asList("Ingredient1", "Ingredient2"));
        MenuItem item2 = new MenuItem("Item 2", "Description 2", 15, 14.99, Arrays.asList("Ingredient3", "Ingredient4"));
        menuService.addMenuItem(item1);
        menuService.addMenuItem(item2);

        List<MenuItem> loadedItems = menuService.loadMenuItems();
        assertEquals(2, loadedItems.size());
        assertEquals("Item 1", loadedItems.get(0).getName());
        assertEquals("Item 2", loadedItems.get(1).getName());
    }

    @Test
    void testMenuItemConversion() throws IOException {
        MenuItem originalItem = new MenuItem("Test Item", "Test Description", 10, 9.99, Arrays.asList("Ingredient1", "Ingredient2"));
        menuService.addMenuItem(originalItem);

        List<MenuItem> loadedItems = menuService.loadMenuItems();
        MenuItem loadedItem = loadedItems.get(0);

        assertEquals(originalItem.getName(), loadedItem.getName());
        assertEquals(originalItem.getDescription(), loadedItem.getDescription());
        assertEquals(originalItem.getPreparationTime(), loadedItem.getPreparationTime());
        assertEquals(originalItem.getPrice(), loadedItem.getPrice(), 0.01);
        assertEquals(originalItem.getIngredients(), loadedItem.getIngredients());
    }
}