package com.andersen.collections;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class HashMap<K, V> {
    /* Array of Entry */
    private Entry<K, V>[] table;

    /* Initial capacity of HashMap */
    private int capacity = 8;

    /* The next size value at which to resize (capacity * load factor) */
    private int threshold;

    /*  The load factor for the hash table. */
    private final float loadFactor = 0.75f;

    /* The number of key-value mappings contained in this map */
    private int size;

    private Set<K> keySet;

    @Data
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.table = (Entry<K, V>[]) new Entry[capacity];
        this.keySet = new HashSet<>();
        this.threshold = (int) (capacity * loadFactor);
    }


    /* Returns the number of key-value mappings in this map */
    public int size() {
        return size;
    }

    /* Returns true if this map contains no key-value mappings */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Method allows you put key-value pair in HashMapCustom.
     * If the map already contains a mapping for the key, the old value is replaced.
     */
    public void put(K key, V value) {
        int hash = getBucket(key);

        Entry<K, V> newEntry = new Entry<>(key, value, null);
        keySet.add(key);

        if (Objects.isNull(table[hash])) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (Objects.nonNull(current)) {
                if ((Objects.nonNull(current.key) && current.key.equals(key))
                        || (Objects.isNull(current.key) && Objects.isNull(key))) {
                    if (Objects.isNull(previous)) {
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            if (Objects.nonNull(previous))
                previous.next = newEntry;
        }
        if (++size > threshold)
            resize();
    }

    /*
     * Method returns value corresponding to key
     * Returns null if key is not found
     */
    public V get(K key) {
        int hash = getBucket(key);
        Entry<K, V> current = table[hash];
        while (Objects.nonNull(current)) {
            if ((Objects.nonNull(current.key) && current.key.equals(key))
                    || (Objects.isNull(current.key) && Objects.isNull(key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public Set<K> keySet() {
        return keySet;
    }

    public Collection<V> getValues() {
        Collection<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (Objects.nonNull(current)) {
                values.add(current.value);
                current = current.next;
            }
        }
        return values;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (Objects.nonNull(current)) {
                entrySet.add(current);
                current = current.next;
            }
        }
        return entrySet;
    }

    /* Method removes key-value pair from HashMapCustom */
    public boolean remove(K key) {
        int index = getBucket(key);
        Entry<K, V> previous = null;
        Entry<K, V> current = table[index];
        keySet.remove(key);
        size--;
        while (Objects.nonNull(current)) {
            if ((Objects.isNull(current.key) && Objects.isNull(key)) || current.key.equals(key)) {
                if (Objects.isNull(previous)) {
                    current = current.next;
                    table[index] = current;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /*
     * Removes all of the mappings from this map
     * The map will be empty after this call returns
     */
    public void clear() {
        if (Objects.nonNull(table) && size > 0) {
            size = 0;
            keySet.clear();
            Arrays.fill(table, null);
        }
    }

    /*
     * Method displays all key-value pairs present in HashMapCustom
     * Insertion order is not guaranteed
     */
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (Objects.nonNull(table[i])) {
                Entry<K, V> entry = table[i];
                while (Objects.nonNull(entry)) {
                    System.out.print("{" + entry.key + "=" + entry.value + "}" + " ");
                    entry = entry.next;
                }
            }
        }

    }

    /* The method returns the length of table array */
    public int length() {
        return table.length;
    }

    /* Computes key.hashCode() and spreads (XORs) higher bits of hash to lower */
    private int hash(Object key) {
        int h;
        return (Objects.isNull(key)) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /* Doubles table size and recomputes the bucket numbers for storing Entry objects */
    @SuppressWarnings("unchecked")
    private Entry<K, V>[] resize() {
        int oldCapacity = capacity;
        capacity <<= 1; // double capacity;
        threshold = (int) (capacity * loadFactor); // recompute threshold based on new capacity;
        Entry<K, V>[] oldTable = table; // reference to old table;
        table = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
        for (int i = 0; i < oldCapacity; i++) {
            Entry<K, V> current = oldTable[i];
            if (Objects.nonNull(current)) {
                while (Objects.nonNull(current)) {
                    put(current.key, current.value);
                    current = current.next;
                }
            }
        }
        return table;
    }

    /* Computes bucket number to put a new element */
    private int getBucket(K key) {
        if (Objects.isNull(key)) {
            return 0;
        }
        return (capacity - 1) & hash(key);
    }
}
