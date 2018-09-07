package me.winterfall.algorithms.assignment1;

import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private int[] sites;
    private boolean[] isOpened;
    private int n;
    private int openNum;

    public Percolation(int n) {
        this.n = n;
        sites = new int[n*n];
        for (int i = 0; i < n*n; i++) {
            sites[i] = i;
        }
        isOpened = new boolean[n*n];
    }

    public void open(int row, int col) {
        int idx = idx(row, col);
        isOpened[idx] = true;

        int upIdx = row > 0 ? idx(row - 1, col) : -1;
        int leftIdx = col > 0 ? idx(row, col-1) : -1;
        int rightIdx = col < n - 1 ? idx(row, col+1) : -1;
        int bottomIdx = row < n - 1 ? idx(row+1, col) : -1;
        if (upIdx >= 0 && isOpened[upIdx]) {
            sites[idx] = sites[upIdx];
        }
        markSameValue(idx, leftIdx);
        markSameValue(idx, rightIdx);
        markSameValue(idx, bottomIdx);
        openNum++;
    }

    private void markSameValue(int idx, int destIdx) {
        if (destIdx >= 0 && isOpened[destIdx]) {
            int rightVal = sites[destIdx];
            for (int i = 0; i < n*n; i++) {
                if (isOpened[i] && sites[i] == rightVal) {
                    sites[i] = sites[idx];
                }
            }
        }
    }

    private int idx(int row, int col) {
        return row * n + col;
    }

    public boolean isOpen(int row, int col) {
        return isOpened[idx(row, col)];
    }

    public boolean isFull(int row, int col) {
        return isOpen(row, col) && sites[idx(row, col)] < n;
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (isFull(n-1, i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int N = 100;
        Percolation percolation = new Percolation(N);
        int[] idx = new int[N*N];
        for (int i = 0; i < N*N; i++) {
            idx[i] = i;
        }

        StdRandom.shuffle(idx);

        int n = 0;
        for (int num : idx) {
            n++;
            percolation.open(num / N, num % N);
            if (percolation.percolates()) {
                System.out.println("Percolated: " + n);
                break;
            }
        }
    }

}

