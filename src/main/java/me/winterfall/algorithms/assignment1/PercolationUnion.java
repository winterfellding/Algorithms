package me.winterfall.algorithms.assignment1;

import edu.princeton.cs.algs4.QuickUnionUF;

public class PercolationUnion {

    private boolean[] isOpened;
    private QuickUnionUF unionUF;
    private int n;
    private int openNum;

    public PercolationUnion(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N should be larger than 0");
        }
        this.n  = n;
        this.isOpened = new boolean[n*n];
        this.unionUF = new QuickUnionUF(n*n+2);

    }

    public void open(int row, int col) {
        if (row > n || row <= 0 || col > n || col <= 0) {
            throw new IllegalArgumentException("Row or col is out of range");
        }

        if (!isOpen(row, col)) {
            int idx = idx(row, col);
            isOpened[openIdx(row, col)] = true;
            if (row == 1) {
                unionUF.union(0, idx);
            }
            if (row == n) {
                unionUF.union(idx, n*n+1);
            }

            int upIdx = row > 1 ? idx(row-1, col) : -1;
            int leftIdx = col > 1 ? idx(row, col-1) : -1;
            int rightIdx = col < n ? idx(row, col+1) : -1;
            int bottomIdx = row < n ? idx(row+1, col) : -1;

            unionIfOpened(idx, upIdx, leftIdx, rightIdx, bottomIdx);
        }

        openNum++;
    }

    private int openIdx(int row, int col) {
        return (row - 1) * n + col - 1;
    }
    private void unionIfOpened(int sourceIdx, int... neighborIdx) {
        for (int idx : neighborIdx) {
            // idx是从quickUnionUF中取的，多了个开头的虚拟
            if (idx > -1 && isOpened[idx-1]) {
                unionUF.union(sourceIdx, idx);
            }
        }
    }

    private int idx(int row, int col) {
        return (row-1) * n + col;
    }

    public boolean isOpen(int row, int col) {
        return isOpened[openIdx(row, col)];
    }

    public boolean isFull(int row, int col) {
        return unionUF.connected(0, idx(row, col));
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        return unionUF.connected(0, n*n+1);
    }
}
