public class DataStatsUser {

    public static void main (String[] argv) {
        DataStats d = new DataStats(6);
        d.append(1.1);
        d.append(2.1);
        d.append(3.1);
        System.out.println("final so far is: " + d.mean());
        d.append(4.1);
        d.append(5.1);
        d.append(6.1);
        System.out.println("final mean is: " + d.mean());

//        added by Daithi to allow for error error testing
        d.append(6.1);
    }
}
