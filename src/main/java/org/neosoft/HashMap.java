package org.neosoft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size;
    private int capacity;
    private float loadFactor;

    // Inner class to represent the key-value pairs in the map
    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Invalid load factor: " + loadFactor);
        }
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = getIndex(hash);

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }


        // Key not found, create a new entry and add it to the linked list
        Entry<K, V> newEntry = new Entry<>(key, value, table[index]);
        table[index] = newEntry;
        size++;

        // Check if resizing is needed
        if (size > loadFactor * capacity) {
            resize();
        }

        return null; // Indicating no previous value for the given key
    }

    private int hash(K key) {
        if (key == null) {
            return 0; // Handle null keys
        }
        // If the key's hashCode() method is overridden, use it.
        // Otherwise, use the default implementation from Object class.
        return key.hashCode();
    }

    private int getIndex(int hash) {
        // Take the absolute value of the hash code to handle negative hash codes
        // Modulo operation to map the hash code to a valid index in the array
        return Math.abs(hash) % capacity;
    }

    private void resize() {
        int newCapacity = capacity * 2; // Double the current capacity
        Entry<K, V>[] newTable = new Entry[newCapacity];

        // Rehash the entries and store them in the new hash table array
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int newIndex = getIndex(hash(entry.key), newCapacity);
                Entry<K, V> nextEntry = entry.next;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = nextEntry;
            }
        }

        // Update the hash table and capacity
        table = newTable;
        capacity = newCapacity;
    }

    private int getIndex(int hash, int newCapacity) {
        return Math.abs(hash) % newCapacity;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = getIndex(hash);

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null; // Key not found
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = getIndex(hash);

        Entry<K, V> prevEntry = null;
        Entry<K, V> current = table[index];

        // Search for the key in the linked list at the specific index
        while (current != null) {
            if (current.key.equals(key)) {
                V removedValue = current.value;

                // Remove the entry from the linked list
                if (prevEntry == null) {
                    table[index] = current.next;
                } else {
                    prevEntry.next = current.next;
                }

                size--;
                return removedValue;
            }
            prevEntry = current;
            current = current.next;
        }

        // Key not found
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);
        int index = getIndex(hash);

        Entry<K, V> current = table[index];

        // Search for the key in the linked list at the specific index
        while (current != null) {
            if (current.key.equals(key)) {
                return true; // Key found
            }
            current = current.next;
        }

        // Key not found
        return false;
    }


    @Override
    public void clear() {
        // Set all entries to null to remove references and allow for garbage collection
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        size = 0; // Reset the size to zero
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        // Traverse each bucket in the hash table
        for (Entry<K, V> entry : table) {
            // Traverse the linked list in the current bucket
            while (entry != null) {
                // Add the key to the set
                keys.add(entry.key);
                entry = entry.next;
            }
        }

        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();

        // Traverse each bucket in the hash table
        for (Entry<K, V> entry : table) {
            // Traverse the linked list in the current bucket
            while (entry != null) {
                // Add the value to the collection
                values.add(entry.value);
                entry = entry.next;
            }
        }

        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();

        // Traverse each bucket in the hash table
        for (Entry<K, V> entry : table) {
            // Traverse the linked list in the current bucket
            while (entry != null) {
                // Add the entry to the set
                entries.add(entry);
                entry = entry.next;
            }
        }

        return entries;
    }

    // Other methods of the Map interface will need to be implemented as well.
    // For the sake of simplicity, they are omitted in this example.
}


