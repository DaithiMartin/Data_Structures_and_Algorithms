import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class CompareTwoArrays
{


    private static boolean compareWithHeap (In inA, In inB, int size) {

        MaxPQ<Integer> heapA = new MaxPQ<Integer>();
        MaxPQ<Integer> heapB = new MaxPQ<Integer>();

        // fill heapA with inA
        while (!inA.isEmpty()) {
            int item = inA.readInt();
            heapA.insert(item);
        }

        // fill heapB with inB
        while ((!inB.isEmpty())) {
            int item = inB.readInt();
            heapB.insert(item);
        }

        for (int i = 0; i < size; ++i) {
            if (!heapA.delMax().equals(heapB.delMax())) {
                return false;
            }
        }

        return true;
    }

    private static boolean compareWithHash (In inA, In inB, int size) {

        LinearProbingHashST<Integer, Integer> hashA = new LinearProbingHashST<Integer, Integer>(size);

        while (!inA.isEmpty()) {
            int item = inA.readInt();
            if (!hashA.contains(item)) {
                hashA.put(item, 1);
            }
            else {
                hashA.put(item, hashA.get(++item));
            }
        }

        for (int i = 0; i < size; ++i) {
            int item = inB.readInt();
            if (hashA.contains(item)){
                if (hashA.get(item).equals(0)) { return false; }
                else { hashA.put(item, hashA.get(item) - 1);}
            }
            else { return false; }

        }
        return true;
    }

    public static void heapCompare(String filename1, String inOne, String filename2, String inTwo, String N) {
        In in1 = new In(filename1);
        int n1 = in1.readInt();
        In in2 = new In(filename2);
        int n2 = in2.readInt();

        Boolean match = false;
        String matchString;

        StopwatchCPU sw = new StopwatchCPU();
        match = compareWithHeap(in1, in2, n1);
        double elapsed = sw.elapsedTime();
        if (match.equals(true)) { matchString = "Yes"; }
        else { matchString = "No"; }

        StdOut.println(N + "  |      " + inOne + "       |     " + inTwo + "       |    " + elapsed + "   |   heap     |   " + matchString );
    }

    public static void hashCompare(String filename1, String inOne, String filename2, String inTwo, String N) {
        In in1 = new In(filename1);
        int n1 = in1.readInt();
        In in2 = new In(filename2);
        int n2 = in2.readInt();

        Boolean match = false;
        String matchString;

        StopwatchCPU sw = new StopwatchCPU();
        match = compareWithHash(in1, in2, n1);
        double elapsed = sw.elapsedTime();
        if (match.equals(true)) { matchString = "Yes"; }
        else { matchString = "No"; }

        StdOut.println(N + "  |      " + inOne + "       |     " + inTwo + "       |    " + elapsed + "   |   hash     |   " + matchString );
    }

    public static void main(String[] args) {

        /* MY MAIN METHOD FOR FORMATTED OUTPUT */
//        // input check
//        if (args.length < 3) {
//            StdOut.println("Usage: java-algs4 CompareTwoArrays filenameA filenameB [heap/hash]");
//            System.exit(1);
//        }
//
//        // necessary arguments fo function calls
//        String filenameA = args[0];
//        String filenameB = args[1];
//        String filenameC = args[2];
//        String N = args[3];
//
//        // heading
//        StdOut.println("N    |    File_1    |   File_2    |    Runtime (s)   |   Method   |   Match?");
//
//        // HEAP
//        // A and B
//        heapCompare(filenameA, "A", filenameB, "B", N);
//
//        // A and C
//        heapCompare(filenameA, "A", filenameC, "C", N);
//
//        // B and C
//        heapCompare(filenameB, "B", filenameC, "C", N);
//
//        // HASH
//        // A and B
//        hashCompare(filenameA, "A", filenameB, "B", N);
//
//        // A and C
//        hashCompare(filenameA, "A", filenameC, "C", N);
//
//        // B and C
//        hashCompare(filenameB, "B", filenameC, "C", N);

        /* ORIGINAL MAIN METHOD */
        if (args.length < 3) {
            StdOut.println("Usage: java-algs4 CompareTwoArrays filenameA filenameB [heap/hash]");
            System.exit(1);
        }
        String filenameA = args[0];
        String filenameB = args[1];
        String method    = args[2];

        if ( !method.equals("heap") && !method.equals("hash") ) {
            StdOut.println("Usage: java-algs4 CompareTwoArrays filenameA filenameB [heap/hash]");
            System.exit(1);
        }

        In inA = new In(filenameA);
        int nA = inA.readInt();

        In inB = new In(filenameB);
        int nB = inB.readInt();

        if (nA != nB) {
            StdOut.println("Arrays are not the same size, so not equivalent");
            System.exit(0);
        }

        boolean match = false;

        StopwatchCPU sw = new StopwatchCPU();
        if (method.equals("heap")) {
            match = compareWithHeap(inA, inB, nA);
        } else {
            match = compareWithHash(inA, inB, nA);
        }


        double elapsed = sw.elapsedTime();


        StdOut.println("The two input arrays do " + (match?"":"not ") + "match" );
        StdOut.println("elapsed time: " + elapsed + " seconds");
    }


}

