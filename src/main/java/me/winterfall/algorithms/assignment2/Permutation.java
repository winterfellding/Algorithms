package me.winterfall.algorithms.assignment2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        int size = queue.size();
        for (int i = 0; i < size - n; i++) {
            queue.dequeue();
        }

        for (String s : queue) {
            StdOut.println(s);
        }
    }
}
