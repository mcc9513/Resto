package com.restaurant.ui;

import javax.swing.*;

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Assuming MainMenuPanel is the starting point after login
        MainMenuPanel mainMenu = new MainMenuPanel();
        frame.add(mainMenu);

        // Show the frame
        frame.setVisible(true);
    }
}

