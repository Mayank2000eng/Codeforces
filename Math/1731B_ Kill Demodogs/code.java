import java.util.*;

public class tle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt();
            long ans=0;
            long mod=1000000007;
            long r1=((4*n)%mod-1);
            long r2=(((n*n)%mod)+n)%mod;
            ans=(r1*r2)%mod;
            ans=((ans%mod)*(337%mod))%mod;
            System.out.println(ans);
        }
    }
}
