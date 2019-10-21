import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

public class PercolationStats {

    private double[] numOpen;
    StdStats stats;
    int numTests;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        numOpen = new double[T];
        numTests = T;

        for (int i = 0; i < T; ++i) {
            Percolation testPerk = new Percolation(N);
            Random random = new Random();
            int row = 0;
            int col = 0;
            while (testPerk.percolates() == false) {
                row = random.nextInt(N);
                col = random.nextInt(N);
                testPerk.open(row, col);
            }

            numOpen[i] = (double) testPerk.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return stats.mean(numOpen);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stats.stddev(numOpen);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - (1.96 * stddev()/ Math.sqrt(numTests));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()/ Math.sqrt(numTests));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats test = new PercolationStats(N, T);

        System.out.println("Percolation(" + N + ", " + T + ")");
        System.out.println("mean()              =" + test.mean());
        System.out.println("stddev()            =" + test.stddev());
        System.out.println("confidenceLow()     =" + test.confidenceLow());
        System.out.println("confidenceHigh()    =" + test.confidenceHigh());
    }
}
