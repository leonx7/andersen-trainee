package com.andersen;

import java.util.Scanner;

public class Test4 {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Producer thread started");
            lock.wait();
            System.out.println("Producer thread resumed");
        }
    }

    public static void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (lock) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            lock.notify();
        }
    }
}
