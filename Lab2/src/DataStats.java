public class DataStats {

    private double[] dataArray;
    private int nAdded;

    // set up an array (to accept up to N doubles) and other member variables
    public DataStats(int N) {

        dataArray = new double[N];
        nAdded = 0;

        for (int i = 0; i < N; ++i)
        { dataArray[i] = 0.0; }
    }

    //compute and return the mean of the set of numbers added so far
    public double mean() {
        double sum = 0.0;
        double mean = 0.0;

        for (int i = 0; i < nAdded; ++i)
        { sum += dataArray[i]; }

        mean = sum / nAdded;
        return mean;
    }

    //append number to the set; throw error if more than N numbers added
    public void append(double in) {

        if (nAdded >= dataArray.length) {
            try {
                throw new IllegalAccessException("Too many doubles dummy!\n" +
                                                 "Printing stack trace and exiting program.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        dataArray[nAdded] = in;
        ++nAdded;
    }

}
