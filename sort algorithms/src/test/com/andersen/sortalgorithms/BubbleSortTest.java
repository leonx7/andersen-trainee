package com.andersen.sortalgorithms;

import com.andersen.sortalgorithms.BubbleSort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BubbleSortTest {
    private static long[] toSort;
    private static long[] sorted;

    @BeforeAll
    public static void initVariables() {
        toSort = new long[]{5, 1, 89, 255, -7, 88, 200, 123, 66, 0};
        sorted = new long[]{-7, 0, 1, 5, 66, 88, 89, 123, 200, 255};
    }

    @Test
    void bubbleSort() {
        BubbleSort.sort(toSort);
        assertArrayEquals(sorted, toSort);
    }
}