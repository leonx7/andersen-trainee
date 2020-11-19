package com.andersen;

import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        Map<Thread, Data> map = new LinkedHashMap<>();
        Data data = new Data();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println("Thread " + Thread.currentThread().getName() + " has started at " + System.nanoTime());
                data.increment();
                System.out.println("Thread " + Thread.currentThread().getName() + " finished work at " + System.nanoTime());
                System.out.println("Counter value: " + data.getCounter() + '\n');
            });
            map.put(thread, data);
        }

        for (Thread thread : map.keySet()) {
            thread.start();
            thread.join();
        }

        System.out.println("Counter value: " + data.getCounter());
        System.out.println(map);
    }
}

@lombok.Data
@ToString
class Data {
    private int counter = 0;

    public void increment() {
        for (int i = 0; i < 10000; i++)
            counter++;
    }
}
