package com.restaurant.service;

import com.restaurant.model.Table;
import com.restaurant.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TableServiceTest {
    private TableService tableService;

    @Mock
    private Table table;
    @Mock
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tableService = new TableService();

        // Initialize the tables in the service
        tableService.addTable(1, 4); // Assume this sets status to "Available" by default
        when(table.getTableId()).thenReturn(1);
        tableService.tables.put(1, table);
    }

    @Test
    void assignTableShouldOccupyWhenAvailable() {
        when(table.getStatus()).thenReturn("Available");
        tableService.assignTable(1, order);
        verify(table, times(1)).occupy(order);
    }

    @Test
    void assignTableShouldThrowWhenNotAvailable() {
        when(table.getStatus()).thenReturn("Occupied");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tableService.assignTable(1, order);
        });
        assertEquals("Table is not available or does not exist.", exception.getMessage());
    }

    @Test
    void freeTableShouldFreeWhenOccupied() {
        when(table.getStatus()).thenReturn("Occupied");
        tableService.freeUpTable(1);
        verify(table, times(1)).freeUp();
    }

    @Test
    void freeTableShouldThrowWhenAlreadyFree() {
        when(table.getStatus()).thenReturn("Available");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tableService.freeUpTable(1);
        });
        assertEquals("Table is already free or does not exist.", exception.getMessage());
    }

    @Test
    void updateTableStatusShouldChangeStatus() {
        tableService.updateTableStatus(1, "Reserved");
        verify(table, times(1)).setStatus("Reserved");
    }

    @Test
    void areAnyTablesAvailableReturnsTrueWhenAvailable() {
        when(table.getStatus()).thenReturn("Available");
        assertTrue(tableService.areAnyTablesAvailable());
    }

    @Test
    void areAnyTablesAvailableReturnsFalseWhenNoAvailable() {
        when(table.getStatus()).thenReturn("Occupied");
        assertFalse(tableService.areAnyTablesAvailable());
    }
}


