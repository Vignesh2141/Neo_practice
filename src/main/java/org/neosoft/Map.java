package org.neosoft;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {
    // Returns the number of key-value mappings in this map.
    int size();

    // Returns true if this map contains no key-value mappings.
    boolean isEmpty();

    // Associates the specified value with the specified key in this map.
    V put(K key, V value);

    // Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    V get(K key);

    // Removes the mapping for the specified key from this map if present.
    V remove(K key);

    // Returns true if this map contains a mapping for the specified key.
    boolean containsKey(K key);

    // Removes all of the mappings from this map.
    void clear();

    // Returns a Set view of the keys contained in this map.
    Set<K> keySet();

    // Returns a Collection view of the values contained in this map.
    Collection<V> values();

    // Returns a Set view of the mappings contained in this map.
    Set<Entry<K, V>> entrySet();

    // Represents an entry in the map, containing a key-value pair.
    interface Entry<K, V> {
        // Returns the key corresponding to this entry.
        K getKey();

        // Returns the value corresponding to this entry.
        V getValue();

        // Replaces the value corresponding to this entry with the specified value.
        V setValue(V value);

        // Compares the specified object with this entry for equality.
        boolean equals(Object o);

        // Returns the hash code value for this entry.
        int hashCode();
    }
}

