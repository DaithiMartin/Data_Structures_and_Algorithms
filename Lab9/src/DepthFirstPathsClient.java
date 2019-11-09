import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;


public class DepthFirstPathsClient
{


    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);  // test with digraph_test.txt

        //hard-coded, using 10 as the source
        DepthFirstDirectedPaths dfspaths = new DepthFirstDirectedPaths(G, 10);

        for (int i=0; i<=11; i++) { // only works because I know the input graph
            boolean path_exists = dfspaths.hasPathTo(i);
            StdOut.print("Node 10 path to " + i + "?  ");
            if (!path_exists) {
                StdOut.println("no");
            } else {
                StdOut.print("yes: ");
                Iterable<Integer> path = dfspaths.pathTo(i);
                for (int n : path)
                    StdOut.print(" " + n );
                StdOut.println("");
            }
        }


    }

}