package com.restaurant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MenuItemTest {

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        // Create a list of ingredients for testing
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Beef");
        ingredients.add("Cheese");
        ingredients.add("Lettuce");

        // Initialize the MenuItem object with example data
        menuItem = new MenuItem("Burger", "Delicious beef burger", 15, 9.99, ingredients);
    }

    // Test the constructor and getters
    @Test
    void testConstructorAndGetters() {
        assertEquals("Burger", menuItem.getName(), "Name should match the initialized value.");
        assertEquals("Delicious beef burger", menuItem.getDescription(), "Description should match the initialized value.");
        assertEquals(15, menuItem.getPreparationTime(), "Preparation time should match the initialized value.");
        assertEquals(9.99, menuItem.getPrice(), 0.01, "Price should match the initialized value.");

        List<String> expectedIngredients = new ArrayList<>();
        expectedIngredients.add("Beef");
        expectedIngredients.add("Cheese");
        expectedIngredients.add("Lettuce");

        assertEquals(expectedIngredients, menuItem.getIngredients(), "Ingredients should match the initialized values.");
    }

    // Test setting and getting the name
    @Test
    void testSetName() {
        menuItem.setName("Cheeseburger");
        assertEquals("Cheeseburger", menuItem.getName(), "Name should be updated to the new value.");
    }

    // Test the immutability of ingredients list (modifying the list after getting it should not affect the original)
    @Test
    void testIngredientsImmutability() {
        List<String> ingredients = menuItem.getIngredients();
        ingredients.add("Tomato");  // Modify the list returned by getIngredients()

        // Verify that the original list inside MenuItem is not affected
        assertFalse(menuItem.getIngredients().contains("Tomato"), "Original ingredients list should not be affected by external changes.");
    }
}

