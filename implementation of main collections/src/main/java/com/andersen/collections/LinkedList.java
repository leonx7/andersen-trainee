package com.andersen.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//Bidirectional linked list
public class LinkedList<E> implements Iterable<E> {
    /* Pointer to first node */
    private Node head;

    /* Pointer to last node */
    private Node tail;

    private int size;

    private class Node {
        E data;
        public Node next;
        public Node previous;

        public Node(E data) {
            this.data = data;
        }

    }

    private class LinkedListIterator implements Iterator<E> {
        private Node node = head;

        @Override
        public boolean hasNext() {
            return Objects.nonNull(node);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = node.data;
            node = node.next;
            return data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E e : this) {
            s.append(e);
            s.append(" ");
        }
        return s.toString();
    }

    public int size() {
        return size;
    }

    /* Unlinks non-null node */
    private E unlink(Node node) {
        final E element = node.data;
        final Node next = node.next;
        final Node prev = node.previous;

        if (Objects.isNull(prev)) {
            head = next;
        } else {
            prev.next = next;
            node.previous = null;
        }

        if (Objects.isNull(next)) {
            tail = prev;
        } else {
            next.previous = prev;
            node.next = null;
        }

        node.data = null;
        size--;

        return element;
    }

    /* Add a node at the end of the list */
    public void add(E e) {
        /* 1. allocate node
         * 2. put in the data */
        Node node = new Node(e);
        size++;

        Node last = head;
        /* 3. This new node is going to be the last node, so
         * make next of it as NULL*/
        node.next = null;

        /* 4. If the Linked List is empty, then make the new
         * node as head */
        if (Objects.isNull(head)) {
            node.previous = null;
            head = node;
            tail = node;
            return;
        }

        /* 5. Else traverse till the last node */
        while (Objects.nonNull(last.next)) {
            last = last.next;
        }

        /* 6. Change the next of last node */
        last.next = node;
        /* 7. Make last node as previous of new node */
        node.previous = last;
        tail = node;
    }

    /* Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged
   `*/
    public boolean remove(Object o) {
        if (Objects.isNull(o)) {
            for (Node x = head; Objects.nonNull(x); x = x.next) {
                if (Objects.isNull(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = head; Objects.nonNull(x); x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));

    }

    /* Returns the element at the specified position in this list */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }

    /* Returns true if this list contains the specified element */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /*
        Returns the index of the first occurrence of the specified element
        in this list, or -1 if this list does not contain the element
    */
    public int indexOf(Object o) {
        int index = 0;
        if (Objects.isNull(o)) {
            for (Node x = head; Objects.nonNull(x); x = x.next) {
                if (Objects.isNull(x.data))
                    return index;
                index++;
            }
        } else {
            for (Node x = head; Objects.nonNull(x); x = x.next) {
                if (o.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /*
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node x = head; Objects.nonNull(x); x = x.next)
            result[i++] = x.data;
        return result;
    }

    /* Tells if the argument is the index of an existing element */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
    }

    /* Returns the (non-null) Node at the specified element index */
    private Node node(int index) {
        Node x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.previous;
        }
        return x;
    }
}
