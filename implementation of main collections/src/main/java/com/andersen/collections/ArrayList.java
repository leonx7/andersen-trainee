package com.andersen.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements Iterable<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private class ArrayListIterator implements Iterator<E> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return elements[current++];
        }
    }

    public ArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    /*
     * Trims the capacity of this instance to be the
     * list's current size
     */
    public void trimToSize() {
        if (size < elements.length) {
            elements = (size == 0) ? (E[]) new Object[DEFAULT_CAPACITY] : Arrays.copyOf(elements, size);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* Returns the length of the array of elements, as an int */
    public int length() {
        return elements.length;
    }

    /* Add element at the end */
    public void add(E element) {
        add(size, element);
    }

    /* Add the element to the specific position */
    public void add(int index, E element) {
        checkIndex(index);
        if (elements.length == size) ensureCapacity(size / 2 * 3);
        Object[] newElements = elements;
        System.arraycopy(elements, index,
                newElements, index + 1,
                size - index);
        newElements[index] = element;
        size++;
    }

    /* Get element by index */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /*
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
        final Object[] newElements = elements;
        for (int i = 0; i < size; i++)
            newElements[i] = null;
        size = 0;
    }

    /*
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     * Return the element that was removed from the list
     * Throw IndexOutOfBoundsException
     */
    public E remove(int index) {
        checkIndex(index);
        final E[] newElements = elements;
        E oldValue = newElements[index];
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(newElements, index + 1, newElements, index, newSize - index);
        newElements[size = newSize] = null;
        return oldValue;
    }

    /* Increases the capacity of this instance till specified as the argument */
    private void ensureCapacity(int capacity) {
        if (capacity <= size)
            return;
        elements = Arrays.copyOf(elements, capacity);
    }

    /* Tells if the argument is the index of an existing element */
    private void checkIndex(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }
}
