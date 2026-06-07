import java.util.*;

public class tle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt();

        while (t-- > 0) {
            long n = sc.nextLong();
            long remainder = n % 12;
            long a = remainder;

            if (remainder == 10) {
                a = 22;
            }

            if (a <= n) {
                System.out.println(a + " " + (n - a));
            } else {
                System.out.println(-1);
            }
        }
    }
}
