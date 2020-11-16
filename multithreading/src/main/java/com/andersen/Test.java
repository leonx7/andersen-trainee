package com.andersen;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyThread1 thread1 = new MyThread1();
        thread1.start();

        Thread thread2 = new Thread(new MyThread2());
        thread2.start();

        FutureTask<Object> future = new FutureTask<>(new MyThread3());
        new Thread(future).start();
        System.out.println(future.get());

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i++){
            executorService.submit(new Work(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            System.out.println("Hello from MyThread1");
        }
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            System.out.println("Hello from MyThread2");
        }
    }
}

class MyThread3 implements Callable<Object> {

    @Override
    public Object call(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread3");
        }
        return null;
    }
}

class Work implements Runnable{
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work for id " + id + " was completed");
    }
}
