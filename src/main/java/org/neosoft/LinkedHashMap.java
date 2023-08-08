package org.neosoft;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMap<K, V> extends AbstractMap<K, V> {
    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry<K, V> prev;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    private final int capacity;
    private final float loadFactor;
    private int size;
    private Entry<K, V>[] table;
    private Entry<K, V> header;

    public LinkedHashMap() {
        this(16, 0.75f);
    }

    public LinkedHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public LinkedHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Invalid load factor: " + loadFactor);
        }
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.header = new Entry<>(null, null);
        header.next = header;
        header.prev = header;
    }

    private int hash(K key) {
        // Custom hash function based on Object.hashCode()
        return (key == null) ? 0 : key.hashCode();
    }

    private int getIndex(int hash) {
        return (hash & 0x7FFFFFFF) % capacity;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = getIndex(hash);

        Entry<K, V> existingEntry = findEntry(key);
        if (existingEntry != null) {
            V oldValue = existingEntry.value;
            existingEntry.value = value;
            return oldValue;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        addEntry(newEntry, index);
        return null;
    }

    @Override
    public V get(Object key) {
        Entry<K, V> entry = findEntry(key);
        return (entry != null) ? entry.value : null;
    }

    @Override
    public boolean containsKey(Object key) {
        return findEntry(key) != null;
    }

    @Override
    public V remove(Object key) {
        int hash = hash((K) key);
        int index = getIndex(hash);

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                removeEntry(entry);
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        header.next = header;
        header.prev = header;
        size = 0;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        // Create a custom set view of the entries in the LinkedHashMap
        return null;
    }

    private Entry<K, V> findEntry(Object key) {
        int hash = hash((K) key);
        int index = getIndex(hash);

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (key == null ? entry.key == null : key.equals(entry.key)) {
                return entry;
            }
            entry = entry.next;
        }

        return null;
    }

    private void addEntry(Entry<K, V> newEntry, int index) {
        Entry<K, V> first = table[index];
        table[index] = newEntry;

        newEntry.next = first;
        newEntry.prev = header;
        header.next.prev = newEntry;
        header.next = newEntry;

        if (size++ >= loadFactor * capacity) {
            resize();
        }
    }

    private void removeEntry(Entry<K, V> entry) {
        int index = getIndex(hash(entry.key));
        Entry<K, V> current = table[index];
        Entry<K, V> prevEntry = null;

        while (current != null) {
            if (current == entry) {
                if (prevEntry == null) {
                    table[index] = current.next;
                } else {
                    prevEntry.next = current.next;
                }

                current.next.prev = current.prev;
                current.prev.next = current.next;

                size--;
                return;
            }

            prevEntry = current;
            current = current.next;
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        // Rehash the entries and store them in the new hash table array
        Entry<K, V> current = header.next;
        while (current != header) {
            int newIndex = getIndex(hash(current.key));
            Entry<K, V> nextEntry = current.next;
            current.next = newTable[newIndex];
            newTable[newIndex] = current;
            current = nextEntry;
        }

        table = newTable;
    }

}

