import java.util.*;

public class tle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] a1 = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                a1[i] = sc.nextInt();
            }
            int i=0,j=n-1;
            while(i<n&&(a[i]==a1[i])) i++;
            while(j>=0&&(a[j]==a1[j])) j--;
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            for(int i1=i;i1<=j;i1++){
                min=Math.min(min,a1[i1]);
                max=Math.max(max,a1[i1]);
            }
            int i1=i-1;
            while(i1>=0&&(a[i1]<=min)){
                min=a[i1];
                i--;
                i1--;
            }
            i1=j+1;
            while(i1<n&&(a[i1]>=max)){
                max=a[i1];
                j++;
                i1++;
            }
            System.out.println(i+1+" "+(j+1));
        }
    }
}
