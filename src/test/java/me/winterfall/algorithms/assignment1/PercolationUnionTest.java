package me.winterfall.algorithms.assignment1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercolationUnionTest {
    private PercolationUnion percolation;

    @Before
    public void before() {
        percolation = new PercolationUnion(5);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4,1);
        percolation.open(5, 1);
        percolation.open(4, 4);
    }

    @Test
    public void isOpen() {
        Assert.assertTrue(percolation.isOpen(1, 1));
        Assert.assertTrue(percolation.isOpen(3, 1));
        Assert.assertTrue(percolation.isOpen(4, 4));
        Assert.assertFalse(percolation.isOpen(5, 5));
        Assert.assertFalse(percolation.isOpen(3, 4));
    }

    @Test
    public void isFull() {
        Assert.assertTrue(percolation.isOpen(1, 1));
        Assert.assertTrue(percolation.isOpen(3, 1));
        Assert.assertFalse(percolation.isFull(4, 4));
        Assert.assertFalse(percolation.isFull(5, 5));
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
        PercolationUnion percolation = new PercolationUnion(3);
        percolation.open(2, 2);
        percolation.open(1, 2);
        percolation.open(2, 1);
        percolation.open(3, 3);
        percolation.open(3, 2);
        Assert.assertTrue(percolation.percolates());
    }

    @Test
    public void notPercolates() {
        PercolationUnion percolation = new PercolationUnion(3);
        percolation.open(2, 1);
        percolation.open(1, 2);

        percolation.open(3, 3);
        percolation.open(3, 2);
        Assert.assertFalse(percolation.percolates());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnConstructor() {
        new PercolationUnion(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void openZeroIndex() {
        PercolationUnion percolation = new PercolationUnion(1);
        percolation.open(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void openOutOfRange() {
        PercolationUnion percolation = new PercolationUnion(1);
        percolation.open(2, 2);
    }
}
