package com.andersen.sortalgorithms;

//The complexity of the algorithm O(N^2);
public class CocktailSort {
    public static void sort(long[] nums) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i <= nums.length - 2; i++) {
                if (nums[i] > nums[i + 1]) {
                    //test if two elements are in the wrong order
                    long temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] > nums[i + 1]) {
                    long temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
