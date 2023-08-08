package org.neosoft;

import java.util.*;

public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E> {
    private transient TreeMap<E, Object> map;

    // Dummy value to associate with each element in the TreeMap
    private static final Object DUMMY = new Object();

    public TreeSet() {
        map = new TreeMap<>();
    }

    public TreeSet(Comparator<? super E> comparator) {
        map = new TreeMap<>(comparator);
    }

    public TreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return map.descendingKeySet().iterator();
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return new TreeSet<>((Comparator) map.descendingMap());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, DUMMY) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == DUMMY;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Comparator<? super E> comparator() {
        return map.comparator();
    }

    @Override
    public E first() {
        return map.firstKey();
    }

    @Override
    public E last() {
        return map.lastKey();
    }

    @Override
    public E lower(E e) {
        return map.lowerKey(e);
    }

    @Override
    public E floor(E e) {
        return map.floorKey(e);
    }

    @Override
    public E ceiling(E e) {
        return map.ceilingKey(e);
    }

    @Override
    public E higher(E e) {
        return map.higherKey(e);
    }

    @Override
    public E pollFirst() {
        Map.Entry<E, Object> entry = (Map.Entry<E, Object>) map.pollFirstEntry();
        return (entry != null) ? entry.getKey() : null;
    }

    @Override
    public E pollLast() {
        Map.Entry<E, Object> entry = (Map.Entry<E, Object>) map.pollLastEntry();
        return (entry != null) ? entry.getKey() : null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return new TreeSet<>((Comparator) map.subMap(fromElement, fromInclusive, toElement, toInclusive));
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return new TreeSet<>((Comparator) map.headMap(toElement, inclusive));
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return new TreeSet<>((Comparator) map.tailMap(fromElement, inclusive));
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }
}

