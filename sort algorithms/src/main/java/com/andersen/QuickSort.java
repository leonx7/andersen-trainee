package com.andersen;

//The best-case complexity of the algorithm O(N * log N);
public class QuickSort {
    public static void quickSort(long[] arr, int l, int h) {
        int p;
        if ((h - l) > 0) {
            p = partition(arr, l, h);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, h);
        }
    }

    private static int partition(long[] arr, int lowerBound, int upperBound) {
        int i;
        int pivot;
        int firstHighElement;
        pivot = upperBound;
        firstHighElement = lowerBound;
        for (i = lowerBound; i < upperBound; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, firstHighElement);
                firstHighElement++;
            }
        }
        swap(arr, pivot, firstHighElement);
        return firstHighElement;
    }

    private static void swap(long[] arr, int one, int two) {
        long temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }
}
