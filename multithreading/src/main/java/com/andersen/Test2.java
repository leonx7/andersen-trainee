package com.andersen;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        thread.shutdown();
    }
}

class MyThread extends Thread {
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("Hello!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        isRunning = false;
    }
}
