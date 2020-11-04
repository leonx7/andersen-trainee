package com.andersen.collections;

import org.junit.jupiter.api.*;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayListTest {
    private static final ArrayList<Integer> arrList = new ArrayList<>();

    public static void initialize() {
        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
    }

    @Test
    @Order(1)
    void add() {
        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
        assertEquals(3, arrList.size());
    }

    @Test
    @Order(2)
    void size() {
        assertEquals(3, arrList.size());
    }

    @Test
    @Order(3)
    void get() {
        assertEquals(2, arrList.get(1));
    }

    @Test
    @Order(4)
    void testAdd() {
        arrList.add(1, 100);
        assertEquals(4, arrList.size());
        assertEquals(100, arrList.get(1));
    }

    @Test
    @Order(5)
    void isEmpty() {
        assertFalse(arrList.isEmpty());
    }

    @Test
    @Order(6)
    void clear() {
        arrList.clear();
        assertEquals(0, arrList.size());
    }

    @Test
    @Order(7)
    void remove() {
        for (int i = 0; i < 15; i++) {
            arrList.add(i + 1);
        }
        assertEquals(15, arrList.remove(14));
    }

    @Test
    @Order(8)
    void trimToSize() {
        assertEquals(15, arrList.length());
        arrList.trimToSize();
        assertEquals(14, arrList.length());
        for (int i = arrList.size(); i > 0; i--) {
            arrList.remove(i - 1);
        }
        arrList.trimToSize();
        assertEquals(10, arrList.length());
    }
}