package com.andersen;

import com.andersen.sortalgorithms.BubbleSort;
import com.andersen.sortalgorithms.BubbleSort2;
import com.andersen.sortalgorithms.CocktailSort;
import com.andersen.sortalgorithms.HeapSort;
import com.andersen.sortalgorithms.InsertionSort;
import com.andersen.sortalgorithms.MergeSort;
import com.andersen.sortalgorithms.QuickSort;
import com.andersen.sortalgorithms.SelectionSort;
import com.andersen.sortalgorithms.ShellSort;
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
    @Measurement(iterations = 5)
    public void bubbleSortBM() {
        BubbleSort.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void bubbleSortBM2() {
        BubbleSort2.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void selectionSortBM() {
        SelectionSort.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void insertionSortBM() {
        InsertionSort.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void mergeSortBM() {
        MergeSort.sort(getArray(), 0, 9999);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void quickSortBM() {
        QuickSort.sort(getArray(), 0, 9999);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void shellSortBM2() {
        ShellSort.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void heapSortBM2() {
        HeapSort.sort(getArray());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void cocktailSortBM2() {
        CocktailSort.sort(getArray());
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
