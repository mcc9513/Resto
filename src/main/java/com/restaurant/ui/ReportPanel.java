package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JTextArea reportArea;
    private JButton generateButton, exportButton;

    public ReportPanel() {
        setLayout(new BorderLayout());

        // Text area for report display
        reportArea = new JTextArea(20, 40);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Buttons for generating and exporting reports
        generateButton = new JButton("Generate Report");
        exportButton = new JButton("Export Report");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);

        // Add components to panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
