package com.andersen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class MyStreamApi {
    private static final Logger logger = LogManager.getLogger(MyStreamApi.class);

    public static void main(String[] args) {
        SortedSet<Integer> nums = new TreeSet<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++)
            nums.add(random.nextInt(100));
        logger.info(nums);

        System.out.println('\n');

        Stream<Integer> stream = nums.stream();
        stream.filter(i -> i % 2 == 0).forEach((i) -> System.out.print(i + " ")); // Display the numbers which are multiples of 2;

        System.out.println('\n');

        Stream<Integer> stream2 = nums.stream();
        stream2.map(i -> i * 2).forEach(i -> System.out.print(i + " ")); // Increase the numbers by 2;

        System.out.println('\n');

        Stream<Integer> stream3 = nums.parallelStream();
        stream3.map(i -> i * 2).forEach(i -> System.out.print(i + " ")); // Increase the numbers by 2 in parallel mode;

        System.out.println('\n');

        Stream<Integer> stream4 = nums.parallelStream();                            // Increase the numbers by 2 in parallel mode
        stream4.map(i -> i * 2).forEachOrdered(i -> System.out.print(i + " "));   // and display in order;

        System.out.println('\n');

        List<Integer> nums2 = new ArrayList<>();
        nums2.add(1);
        nums2.add(2);
        nums2.add(3);

        Stream<Integer> stream5 = nums2.stream();

        nums2.add(4);
        nums2.add(5);
        nums2.add(6);

        stream5.forEach(i -> System.out.print(i + " "));
    }
}
