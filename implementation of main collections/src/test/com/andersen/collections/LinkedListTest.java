package com.andersen.collections;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LinkedListTest {
    private static final LinkedList<Integer> ls = new LinkedList<>();

    @Test
    @Order(1)
    void add() {
        ls.add(1);
        ls.add(2);
        ls.add(3);
        assertEquals(3, ls.size());
    }

    @Test
    @Order(2)
    void toArray() {
        Object[] arr = ls.toArray();
        assertEquals("[1, 2, 3]", Arrays.toString(arr));
    }

    @Test
    @Order(3)
    void get() {
        assertEquals(1, ls.get(0));
    }

    @Test
    @Order(4)
    void size() {
        assertEquals(3, ls.size());
    }

    @Test
    @Order(5)
    void indexOf() {
        assertEquals(2, ls.indexOf(3));
    }

    @Test
    @Order(6)
    void remove() {
        ls.remove(0);
        assertEquals(2, ls.size());
    }

    @Test
    @Order(7)
    void testRemove() {
        Integer x = 2;
        assertTrue(ls.remove(x));
        assertFalse(ls.remove(x));
        assertEquals(1, ls.size());
    }

    @Test
    @Order(8)
    void contains() {
        assertTrue(ls.contains(3));
        assertFalse(ls.contains(333));
    }
}