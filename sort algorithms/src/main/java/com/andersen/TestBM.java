package com.andersen;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Random;

public class TestBM {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void bubbleSortBM() {
        BubbleSort.bubbleSort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void bubbleSortBM2() {
        BubbleSort2.bubbleSort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void selectionSortBM() {
        SelectionSort.selectionSort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void insertionSortBM() {
        InsertionSort.insertionSort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void mergeSortBM() {
        MergeSort.mergeSort(getArray(), 0, 9999);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 10)
    public void quickSortBM() {
        QuickSort.quickSort(getArray(), 0, 9999);
    }

    private long[] getArray() {
        Random random = new Random();
        final int SIZE = 10000;
        long[] arr = new long[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }
}
