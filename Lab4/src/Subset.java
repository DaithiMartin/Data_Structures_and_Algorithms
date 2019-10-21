import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Subset {

    public static void main(String[] args) throws IOException {
        RandomizedBag set = new RandomizedBag();
        int subsetSize = Integer.parseInt(args[0]);
        String line;

        // set scanner to read from standard input
        Scanner scanner = new Scanner(System.in);

        // read from standard input and put items into bag
        try {
            while ((line = scanner.next()) != null) {
                set.put(line); } }

        // catch exception from the reader running out of elements and print subset
        catch (NoSuchElementException e) {
            for (int i = 0; i < subsetSize; ++ i) {
                System.out.println(set.get()); } }
    }
}
