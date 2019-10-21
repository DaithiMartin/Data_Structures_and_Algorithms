/*
Basic implementation of Quickfind algorithm.

the main issue with this algorithm is that is operates in quadratic time.
this is dude to the union operation needing to change many entries if large sets exist.

*/

public class QuickFind {

//    defines basic array that is not to be manipulated by the client
    private int[] unionArray;

//    constructor for array type. allocates memory and populates it with starting values
    public QuickFind(int size) {

        unionArray = new int[size];
        for (int i = 0; i < size; ++i) {
            unionArray[i] = i;
        }
    }

//    checks if element a and b are in the same set
    public boolean areConnected(int a, int b) {
        return unionArray[a] == unionArray[b];
    }

//    creates a union between the sets of a and b
    public void union(int a, int b) {
        int aID = unionArray[a];
        int bID = unionArray[b];

        for (int i = 0; i < unionArray.length; ++i) {
            if (unionArray[i] == bID) {
                unionArray[i] = aID;
            }
        }
    }
}
