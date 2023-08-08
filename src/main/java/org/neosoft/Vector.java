package org.neosoft;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Vector<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_INCREMENT = 10;

    private Object[] elementData;
    private int elementCount;
    private int capacityIncrement;

    public Vector() {
        this(DEFAULT_CAPACITY, DEFAULT_INCREMENT);
    }

    public Vector(int initialCapacity) {
        this(initialCapacity, DEFAULT_INCREMENT);
    }

    public Vector(int initialCapacity, int capacityIncrement) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.elementCount = 0;
        this.capacityIncrement = capacityIncrement;
    }

    public Vector(Collection<? extends E> c) {
        this.elementData = c.toArray();
        this.elementCount = elementData.length;
        this.capacityIncrement = DEFAULT_INCREMENT;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new VectorIterator();
    }

    private class VectorIterator implements Iterator<E> {
        private int cursor = 0;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != elementCount;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= elementCount)
                throw new NoSuchElementException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < elementCount; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < elementCount; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    // Rest of the List interface methods such as add, remove, get, set, etc.

    // Not implemented for brevity. You can refer to the actual Vector implementation for complete code.
}

