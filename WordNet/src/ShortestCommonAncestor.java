import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;

import java.util.logging.SocketHandler;

public class ShortestCommonAncestor {

    Digraph ancestorDigraph;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) throws NullPointerException {

        // asshole checks
        if (G.equals(null)) throw new NullPointerException();
        DirectedCycle cycle = new DirectedCycle(G);
        if (cycle.hasCycle()) throw new IllegalArgumentException();

        // reference digraph
        this.ancestorDigraph = G;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) throws NullPointerException {

        // asshole check
        if (v < 0 | v > ancestorDigraph.V()) throw new IndexOutOfBoundsException();
        if (w < 0 | w > ancestorDigraph.V()) throw new IndexOutOfBoundsException();

        // breath first search starting at v
        // tracks search depth in hash table
        LinearProbingHashST<Integer, Integer> vHash = new LinearProbingHashST<>();
        Queue<Integer> vQue = new Queue<Integer>();
        vQue.enqueue(v);
        vHash.put(v, 0);
        while (!vQue.isEmpty()) {
            int x = vQue.dequeue();

            for (int vert : ancestorDigraph.adj(x)) {
                if (!vHash.contains(vert)) {
                    vHash.put(vert, vHash.get(x) + 1);
                }
                vQue.enqueue(vert);
            }
        }


        // breath first search starting at w
        // tracks distance in hash table
        // stops at first match with vhash
        LinearProbingHashST<Integer, Integer> wHash = new LinearProbingHashST<>();
        Queue<Integer> wQue = new Queue<>();
        wQue.enqueue(w);
        wHash.put(w, 0);
        int y = wQue.dequeue();
        while (!vHash.contains(y)) {
            for (int vert : ancestorDigraph.adj(y)) {
                if (!wHash.contains(vert)) {
                    wHash.put(vert, wHash.get(y) + 1);
                }
                wQue.enqueue(vert);
            }
            if (!wQue.isEmpty()) {
                y = wQue.dequeue();
            }
        }
        return wHash.get(y) + vHash.get(y);
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {

        // asshole check
        if (v < 0 | v > ancestorDigraph.V()) throw new IndexOutOfBoundsException();
        if (w < 0 | w > ancestorDigraph.V()) throw new IndexOutOfBoundsException();

        // breath first search starting at v
        // tracks search depth in hash table
        LinearProbingHashST<Integer, Integer> vHash = new LinearProbingHashST<>();
        Queue<Integer> vQue = new Queue<Integer>();
        vQue.enqueue(v);
        vHash.put(v, 0);
        while (!vQue.isEmpty()) {
            int x = vQue.dequeue();
            for (int vert : ancestorDigraph.adj(x)) {
                if (!vHash.contains(vert)) {
                    vHash.put(vert, vHash.get(x) + 1);
                }
                vQue.enqueue(vert);
            }
        }

        // breath first search starting at w
        // tracks distance in hash table
        // stops at first match with vhash
        LinearProbingHashST<Integer, Integer> wHash = new LinearProbingHashST<>();
        Queue<Integer> wQue = new Queue<>();
        wQue.enqueue(w);
        wHash.put(w, 0);

        int y = wQue.dequeue();
        while (!vHash.contains(y)) {
            for (int vert : ancestorDigraph.adj(y)) {
                wQue.enqueue(vert);
            }
            if (!wQue.isEmpty()) {
                y = wQue.dequeue();
            }
        }
        return y;
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {

        // asshole checks
        if (subsetA.equals(null)) throw new NullPointerException();
        if (subsetB.equals(null)) throw new NullPointerException();

        int size = 0;
        for (Integer item : subsetA) {
            if (item.equals(null)) throw new NullPointerException();
            ++size;
        }
        if (size == 0) throw new IllegalArgumentException();

        size = 0;
        for (Integer item : subsetB) {
            if (item.equals(null)) throw new NullPointerException();
            ++size;
        }
        if (size == 0) throw new IllegalArgumentException();


        MinPQ<Integer> shortestLengths = new MinPQ<>();

        for (int vertexA : subsetA) {

            for (int vertexB : subsetB) {
                shortestLengths.insert(length(vertexA, vertexB));
            }
        }

        return shortestLengths.delMin();
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {

        // asshole checks
        if (subsetA.equals(null)) throw new NullPointerException();
        if (subsetB.equals(null)) throw new NullPointerException();

        int size = 0;
        for (Integer item : subsetA) {
            if (item.equals(null)) throw new NullPointerException();
            ++size;
        }
        if (size == 0) throw new IllegalArgumentException();

        size = 0;
        for (Integer item : subsetB) {
            if (item.equals(null)) throw new NullPointerException();
            ++size;
        }
        if (size == 0) throw new IllegalArgumentException();


        int lengthMin = 1000000000;
        int vertMin = 0;

        for (int vertexA : subsetA) {
            for (int vertexB : subsetB) {
//                int ancestor = ancestor(vertexA, vertexB);
                int length = length(vertexA, vertexB);
                if (length < lengthMin) {
                    lengthMin = length;
                    vertMin = ancestor(vertexA, vertexB);
                }
            }
        }

        return vertMin;
    }

    // do unit testing of this class
    public static void main(String[] args) {

        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        ShortestCommonAncestor test = new ShortestCommonAncestor(G);
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();

        bag1.add(3);
//        bag1.add(9);
        bag1.add(7);
        bag1.add(1);

        bag2.add(11);
        bag2.add(2);
        bag2.add(4);

        System.out.println(test.length(bag1, bag2));
        System.out.println(test.ancestor(bag1, bag2));
//
//        int i = test.length(null, null);
    }
}