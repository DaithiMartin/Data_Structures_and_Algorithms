import java.util.Random;

public class ComputePi {

    public static double computePI(int n) {
        double PI = 0.0;
        int inside = 0;
        double x = 0.0;
        double y= 0.0;
        Random randNum= new Random();

        for (int i = 0; i < n; ++i) {
            x = randNum.nextDouble();
            y = randNum.nextDouble();

            if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= 1) {
                ++inside;
            }
        }
        PI = (4.0 * inside) / n;
        return PI;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        double pi = computePI(n);

        System.out.printf("The estimate of Pi based on %d random points is: %f", n, pi);
    }

}
