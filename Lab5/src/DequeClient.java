public class DequeClient {

    /* A very simple client for your Deque
     * implementation, designed to show that it works
     * (or doesn't)
     */
    public static void main(String[] args) {

        Deque<String> d = new Deque<String>();

        d.addLast("I");
        d.addLast("once");
        d.addLast("danced");
        System.out.print( d.removeFirst() + " ");
        d.addFirst("upon");
        d.addFirst("a");
        d.addFirst("dream");
        d.addFirst("with");
        System.out.print( d.removeLast() + " ");
        d.addLast("you");
        System.out.print( d.removeFirst() + " ");
        while (!d.isEmpty()) {
            System.out.print( d.removeLast() + " ");
        }
    }

}