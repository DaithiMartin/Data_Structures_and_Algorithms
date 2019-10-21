import java.util.Comparator;

public class BinarySearchDeluxe {

    // FIXME: NEED TO FIX TO GUARD AGAINST OUT OF INDEX ERRORS THAT OCCUR DURING THE FIRST CHECK  ON BOTH FUNCTIONS
    // FIXME: FUCKING EDGE CASES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if keys are equal and one index to the left is not we are at the first occurrence
            if (comparator.compare(key, a[mid]) == 0 & comparator.compare(key, a[mid - 1]) != 0) {
                return mid; }

            // if key <= a[mid] then the first occurrence is still to the left
            else if (comparator.compare(key, a[mid]) == 0  | comparator.compare(key, a[mid]) == -1) {
                hi = mid -1; }

            // if key > a[mid] then the first occurrence is still to the right
            else if (comparator.compare(key, a[mid]) == 1) {
                lo = mid + 1; }
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if keys are equal and one index to the right is not we are at the last occurrence
            if (comparator.compare(key, a[mid]) == 0 & comparator.compare(key, a[mid + 1]) != 0) {
                return mid;
            }

            // if key >= a[mid] then the last occurrence is still to the right
            else if (comparator.compare(key, a[mid]) == 1 | comparator.compare(key, a[mid]) == 0) {
                lo = mid + 1;
            }

            // if key < a[mid] then the last occurrence is still to the left
            else if (comparator.compare(key, a[mid]) == -1) {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // unit testing (you should have some Unit Testing here to confirm that your methods work)
    public static void main(String[] args) {

    }
}