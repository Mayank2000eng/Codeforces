import java.util.*;

public class tle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String s= sc.nextLine();
            int i=0;
            long max=0;
            long c=0;
            while(i<s.length()){
                while(i<s.length()&&s.charAt(i)=='1') {c++;i++;}
                max=Math.max(c,max);
                c=0;
                i++;
            }
            long j=-1;
            i=0;
            int c_0=0;
            while(i<s.length()){
                if(s.charAt(i)=='0') {j=i;c_0++;}
                i++;
            }
            if(!(j==-1||(j==s.length()-1))){
                String r=s+s;
                c=0;
                for(int t1=(int)j+1;t1<r.length();t1++){
                    if(r.charAt(t1)=='0') break;
                    c++;
                }
                max=Math.max(max,c);
            }
            if(c_0==0||max==1||max==0){
                System.out.println(max*max);
            }
            else{
               long ans=0;
               j=1;
               while(j<=max){
                   long j1=max-j+1;
                   ans=Math.max(ans,j*j1);
                   j++;
               }
                System.out.println(ans);
            }
        }
    }
}
