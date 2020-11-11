package com.andersen.sortalgorithms;

//The complexity of the algorithm is in the interval from O(N^3/2) to O( N^7/6);
public class ShellSort {
    public static void sort(long[] arr) {
        int inner, outer;
        long temp;
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < arr.length; outer++) {
                temp = arr[outer];
                inner = outer;
                while (inner > h - 1 && arr[inner - 1] >= temp) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }
}
