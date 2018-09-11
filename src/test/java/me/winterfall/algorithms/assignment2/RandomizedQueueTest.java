package me.winterfall.algorithms.assignment2;

import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void testRandomizedQueueEmpty() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(2);
        Assert.assertEquals(1, rq.size());
        Integer num = rq.dequeue();
        Assert.assertEquals(num, new Integer(2));

        Assert.assertTrue(rq.isEmpty());
    }


}
