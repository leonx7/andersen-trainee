package com.andersen.patterns.behavioral.strategy;

import java.util.Arrays;

//Concrete strategy #2
public class SelectionSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        System.out.println("Selection sorting");
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        System.out.println("After sorting: " + Arrays.toString(arr));
    }
}
