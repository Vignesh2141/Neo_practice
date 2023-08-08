package org.neosoft;

import java.util.*;
import java.util.Queue;

public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {
    // Constructors, Size, and Collection methods are inherited from AbstractCollection

    @Override
    public boolean add(E e) {
        if (offer(e)) {
            return true;
        } else {
            throw new IllegalStateException("Queue full");
        }
    }

    @Override
    public E remove() {
        E element = poll();
        if (element != null) {
            return element;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E element() {
        E element = peek();
        if (element != null) {
            return element;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void clear() {
        while (poll() != null) ;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        boolean modified = false;
        for (E e : c) {
            if (add(e))
                modified = true;
        }
        return modified;
    }
}

