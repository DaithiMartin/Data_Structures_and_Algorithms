/******************************************************************************
 *  Compilation:  javac ResizingArrayStack.java
 *  Execution:    java ResizingArrayStack < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Stack implementation with a resizing array.
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class ResizingArrayStack<Item>  {
    private Item[] a;         // array of items
    private int n;            // number of elements on stack

    /**
     * Initializes an empty stack.
     */
    public ResizingArrayStack() {
        n = 0;
        a = (Item[]) new Object[10];
    }

    /**
     * Is this stack empty?
     */
    public boolean isEmpty() {
       return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
       return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < n; ++i) {
            newArray[i] = a[i];
        };
        a = newArray;
    }

    /**
     * Adds the item to this stack.
     */
    public void push(Item item) {
        if (n + 1 == a.length) {
            int newSize = a.length * 2;
            resize(newSize);
        }
        a[n] = item;
        ++n;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        --n;
        Item temp = a[n];
        return temp;
    }

    /**
     * This isn't necessary for the data type, but it is useful to
     * unit test the ResizingArrayStack data type.
     * You don't need to change this code at all.
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {

            String item = StdIn.readString();

            if (!item.equals("-"))  stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

}
