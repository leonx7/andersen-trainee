package com.andersen.patterns.behavioral.iterator;

public class IteratorApp {
    public static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext())
            System.out.println((String) iterator.next());
    }
}
