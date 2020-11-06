package com.andersen.patterns.behavioral.strategy;

import java.util.Arrays;

//Concrete strategy #3
public class InsertingSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        System.out.println("Insertion sorting");
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println("After sorting: " + Arrays.toString(arr));
    }
}
