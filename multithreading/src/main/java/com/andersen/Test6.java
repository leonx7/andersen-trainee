package com.andersen;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Test6 {
    public static void main(String[] args) {
        List<CounterHolder> list = new ArrayList<>();
        CounterHolder counterHolder = new CounterHolder();

        for (int i = 0; i < 10; i++)
            list.add(counterHolder);

        list.parallelStream().forEach(CounterHolder::increment);

        System.out.println(counterHolder.getCounter());
    }
}

@lombok.Data
@ToString
class CounterHolder {
    private int counter = 0;

    public synchronized void increment() {
        System.out.println("Thread " + Thread.currentThread().getName() + " has started at " + System.nanoTime());
        for (int i = 0; i < 10000; i++)
            counter++;
        System.out.println("Thread " + Thread.currentThread().getName() + " finished work at " + System.nanoTime() + '\n');
        System.out.println("Counter value: " + this.counter);
    }
}


