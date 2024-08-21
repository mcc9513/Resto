package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JTextArea reportArea;
    private JButton generateButton, exportButton, mainButton, logOutButton;

    public ReportPanel() {
        setLayout(new BorderLayout());

        // Report Area
        reportArea = new JTextArea(20, 40);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Buttons
        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Report");
        exportButton = new JButton("Export Report");
        mainButton = new JButton("Home");
        logOutButton = new JButton("Log Out");
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(logOutButton);

        // Add components to panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

