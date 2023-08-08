package org.neosoft;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Collection<E> {
    // *** Positional Access and Search Operations ***

    // Adds the specified element at the given index in the list.
    void add(int index, E element);

    // Removes the element at the given index from the list.
    E remove(int index);

    // Retrieves the element at the given index from the list.
    E get(int index);

    // Replaces the element at the given index with the specified element.
    E set(int index, E element);

    // Searches for the first occurrence of the specified element in the list.
    int indexOf(Object o);

    // Searches for the last occurrence of the specified element in the list.
    int lastIndexOf(Object o);

    // *** List Iterators ***

    // Returns a list iterator over the elements in the list.
    ListIterator<E> listIterator();

    // Returns a list iterator over the elements in the list, starting at the specified index.
    ListIterator<E> listIterator(int index);

    // *** View-based Operations ***

    // Returns a sub-list view of the list, starting from the given start index (inclusive) to the end index (exclusive).
    List<E> subList(int fromIndex, int toIndex);
}

