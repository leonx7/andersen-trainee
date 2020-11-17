package com.andersen;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread1 = new MyThread1();
        thread1.start();

        Thread thread2 = new Thread(new MyThread2());
        thread2.start();

        FutureTask<Object> future = new FutureTask<>(new MyThread3());
        new Thread(future).start();

        /* Creates an Executor that uses a single worker thread operating off an unbounded queue */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Work(i));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("*******************************************************");

        /*
         * Creates a thread pool that reuses a fixed number of threads
         * operating off a shared unbounded queue.
         */
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService2.submit(new Work(i));
        }
        executorService2.shutdown();
        executorService2.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("*******************************************************");

        /*
         * Creates a thread pool that creates new threads as needed,
         * but will reuse previously constructed threads when they are available.
         */
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService3.submit(new Work(i));
        }
        executorService3.shutdown();
        executorService3.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("*******************************************************");

        /*
         * Creates thread pool for recursive tasks,
         * where all threads in the pool attempt to find and execute tasks
         * submitted to the pool and/or created by other active tasks.
         */
        ScheduledExecutorService executorService4 = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 5; i++) {
            executorService4.scheduleWithFixedDelay(new Work(i), 0, 5000, TimeUnit.MILLISECONDS);
        }
        Thread.sleep(6000);
        executorService4.shutdown();
        System.out.println("*******************************************************");

        /*
         * Creates a single-threaded executor
         * that can schedule commands to run after a given delay,
         * or to execute periodically.
         */
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(new Fibonacci(10));
        System.out.println(result);
        forkJoinPool.shutdown();

    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread1");
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread2");
        }
    }
}

class MyThread3 implements Callable<Object> {

    @Override
    public Object call() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread3");
        }
        return null;
    }
}

class Work implements Runnable {
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was completed by the thread " + Thread.currentThread().getName());
    }
}

class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        f2.fork();
        return f1.join() + f2.join();
    }
}
