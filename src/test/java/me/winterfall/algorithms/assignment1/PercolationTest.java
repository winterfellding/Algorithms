package me.winterfall.algorithms.assignment1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {

    private Percolation percolation;

    @Before
    public void before() {
        percolation = new Percolation(5);
        percolation.open(0, 0);
        percolation.open(1, 0);
        percolation.open(2, 0);
        percolation.open(3, 0);
        percolation.open(4, 0);
        percolation.open(3, 3);
    }

    @Test
    public void isOpen() {
        Assert.assertTrue(percolation.isOpen(0, 0));
        Assert.assertTrue(percolation.isOpen(2, 0));
        Assert.assertTrue(percolation.isOpen(3, 3));
        Assert.assertFalse(percolation.isOpen(4, 4));
        Assert.assertFalse(percolation.isOpen(2, 3));
    }

    @Test
    public void isFull() {
        Assert.assertTrue(percolation.isOpen(0, 0));
        Assert.assertTrue(percolation.isOpen(2, 0));
        Assert.assertFalse(percolation.isFull(3, 3));
        Assert.assertFalse(percolation.isFull(4, 4));
    }

    @Test
    public void numberOfOpenSites() {
        Assert.assertEquals(percolation.numberOfOpenSites(), 6);
    }

    @Test
    public void percolates() {
        Assert.assertTrue(percolation.percolates());
    }

    @Test
    public void percolatesWithAnotherOrder() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(0, 1);
        percolation.open(1, 0);
        percolation.open(2, 2);
        percolation.open(2, 1);
        Assert.assertTrue(percolation.percolates());
    }

    @Test
    public void notPercolates() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 0);
        percolation.open(0, 1);

        percolation.open(2, 2);
        percolation.open(2, 1);
        Assert.assertFalse(percolation.percolates());
    }
}
