package com.restaurant.util;

public class PasswordHashTest {
    public static void main(String[] args) {
        String password = "password123";  // Change this to the password you want to hash
        String hashedPassword = HashUtil.hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
