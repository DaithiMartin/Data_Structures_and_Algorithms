/******************************************************************************
 *  Lab 6 template - PQTester
 *  Compilation:  javac PQtester.java
 *  Execution:    java PQtester
 ******************************************************************************/
import edu.princeton.cs.algs4.MaxPQ;
import java.util.Random;
public class PQtester {

    private Random r = new Random();

    public long runTrial(int n) {
        MaxPQ<Integer> mpq = new MaxPQ<Integer>(n);
        long start = System.nanoTime();

        MaxPQ heap = new MaxPQ(n);
        Random random = new Random();

        // insert n times
        for (int i = 0; i < n; ++i) {
            int newInt = random.nextInt(n);
            heap.insert(newInt);
        }

        // delmax n/2 times
        for (int i = 0; i < (n/2); ++i) {
            heap.delMax();
        }

        // insert n/2 times
        for (int i = 0; i < (n/2); ++i) {
            int newInt = random.nextInt(n);
            heap.insert(newInt);
        }

        // delmax n times
        for (int i = 0; i < n; ++i) {
            heap.delMax();
        }


        long end = System.nanoTime();
        return end-start;
    }

    public static void main(String[] args) {
        PQtester t = new PQtester();
        int trials = 10;
//        int n = Integer.parseInt(args[0]);

        long total = 0;
        System.out.println("Trials: " + trials);
        System.out.println("-----------------------------------------------");
        for (int n = 1; n <= 2e10; n*=10) { //test with n=10,100,1000,...,1e8
            for (int i = 0; i < trials; i++) {
                total += t.runTrial(n);
            }
            System.out.println("Average run time for " + n +": " + total/trials + " nanoseconds");
        }
        System.out.println("-----------------------------------------------");

    }
}