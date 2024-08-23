//package com.restaurant.service;
//
//import com.restaurant.model.Table;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TableServiceTest {
//
//    private TableService tableService;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize TableService and prepopulate tables
//        tableService = new TableService();
//    }
//
//    // Test getting all tables
//    @Test
//    void testGetAllTables() {
//        List<Table> tables = tableService.getAllTables();
//        assertEquals(13, tables.size(), "There should be 13 tables initialized.");
//        assertEquals("Open", tables.get(0).getStatus(), "All tables should initially be 'Open'.");
//    }
//
//    // Test assigning a customer to a table
//    @Test
//    void testAssignCustomer() {
//        tableService.assignCustomer(1, "John Doe");
//        Table table = tableService.getAllTables().get(0);  // Table ID 1 is at index 0
//        assertEquals("John Doe", table.getCustomerName(), "Customer name should be assigned to the table.");
//    }
//
//    // Test changing table status
//    @Test
//    void testChangeTableStatus() {
//        tableService.changeTableStatus(1, "Reserved");
//        Table table = tableService.getAllTables().get(0);  // Table ID 1 is at index 0
//        assertEquals("Reserved", table.getStatus(), "Table status should be updated to 'Reserved'.");
//    }
//
//    // Test clearing a table
//    @Test
//    void testClearTable() {
//        // First, assign a customer and change status
//        tableService.assignCustomer(1, "John Doe");
//        tableService.changeTableStatus(1, "Seated");
//
//        // Now, clear the table
//        tableService.clearTable(1);
//        Table table = tableService.getAllTables().get(0);  // Table ID 1 is at index 0
//        assertEquals("Open", table.getStatus(), "Table status should be reset to 'Open'.");
//        assertNull(table.getCustomerName(), "Customer name should be cleared.");
//        assertNull(table.getDate(), "Reservation date should be cleared.");
//        assertNull(table.getTime(), "Reservation time should be cleared.");
//    }
//
//    // Test changing table status with reservation details
//    @Test
//    void testChangeTableStatusWithReservation() {
//        LocalDate reservationDate = LocalDate.of(2024, 8, 23);
//        LocalTime reservationTime = LocalTime.of(19, 0);
//
//        tableService.changeTableStatus(1, "Reserved", reservationDate, reservationTime);
//        Table table = tableService.getAllTables().get(0);  // Table ID 1 is at index 0
//
//        assertEquals("Reserved", table.getStatus(), "Table status should be updated to 'Reserved'.");
//        assertEquals(reservationDate, table.getDate(), "Reservation date should be set.");
//        assertEquals(reservationTime, table.getTime(), "Reservation time should be set.");
//    }
//}
//
