package com.restaurant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

class TableTest {

    private Table table;

    @BeforeEach
    void setUp() {
        // Initialize the Table object with example data
        table = new Table(1, "Open", "John Doe", LocalDate.of(2024, 8, 1), LocalTime.of(19, 30));
    }

    // Test the constructor and getters
    @Test
    void testConstructorAndGetters() {
        assertEquals(1, table.getTableId(), "Table ID should match the initialized value.");
        assertEquals("Open", table.getStatus(), "Status should match the initialized value.");
        assertEquals("John Doe", table.getCustomerName(), "Customer name should match the initialized value.");
        assertEquals(LocalDate.of(2024, 8, 1), table.getDate(), "Date should match the initialized value.");
        assertEquals(LocalTime.of(19, 30), table.getTime(), "Time should match the initialized value.");
    }

    // Test setting the status
    @Test
    void testSetStatus() {
        table.setStatus("Seated");
        assertEquals("Seated", table.getStatus(), "Status should be updated to the new value.");
    }

    // Test setting the customer name
    @Test
    void testSetCustomerName() {
        table.setCustomerName("Jane Doe");
        assertEquals("Jane Doe", table.getCustomerName(), "Customer name should be updated to the new value.");
    }

    // Test setting the date
    @Test
    void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 9, 15);
        table.setDate(newDate);
        assertEquals(newDate, table.getDate(), "Date should be updated to the new value.");
    }

    // Test setting the time
    @Test
    void testSetTime() {
        LocalTime newTime = LocalTime.of(18, 0);
        table.setTime(newTime);
        assertEquals(newTime, table.getTime(), "Time should be updated to the new value.");
    }

    // Test changing status from "Open" to "Reserved"
    @Test
    void testStatusChangeToReserved() {
        table.setStatus("Reserved");
        assertEquals("Reserved", table.getStatus(), "Table status should change to 'Reserved'.");
    }

    // Test changing status from "Reserved" to "Seated"
    @Test
    void testStatusChangeToSeated() {
        table.setStatus("Reserved");  // Start with "Reserved"
        table.setStatus("Seated");
        assertEquals("Seated", table.getStatus(), "Table status should change to 'Seated'.");
    }
}

