    package me.winterfall.algorithms.assignment2;

    import java.util.Iterator;
    import java.util.NoSuchElementException;

    public class Deque<Item> implements Iterable<Item> {

        private Node<Item> head;
        private Node<Item> tail;
        private int size;

        public Deque() {
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void addFirst(Item item) {
            checkIllegalArgument(item);
            Node<Item> f = head;
            Node<Item> newNode = new Node<>(null, item, f);
            head = newNode;
            if (f == null)
                tail = newNode;
            else
                f.prev = newNode;
            size++;
        }

        public void addLast(Item item) {
            checkIllegalArgument(item);
            Node<Item> e = tail;
            Node<Item> newNode = new Node<>(e, item, null);
            tail = newNode;
            if (e == null) {
                head = newNode;
            } else {
                e.next = newNode;
            }
            size++;
        }

        public Item removeFirst() {
            checkEmpty();
            Node<Item> f = head;
            Item element = f.item;
            Node<Item> next = f.next;
            f.item = null;
            f.next = null; // help GC
            head = next;
            if (next == null)
                tail = null;
            else
                next.prev = null;
            size--;
            return element;
        }

        public Item removeLast() {
            checkEmpty();
            Node<Item> e = tail;
            Item element = e.item;
            Node<Item> prev = e.prev;
            e.item = null;
            e.prev = null; // help GC
            tail = prev;
            if (prev == null)
                head = null;
            else
                prev.next = null;
            size--;
            return element;
        }

        private void checkEmpty() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
        }

        private void checkIllegalArgument(Item item) {
            if (item == null) {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public Iterator<Item> iterator() {
            return new DequeIterator<>(head);
        }

        private class Node<Item> {

            private Item item;
            private Node<Item> prev;
            private Node<Item> next;

            public Node(Node<Item> prev, Item item, Node<Item> next) {
                this.item = item;
                this.prev = prev;
                this.next = next;
            }

            public Item getItem() {
                return item;
            }

            public void setItem(Item item) {
                this.item = item;
            }

            public Node<Item> getPrev() {
                return prev;
            }

            public void setPrev(Node<Item> prev) {
                this.prev = prev;
            }

            public Node<Item> getNext() {
                return next;
            }

            public void setNext(Node<Item> next) {
                this.next = next;
            }
        }

        private class DequeIterator<Item> implements Iterator<Item> {

            private Node<Item> node;

            public DequeIterator(Node<Item> node) {
                this.node = node;
            }

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                if (node == null) {
                    throw new NoSuchElementException();
                }
                Item element = node.item;
                node = node.next;
                return element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
