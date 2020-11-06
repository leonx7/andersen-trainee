package com.andersen.patterns.behavioral.iterator;

public class ConcreteAggregate implements Aggregate {
    String[] tasks = {"Построить дом", "Родить сына", "Посадить дерево"};

    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    //Concrete iterator
    private class TaskIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < tasks.length;
        }

        @Override
        public Object next() {
            return tasks[index++];
        }
    }
}
