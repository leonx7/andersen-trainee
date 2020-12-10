package com.andersen.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse;

    @BeforeEach
    public void initialize() {
        warehouse = new Warehouse();
    }

    @Test
    void add() {
        warehouse.add(4,20);
        assertTrue(warehouse.getItems().containsKey(4));
        assertEquals(20, warehouse.getItems().get(4));
    }
}