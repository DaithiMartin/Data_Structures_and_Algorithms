import java.util.Iterator;
import java.util.Random;

public class RandomizedBag<Item> implements Iterable<Item> {

    private Item[] array;       // array of items
    private int n;              // number of items in the array
    private Random randNum;     // random number generator

    // construct an empty randomized bag
    public RandomizedBag(){
        n = 0;
        array = (Item[]) new Object[5];
        randNum = new Random();
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < n; ++i) {
            newArray[i] = array[i];
        };
        array = newArray;
    }

    public void put(Item item){
        if (n == array.length) {
            int newSize = array.length * 2;
            resize(newSize);
        }

        array[n] = item;
        ++n;
    }

    // remove and return a random item
    public Item get(){
        Item temp = (Item) new Object();
        int random = randNum.nextInt(n);
        temp = array[random];

        // fill gap
        array[random] = array[n - 1];
        array[n - 1] = null;
        --n;
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        int random = randNum.nextInt(n);
        return array[random];
    }

    // define internal class BagIterator
    private class BagIterator implements Iterator<Item> {
        Item[] iteratorArray;
        int iterator_n;

        public BagIterator() {
            iteratorArray = (Item[]) new Object[iterator_n];
            iteratorArray = array.clone();
            iterator_n = n;
        }

        @Override
        public boolean hasNext() {
            return iterator_n >= 1;
        }

        @Override
        public Item next() {

            if (hasNext()) {
                Item temp = (Item) new Object();
                int random = randNum.nextInt(iterator_n);
                temp = iteratorArray[random];

                // fill gap
                iteratorArray[random] = iteratorArray[iterator_n - 1];
                iteratorArray[iterator_n - 1] = null;
                --iterator_n;
                return temp;
            }
            return iteratorArray[0];
        }
    }

    public Iterator<Item> iterator(){
        BagIterator bagIterator = new BagIterator();

        return bagIterator;
    }

    public static void main(String[] args) {

        RandomizedBag testBag = new RandomizedBag();
        for (int i = 0; i < 100; ++i) {
            testBag.put(i);
        }

        Iterator testIterator1 = testBag.iterator();
        Iterator testIterator2 = testBag.iterator();

        while (testIterator1.hasNext()) {
            System.out.println(testIterator1.next());
        }

        System.out.println("\nsep iterator\n");

        for (int i = 0; i < 100; ++i) {
            System.out.println(testIterator2.next());
        }
    }
}