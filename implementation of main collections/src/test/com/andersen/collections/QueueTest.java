package com.andersen.collections;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private static final ArrayList<Integer> arrList = new ArrayList<>();

    @Test
    void peek() {
        initVar();
        assertEquals(1, arrList.peek());
        arrList.clear();
        assertNull(arrList.peek());
    }

    @Test
    void element() {
        initVar();
        assertEquals(1, arrList.element());
        arrList.clear();

        Exception exception = assertThrows(NoSuchElementException.class, arrList::element);

        String expectedMessage = "The queue is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void poll() {
        initVar();
        assertEquals(10, arrList.size());
        assertEquals(1,arrList.poll());
        assertEquals(9, arrList.size());
    }

    private void initVar(){
        for (int i = 0; i < 10; i++) {
            arrList.add(i + 1);
        }
    }
}