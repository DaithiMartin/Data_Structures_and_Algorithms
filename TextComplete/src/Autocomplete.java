import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {

    Term[] termArray;
    Comparator byReverseWeight = Term.byReverseWeightOrder();
    BinarySearchDeluxe binarySearch = new BinarySearchDeluxe();

    // initializes data type
    // takes array of terms and sorts it in lexicographic
    // uses mergeSort: stable but not in place
    public Autocomplete(Term[] terms) {

        // assigns reference to same memory location to save memory and computations
        termArray = terms;
        Arrays.sort(termArray);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    //return an empty array if no matches
    public Term[] allMatches(String prefix) {

        // have to change prefix to type term so that we can use .compare()
        Term prefixTerm = new Term(prefix, 0);

        // initialize comparator
        Comparator byPrefixOrder = Term.byPrefixOrder(prefix.length());

        // find first and last index
        int fistIndex = binarySearch.firstIndexOf(termArray, prefixTerm, byPrefixOrder);
        int lastIndex = binarySearch.lastIndexOf(termArray, prefixTerm, byPrefixOrder);

        // if prefix found, copy matching terms to new array then sort in reverse weight order
        if (fistIndex != -1 & lastIndex != -1) {
            int newArraySIze = (lastIndex + 1) - fistIndex;
            Term[] matches = new Term[newArraySIze];

            for (int i = 0; i < newArraySIze; ++i) {
                matches[i] = termArray[fistIndex + i];
            }

            Arrays.sort(matches, byReverseWeight);
            return matches;
        }

        else {
            Term[] noMatches = new Term[0];
            return noMatches;
        }
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix){
        Comparator byPrefixOrder = Term.byPrefixOrder(prefix.length());

        // have to change prefix to type term so that we can use .compare()
        Term prefixTerm = new Term(prefix, 0);

        int fistIndex = binarySearch.firstIndexOf(termArray, prefixTerm, byPrefixOrder);
        int lastIndex = binarySearch.lastIndexOf(termArray, prefixTerm, byPrefixOrder);

        return (lastIndex + 1) - fistIndex;
    }


    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}