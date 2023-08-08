package org.neosoft;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> extends AbstractQueue<E> {
    private final ArrayList<E> elements;
    private final Comparator<? super E> comparator;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
        this.comparator = null;
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this.elements = new ArrayList<>();
        this.comparator = comparator;
    }

    public PriorityQueue(Collection<? extends E> c) {
        this.elements = new ArrayList<>(c);
        this.comparator = null;
        heapify();
    }

    public PriorityQueue(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.elements = new ArrayList<>(initialCapacity);
        this.comparator = null;
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.elements = new ArrayList<>(initialCapacity);
        this.comparator = comparator;
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        elements.add(e);
        siftUp(elements.size() - 1);
        return true;
    }

    @Override
    public E peek() {
        if (elements.isEmpty())
            return null;
        return elements.get(0);
    }

    @Override
    public E poll() {
        if (elements.isEmpty())
            return null;
        E result = elements.get(0);
        E lastElement = elements.remove(elements.size() - 1);
        if (!elements.isEmpty()) {
            elements.set(0, lastElement);
            siftDown(0);
        }
        return result;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    private void heapify() {
        int n = elements.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int index) {
        E e = elements.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) >>> 1;
            E parent = elements.get(parentIndex);
            if (compare(e, parent) >= 0)
                break;
            elements.set(index, parent);
            index = parentIndex;
        }
        elements.set(index, e);
    }

    private void siftDown(int index) {
        int n = elements.size();
        E e = elements.get(index);
        int half = n >>> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements.get(childIndex);
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < n && compare(child, elements.get(rightChildIndex)) > 0) {
                childIndex = rightChildIndex;
                child = elements.get(childIndex);
            }
            if (compare(e, child) <= 0)
                break;
            elements.set(index, child);
            index = childIndex;
        }
        elements.set(index, e);
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        if (comparator != null)
            return comparator.compare(e1, e2);
        else
            return ((Comparable<? super E>) e1).compareTo(e2);
    }
}

