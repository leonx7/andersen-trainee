package com.andersen.collections;

import java.util.NoSuchElementException;

public interface Queue<E> {
    /* Retrieves, but does not remove, the head of this queue. It returns Null if the queue is empty. */
    E peek();

    /* This method is similar to peek(). It throws NoSuchElementException when the queue is empty */
    E element();

    /* Retrieves and removes the head of this queue */
    E poll();
}
