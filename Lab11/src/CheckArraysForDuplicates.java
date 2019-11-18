import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class CheckArraysForDuplicates
{
  
    
    public static void main(String[] args) {
        
        if (args.length < 1) {
            StdOut.println("Usage: java-algs4 CheckArraysForDuplicates filename");
            System.exit(1);
        }
        String filename = args[0];
        
        In in = new In(filename);
        
        int k = in.readInt(); // how many arrays
        int[] sizes = new int[k];  
        int[][] vals = new int[k][];


        /* Read in all numbers into a set of arrays */
        for (int i=0; i<k; i++) {
            int n = in.readInt();
            sizes[i] = n;
            vals[i] = new int[n];
        }
        for (int i=0; i<k; i++) {
            for (int j=0; j < sizes[i]; j++) {
                vals[i][j] = in.readInt();
            }
        }

        int N = 0;
        for (int i = 0; i < sizes.length; ++i) {
            N += sizes[i];
        }

        /* Now it's your turn:  how to use a RedBlack tree (of size k) to solve this?*/
        boolean duplicate = false;
        StopwatchCPU sw = new StopwatchCPU();

        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
        int[] offsets = new int[k];
        for (int i=0; i<k; i++) {
            offsets[i] = 1;

            // check for duplicates within the first three entries
            if (rbt.contains(vals[i][0])) { duplicate = true; }
            
            rbt.put(vals[i][0], i); // use the array entry as the key, and the OFFSET! array index as the value
        }

        int count = 3;
        int total = N;
        while (!duplicate) {
            // if all keys have been deleted break
            if (rbt.size() == 0) { break; }
            // key is int from k array
            int minKey = rbt.min();
            // val is id of array it came from
            int minVal = rbt.get(minKey);
            // delete min value
            rbt.deleteMin();
            ++count;
            // if there is more in the array get the next value
            if (offsets[minVal] < sizes[minVal]) {
                // insert new entry from the same array as the int that was just removed
                rbt.put(vals[minVal][offsets[minVal]], minVal);
                // increment offsets so it pulls the right number next time
                offsets[minVal] += 1;
            }
            else {
                // one array has been completely searched
                // decrement k so duplicate invariant holds
                --k;
            }
            // check for duplicates using invariant
            if (rbt.size() < k) { duplicate = true; }
        }

        double elapsed = sw.elapsedTime();

        StdOut.println("The arrays do " + (duplicate?"":"not ") + "contain a duplicate " );
        StdOut.println("elapsed time: " + elapsed + " seconds");
    }

   
}

