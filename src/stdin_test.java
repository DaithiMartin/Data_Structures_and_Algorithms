import edu.princeton.cs.algs4.StdIn;

public class stdin_test {

    public static void main(String[] args) {
//        LinkedQueue<String> queue = new LinkedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
//            if (!item.equals("-"))
//                queue.enqueue(item);
//            else if (!queue.isEmpty())
//                StdOut.print(queue.dequeue() + " ");
        }
//        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
