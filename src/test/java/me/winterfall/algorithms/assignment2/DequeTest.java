package me.winterfall.algorithms.assignment2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class DequeTest {

    @Test
    public void isEmpty() {
        Deque<String> deque = new Deque<>();
        Assert.assertTrue(deque.isEmpty());
        deque.addFirst("aaa");
        deque.addLast("bbb");
        Assert.assertFalse(deque.isEmpty());
    }

    @Test
    public void size() {
        Deque<String> deque = new Deque<>();
        Assert.assertEquals(deque.size(), 0);
        deque.addFirst("aaa");
        deque.addLast("bbb");
        for (String s : deque) {
            System.out.println(s);
        }
        Assert.assertEquals(deque.size(), 2);
    }

    @Test
    public void removeFirst() {
        Deque<String> deque = new Deque<>();
        Assert.assertEquals(deque.size(), 0);
        deque.addFirst("aaa");
        deque.addLast("bbb");
        deque.addFirst("ccc");
        Assert.assertEquals("ccc", deque.removeFirst());
    }

    @Test
    public void removeLast() {
        Deque<String> deque = new Deque<>();
        Assert.assertEquals(deque.size(), 0);
        deque.addFirst("aaa");
        deque.addLast("bbb");
        deque.addFirst("ccc");
        Assert.assertEquals("bbb", deque.removeLast());
    }

    @Test
    public void iterator() {
        Deque<String> deque = new Deque<>();
        Assert.assertEquals(deque.size(), 0);
        deque.addFirst("aaa");
        deque.addLast("bbb");
        deque.addFirst("ccc");
        Iterator<String> iterator = deque.iterator();
        Assert.assertEquals("ccc", iterator.next());
        Assert.assertEquals("aaa", iterator.next());
        Assert.assertEquals("bbb", iterator.next());
    }
}
