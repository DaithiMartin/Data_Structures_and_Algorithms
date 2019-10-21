import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Random;

public class Percolation {

    private int openSites;                          // number of open sites
    private int N;                                  // grid dimension
    private int[][] grid;                           // percolation grid
    private WeightedQuickUnionUF percolateSet;      // union find object for percolation
    private WeightedQuickUnionUF filledSet;         // union find object for fullness
//    private QuickUnionUF percolateSet;
//    private QuickUnionUF filledSet;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int gridSize) {
        if (gridSize <= 0) { throw new IndexOutOfBoundsException(); }

        openSites = 0;
        N = gridSize;
        grid = new int[N][N];

        // initialize union find sets with 2 extra spots for percolation and one extra for filled
        percolateSet = new WeightedQuickUnionUF(N*N + 2);
        filledSet = new WeightedQuickUnionUF(N*N + 1);
//        percolateSet = new QuickUnionUF(N * N + 2);
//        filledSet = new QuickUnionUF(N * N + 2);
    }

    // converts row an col to unionFind index
    private int gridToIndex(int row, int col) { return N * row + col; }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > (N - 1)) { throw new IndexOutOfBoundsException(); }
        if (col < 0 || col > (N - 1)) { throw new IndexOutOfBoundsException(); }

        // if cell is already open nothing needs to be done
        if (grid[row][col] == 1) { return; }

        // opens location and increments openSites count
        grid[row][col] = 1;
        ++openSites;

        // if in top row connects to percolationSet source and filledSet source
        if (gridToIndex(row, col) < N) {
            percolateSet.union(gridToIndex(row, col), N * N);
            filledSet.union(gridToIndex(row, col), N * N);
        }

        // if in bottom row connect to percolation sink
        if (gridToIndex(row, col) < (N * N - 1) && gridToIndex(row, col) > (N * N - N)) {
            percolateSet.union(gridToIndex(row, col), N * N + 1);
        }

        // checks cell to right
        if (col < N - 1) {
            if (grid[row][col + 1] == 1) {
                percolateSet.union(gridToIndex(row, col), gridToIndex(row, col + 1));
                filledSet.union(gridToIndex(row, col), gridToIndex(row, col + 1));
            }
        }

        // checks cell to the left
        if (col > 0) {
            if (grid[row][col - 1] == 1) {
                percolateSet.union(gridToIndex(row, col), gridToIndex(row, col - 1));
                filledSet.union(gridToIndex(row, col), gridToIndex(row, col - 1));
            }
        }

        // checks cell above
        if (row > 0) {
            if (grid[row - 1][col] == 1) {
                percolateSet.union(gridToIndex(row, col), gridToIndex(row - 1, col));
                filledSet.union(gridToIndex(row, col), gridToIndex(row - 1, col));
            }
        }

        // checks cell bellow
        if (row < N - 1) {
            if (grid[row + 1][col] == 1) {
                percolateSet.union(gridToIndex(row, col), gridToIndex(row + 1, col));
                filledSet.union(gridToIndex(row, col), gridToIndex(row + 1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > (N - 1)) { throw new IndexOutOfBoundsException(); }
        if (col < 0 || col > (N - 1)) { throw new IndexOutOfBoundsException(); }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > (N - 1)) { throw new IndexOutOfBoundsException(); }
        if (col < 0 || col > (N - 1)) { throw new IndexOutOfBoundsException(); }
        return filledSet.connected(gridToIndex(row, col), N * N);
    }

    public int numberOfOpenSites() {
        return openSites; }

    public boolean percolates() {
        return percolateSet.connected(N * N, N * N + 1); }

    public static void main(String[] args) {

        int N = 10;
        Percolation percTest = new Percolation(N);
        int row = 0;
        int col = 0;
        Random random = new Random();
//        PercolationVisualizer picture = new PercolationVisualizer();

        for (int i = 0; i < 10; ++i) {
            row = random.nextInt(N);
            col = random.nextInt(N);
            percTest.open(row, col);
        }
    }
}