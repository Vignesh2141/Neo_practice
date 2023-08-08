package org.neosoft;

import java.util.Collection;
import java.util.Iterator;

public interface Queue<E> extends Collection<E> {
    // Insert an element at the rear of the queue
    boolean add(E e);

    // Same as add, but may throw an exception if the queue is full
    boolean offer(E e);

    // Remove and return the element at the front of the queue
    E remove();

    // Same as remove, but may return null if the queue is empty
    E poll();

    // Return the element at the front of the queue without removing it
    E element();

    // Same as element, but may return null if the queue is empty
    E peek();
}

