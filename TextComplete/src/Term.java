import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String Query, long Weight) {
        this.query = Query;
        this.weight = Weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                if (v.weight > w.weight) { return -1; }
                else if ( v.weight < w.weight) { return 1; }
                return 0;
            }
        };
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new Comparator<Term>() {
            @Override
            public int compare(Term term1, Term term2) {

                // check the first r char's as long as r chars exist
                for (int i = 0; i < r; ++i) {

                    // need to prevent index out of range error
                    if (i < term1.query.length() & i < term2.query.length()) {
                        if (term1.query.charAt(i) < term2.query.charAt(i)) { return 1; }
                        else if (term1.query.charAt(i) > term2.query.charAt(i)) { return -1; }
                    }
                }
                return 0;
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        // find the smallest length
        int smallest = Math.min(this.query.length(), that.query.length());

        // compare two string up to length of smallest string
        for (int i = 0; i < smallest; ++i) {
            if (this.query.charAt(i) > that.query.charAt(i)) { return -1; }
            else if (this.query.charAt(i) < that.query.charAt(i)) { return 1; }
        }

        if (this.query.length() < that.query.length()) { return 1; }
        else if (this.query.length() > that.query.length()) { return -1; }
        return 0;
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "  " + this.query;
    }

    // unit testing (you should have some Unit Testing here to confirm that your methods work)
    public static void main(String[] args) {

        Term term1 = new Term("Greg", (long) 100.20);
        Term term2 = new Term("Greg", (long) 105.20);
        Term term3 = new Term("Thor", (long) 99999.999);
        Term term4 = new Term("Thors", (long) 999999.99);
        Comparator byrev = byReverseWeightOrder();
        Comparator byprefix = byPrefixOrder(5);

        System.out.println( byrev.compare(term1, term2));
        System.out.println(byprefix.compare(term3, term4));

        System.out.println(term1.compareTo(term2));


    }
}