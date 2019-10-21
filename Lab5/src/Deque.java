import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // class fields
    Node master;    // master node for deque
    int n;          // size of deque

    // private inner class to implement Iterator
    private class dequeIterator implements Iterator<Item> {
        Node iteratorNode;      // iterator pointer
        int iteratorN;          // deque size

        public dequeIterator() {
            iteratorNode = new Node();
            iteratorNode.top = master;
            iteratorNode.bottom = master;
            iteratorN = size();
        }

        @Override
        public boolean hasNext() {
            return iteratorNode.top.top != null;
        }

        @Override
        public Item next() {
            Item iteratorReturn = (Item) new Object();
            if (iteratorN == 0) { throw new NoSuchElementException(); }

            if (hasNext()) {
                if (iteratorNode.top == iteratorNode.bottom) {
                    iteratorNode.top = iteratorNode.top.top;
                }

                iteratorReturn =  iteratorNode.top.data;
                iteratorNode.top = iteratorNode.top.bottom;
                --iteratorN;
            }
            return iteratorReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Iterator constructor
    @Override
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }


    // private inner class for nodes
    private class Node {
        Node top;
        Node bottom;
        Item data;

        // default constructor
        public Node() {
            top = null;
            bottom = null;
            data = null;
        }

        // constructor with data filed initializer
        public Node(Item item) {
            top = null;
            bottom = null;
            data = item;
        }
    }

    // construct an empty deque
    public Deque(){
        master = new Node();

        master.top = null;
        master.bottom = null;
        n = 0;
    }

    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    public void addFirst(Item item) {
        Node newNode = new Node(item);

        // null item check
        if (item == null) { throw new NullPointerException(); }

        // baser case if list is empty
        if (isEmpty()) {
            // double pointers from master to newNode
            master.top = newNode;
            master.bottom = newNode;

            // double pointers from newNode to master
            newNode.top = master;
            newNode.bottom = master;
            ++n;
            return;
        }

        // list is not empty, put new node on top
        master.top.top = newNode;
        newNode.bottom = master.top;
        newNode.top = master;
        master.top = newNode;
        ++n;
        return;
    }


    public void addLast(Item item) {
        Node newNode = new Node(item);

        // null item check
        if (item == null) { throw new NullPointerException(); }

        // baser case if list is empty
        if (isEmpty()) {
            // double pointers from master to newNode
            master.top = newNode;
            master.bottom = newNode;

            // double pointers from newNode to master
            newNode.top = master;
            newNode.bottom = master;
            ++n;
            return;
        }

        // list is not empty, put node on bottom;
        master.bottom.bottom = newNode;
        newNode.top = master.bottom;
        newNode.bottom = master;
        master.bottom = newNode;
        ++n;
        return;
    }

    public Item removeFirst() {

        // empty case
        if (isEmpty()) { throw new NoSuchElementException(); }

        // new object initialization may not be necessary but just being safe
        Item returnData = (Item) new Object();
        returnData = master.top.data;

        // size 1 case
        if (size() == 1) {
            master.top = null;
            master.bottom = null;
            --n;
            return returnData;
        }

        // size > 1 case
        master.top.bottom.top = master;
        master.top = master.top.bottom;
        --n;
        return returnData;
    }


    public Item removeLast() {
        // empty case
        if (isEmpty()) { throw new NoSuchElementException(); }

        // empty case
        if (isEmpty()) { throw new IndexOutOfBoundsException(0); }

        // new object initialization may not be necessary but just being safe
        Item returnData = (Item) new Object();
        returnData = master.bottom.data;

        // size 1 case
        if (size() == 1) {
            master.top = null;
            master.bottom = null;
            --n;
            return  returnData;
        }

        // size > 1 case
        master.bottom.top.bottom = master;
        master.bottom = master.bottom.top;
        --n;
        return returnData;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        deque.addLast("I");
        deque.addLast("once");
        deque.addLast("danced");
        deque.addFirst("upon");
        deque.addFirst("a");
        deque.addFirst("dream");
        deque.addFirst("with");
        deque.addLast("you");

        // iterator test
        // expected output is "with dream a upon I once danced you"
        Iterator dequeIterator = deque.iterator();
        for (int i = 0; i < deque.n; ++i) {
            System.out.println(dequeIterator.next());
        }
    }
}