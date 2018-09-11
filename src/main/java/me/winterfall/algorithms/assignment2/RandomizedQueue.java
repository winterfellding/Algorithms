package me.winterfall.algorithms.assignment2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] list;
    private int size;

    public RandomizedQueue() {
        list = (Item[]) new Object[16];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }





    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == list.length) {
            resize(list.length * 2);
        }
        list[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int num = StdRandom.uniform(size());
        Item element = list[num];

        for (int i = num; i < size - 1; i++) {
            list[num] = list[num+1];
        }
        list[--size] = null;
        if (size <= list.length / 4) {
            resize(list.length / 2);
        }
        return element;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int num = StdRandom.uniform(size());
        if (size > 0 && size <= list.length / 4) {
            resize(list.length / 2);
        }
        return list[num];
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++)
        {
            copy[i] = list[i];
        }
        list = copy;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<>();
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private int idx = 0;

        @Override
        public boolean hasNext() {
            return idx < size();
        }

        @Override
        public Item next() {
            if (idx < size() - 1) {
                throw new NoSuchElementException();
            }
            return (Item) list[idx++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
