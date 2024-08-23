package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;
import org.junit.jupiter.api.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private static final String TEST_CSV_FILE = "Resto/test_users.csv";

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        userService = new UserService();

        // Use reflection to set the csvFilePath field in UserService
        Field csvFilePathField = UserService.class.getDeclaredField("csvFilePath"); // Reference the field name
        csvFilePathField.setAccessible(true);
        csvFilePathField.set(userService, TEST_CSV_FILE);

        // Verify that the csvFilePath was correctly updated
        assertEquals(TEST_CSV_FILE, csvFilePathField.get(userService));
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testRegisterUser() {
        User user = new User(1, "testuser", "password", "Test U", "Staff", 0);
        boolean result = userService.registerUser(user);
        assertTrue(result);

        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());

        // Try to register the same user again
        result = userService.registerUser(user);
        assertFalse(result);
    }

    @Test
    void testUpdateUser() {
        User user = new User(1, "testuser", "password", "Test U", "Staff", 0);
        userService.registerUser(user);

        User updatedUser = new User(1, "testuser", "newpassword", "Test U", "Manager", 10);
        boolean result = userService.updateUser(updatedUser);
        assertTrue(result);

        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("Manager", users.get(0).getRole());
        assertEquals(10, users.get(0).getHoursWorked());
    }

    @Test
    void testRemoveUser() {
        User user = new User(1, "testuser", "password", "Test U", "Staff", 0);
        userService.registerUser(user);

        boolean result = userService.removeUser(1);
        assertTrue(result);

        List<User> users = userService.getAllUsers();
        assertEquals(0, users.size());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1, "user1", "password1", "User O", "Staff", 0);
        User user2 = new User(2, "user2", "password2", "User T", "Manager", 0);
        userService.registerUser(user1);
        userService.registerUser(user2);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
    }

    @Test
    void testPasswordHashing() {
        User user = new User(1, "testuser", "password", "TestU", "Staff", 0);
        userService.registerUser(user);

        List<User> users = userService.getAllUsers();
        User savedUser = users.get(0);

        assertNotEquals("password", savedUser.getPasswordHash());
        assertTrue(HashUtil.verifyPassword("password", savedUser.getPasswordHash()));
    }

    // New Edge Case Tests

    @Test
    void testRegisterUserWithEmptyUsername() {
        User user = new User(1, "", "password", "Test U", "Staff", 0);
        boolean result = userService.registerUser(user);
        assertFalse(result, "User with empty username should not be allowed to register");
    }

    @Test
    void testUpdateNonExistentUser() {
        User nonExistentUser = new User(999, "nonexistent", "password", "Test U", "Staff", 0);
        boolean result = userService.updateUser(nonExistentUser);
        assertFalse(result, "Should not be able to update a non-existent user");
    }

    @Test
    void testDeleteNonExistentUser() {
        boolean result = userService.removeUser(999);  // ID that doesn't exist
        assertFalse(result, "Should not be able to delete a non-existent user");
    }
}

