package com.andersen;

public class Test3 {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Test3::increment);
        Thread thread2 = new Thread(Test3::increment);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + ": " + counter);
    }

    public static void increment() {
        synchronized (Test3.class) {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }
    }
}
