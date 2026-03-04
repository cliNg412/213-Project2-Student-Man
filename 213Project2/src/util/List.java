package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic growable list.
 * @param <E> element type
 */
public class List<E> implements Iterable<E> {
    private static final int CAPACITY = 4;
    private E[] objects;
    private int size;

    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[CAPACITY];
        size = 0;
    }

    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i] == null && e == null) return i;
            if (objects[i] != null && objects[i].equals(e)) return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        E[] bigger = (E[]) new Object[objects.length + CAPACITY];
        for (int i = 0; i < size; i++) {
            bigger[i] = objects[i];
        }
        objects = bigger;
    }

    public boolean contains(E e) {
        return find(e) != -1;
    }

    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size++] = e;
    }

    public void remove(E e) {
        int index = find(e);
        if (index == -1) return;
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[size - 1] = null;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if (index < 0 || index >= size) return null;
        return objects[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) return;
        objects[index] = e;
    }

    public int indexOf(E e) {
        return find(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>();
    }

    private class ListIterator<T> implements Iterator<E> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return !isEmpty() && current < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return objects[current++];
        }
    }
}