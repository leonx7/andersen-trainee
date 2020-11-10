package com.andersen;

public class BubbleSort2 {
    public static void bubbleSort(long[] arr) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                    needIteration = true;
                }
            }
        }
    }

    private static void swap(long[] arr, int index1, int index2) {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
