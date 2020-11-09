package com.andersen;

//The complexity of the algorithm O(N^2);
public class InsertionSort {
    public static long[] insertionSort(long[] arr) {
        for (int i = 1; i < arr.length; i++) {
            long temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }
}
