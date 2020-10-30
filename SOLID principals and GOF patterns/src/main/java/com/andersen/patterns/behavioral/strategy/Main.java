package com.andersen.patterns.behavioral.strategy;

public class Main {
    public static void main(String[] args) {
        ClientStrategy client = new ClientStrategy();

        int[] arr = {-5, 0, 8, 33};
        client.setStrategy(new BubbleSort());
        client.executeStrategy(arr);

        int[] arr1 = {43, 14, 24, 1, 4, -3};
        client.setStrategy(new SelectionSort());
        client.executeStrategy(arr1);

        int[] arr2 = {5, 0, 8, 20, 12};
        client.setStrategy(new InsertingSort());
        client.executeStrategy(arr2);
    }
}
