package com.andersen.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HashMapTest {
    private static final HashMap<String, Integer> hm = new HashMap<>();

    @BeforeAll
    public static void initialize() {
        initVar();
    }

    @Test
    @Order(1)
    void size() {
        assertEquals(10, hm.size());
    }

    @Test
    @Order(2)
    void isEmpty() {
        assertFalse(hm.isEmpty());
    }

    @Test
    @Order(3)
    void put() {
        hm.put("10", 10);
        assertEquals(11, hm.size());
        assertEquals(10, hm.get("10"));

        hm.put("10", null);
        assertEquals(11, hm.size());
        assertNull(hm.get("10"));

        hm.put(null, 10);
        assertEquals(12, hm.size());
        assertEquals(10, hm.get(null));
    }

    @Test
    @Order(4)
    void clear() {
        hm.clear();
        assertEquals(0, hm.size());
        for (int i = 0; i < hm.size(); i++)
            assertNull(hm.getTable()[i]);
    }

    @Test
    @Order(5)
    void get() {
        hm.put("11", 11);
        assertEquals(11, hm.get("11"));
    }

    @Test
    @Order(6)
    void keySet() {
        hm.clear();
        initVar();
        assertEquals(10, hm.keySet().size());
        for (int i = 0; i < 10; i++) {
            assertTrue(hm.keySet().contains(Integer.toString(i)));
        }

    }

    @Test
    @Order(7)
    void getValues() {
        assertEquals(10, hm.getValues().size());
        for (int i = 0; i < 10; i++) {
            assertTrue(hm.getValues().contains(i));
        }
    }

    @Test
    @Order(8)
    void entrySet() {
        assertEquals(10, hm.entrySet().size());

        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (HashMap.Entry<String, Integer> entry : hm.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(keys.contains(Integer.toString(i)));
            assertTrue(values.contains(i));
        }
    }

    @Test
    @Order(9)
    void remove() {

        hm.put(null, 333);
        assertEquals(11, hm.size());
        hm.remove(null);
        assertEquals(10, hm.size());

    }

    private static void initVar() {
        for (int i = 0; i < 10; i++) {
            hm.put(Integer.toString(i), i);
        }
    }
}