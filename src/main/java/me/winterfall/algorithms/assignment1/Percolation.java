package me.winterfall.algorithms.assignment1;

import java.util.HashSet;
import java.util.Set;

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

        int upIdx = row > 0 ? idx(row-1, col) : -1;
        int leftIdx = col > 0 ? idx(row, col-1) : -1;
        int rightIdx = col < n - 1 ? idx(row, col+1) : -1;
        int bottomIdx = row < n - 1 ? idx(row+1, col) : -1;

        Set<Integer> vals = numVals(idx, upIdx, leftIdx, rightIdx, bottomIdx);

        markToMinimalValue(vals);
        openNum++;
    }

    private Set<Integer> numVals(int... indexes) {
        Set<Integer> numVals = new HashSet<>();
        for (int idx : indexes) {
            if (idx > -1 && isOpened[idx]) {
                numVals.add(sites[idx]);
            }
        }
        return numVals;
    }

    private void markToMinimalValue(Set<Integer> numVals) {
        int minVal = numVals.stream().min(Integer::compareTo).get();
        for (int i = 0; i < n*n; i++) {
            if (numVals.contains(sites[i])) {
                sites[i] = minVal;
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
        return sites[idx(row, col)] < n;
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

}

