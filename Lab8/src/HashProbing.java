import java.util.Random;

public class HashProbing {

    public static void arrayCreationCheck(int[] hashTable, int N) {
        // unit testing
        int zerocount = 0;
        int count = 0;
        for (int i = 0; i < N; ++i) {
            if (hashTable[i] != 0) ++count;
        }
        for (int i = 0; i < N; ++i) {
            if (hashTable[i] == 0) ++zerocount;
        }
        System.out.println(zerocount);
        System.out.println(count);
    }

    public static int[] createHashLikeTable(int N) {

        Random random = new Random();

        // create "hashtable" of size N
        int[] hashTable = new int[N];

        // insert N/2 random int keys
        for (int i = 0; i < (N / 2); ++i) {

            // generate random number bound by N - 1 and add 1 so that we never get a 0
            // this is important for checking if array is empty
            int key = random.nextInt(N-1) + 1;
            int index = key;

            // linear probe while spot is not empty
            while (hashTable[index] != 0) {

                // check for at end of array, wrap if so
                if (index == N-1) { index = 0; }
                else { ++index; }
            }

            // spot is empty insert key
            hashTable[index] = key;
        }
        return hashTable;
    }


    public static float searchMiss(int[] hashTable, int N) {
        Random random = new Random();

        int[] probeTable = new int[N/2];

        // insert N/2 random int keys
        for (int i = 0; i < (N / 2); ++i) {

            // generate random number bound by N - 1 and add 1 so that we never get a 0
            // this is important for checking if array is empty
            int key = random.nextInt(N-1) + 1;
            int index = key;
            int probe = 0;

            // linear probe while spot is not empty
            while (hashTable[index] != 0) {

                // increment probe count for each collision
                ++probe;

                // check for at end of array, wrap if so
                if (index == N-1) { index = 0; }
                else { ++index; }
            }

            // increment probe for insertion and add to table for stats
            ++probe;
            probeTable[i] = probe;
        }

        // calc average probe number
        int total = 0;
        for (int i = 0; i < probeTable.length; ++i) {
            total += probeTable[i];
        }
        float average = total / (float)probeTable.length;

        return average;
    }

    public static void main(String[] args) {

        // conduct experiment over range N = 10^3 to 10^6
        for (int N = (int) 10e3; N <= 10e6; N *= 10) {

            int[] hashTable = createHashLikeTable(N);
            float average = searchMiss(hashTable, N);

            System.out.println("Average probes for " + N + " is " + average);
        }
    }
}
