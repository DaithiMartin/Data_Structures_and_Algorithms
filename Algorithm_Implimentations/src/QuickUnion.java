/*
Basic implementation of QuickUnion algorithm.

this algorithm operates faster in the union method because it changes
fewer entries in the arrayID[].

the data structure is more complicated intellectually but the implementation
appears to be simpler.

improved from basic implementation to balance tree size using tree size weighting when combining roots.

FIXME:ADD NODE COMPRESSION TO findRoot()

 */

public class QuickUnion {

    private int[] arrayID;
    private int[] sizeArray;

    public QuickUnion(int size) {
        arrayID = new int[size];
        for (int i = 0; i < size; ++i) {
            arrayID[i] = i;
        }

        sizeArray = new int[size];
        for (int i = 0; i < size; ++i) {
            sizeArray[i] = 1;
        }

    }

    private int findRoot(int i) {
        while (i != arrayID[i]) {
            i = arrayID[i];
//            FIXME:ADD NODE COMPRESSION
        }
        return i;
    }

    public boolean areConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    public void union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);

        if (rootA == rootB) {return;}

        if (sizeArray[rootA] < sizeArray[rootB]){ arrayID[rootA] = rootB; sizeArray[rootB] += sizeArray[rootA];}
        else                                    { arrayID[rootB] = rootA; sizeArray[rootA] += sizeArray[rootB];}

    }


}
