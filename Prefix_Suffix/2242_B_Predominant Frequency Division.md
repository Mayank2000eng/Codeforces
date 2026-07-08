# B. Predominant Frequency Division

## 1. Deep Problem Analysis & Breakdown

The problem requires us to determine whether an array $a$ of size $n$ containing only elements `1`, `2`, and `3` can be divided into exactly **three contiguous, non-empty segments**: Left Part, Middle Part, and Right Part. 

Each part must satisfy a relative frequency rule based on the element values:
- **Left Part:** The number of elements greater than `1` (which can only be `2` and `3`) must be at most half of the part's total size. This mathematically transforms into:
  $$\text{count}(1) \ge \text{count}(2) + \text{count}(3)$$
- **Middle Part:** The number of elements greater than `2` (which can only be `3`) must be at most half of the part's total size. This mathematically transforms into:
  $$\text{count}(1) + \text{count}(2) \ge \text{count}(3)$$
- **Right Part:** The number of elements greater than `3` must be at most half of the part's total size. Since there are no elements greater than `3`, this condition is always `true`. The only true restriction is that this part must be **non-empty** (consisting of at least 1 element).

---

## 2. Core Logic & Mathematical Intuition

To avoid checking all quadratic partition combinations $\mathcal{O}(n^2)$ which would cause a Time Limit Exceeded (TLE) verdict, my solution utilizes **Prefix Balance Tracking** to evaluate valid splits linearly in $\mathcal{O}(n)$ time.

### Prefix Multipliers
Instead of manually counting frequencies, we convert the conditions into running scores:
- `pref1[i]`: Tracks the relative score of `1` against `2` and `3`. If $a[i] == 1$, the balance gains $+1$, otherwise it drops by $-1$. A valid ending for the **Left Part** occurs at any index $i$ where `pref1[i] >= 0`.
- `pref12[i]`: Tracks the relative score of `1` and `2` against `3`. If $a[i] == 3$, the balance drops by $-1$, otherwise it gains $+1$. For a **Middle Part** extending from some index $j+1$ to $i$, the validity check simplifies to:
  $$\text{pref12}[i] - \text{pref12}[j] \ge 0 \implies \text{pref12}[i] \ge \text{pref12}[j]$$

### The Greedy Minimum Match
To ensure the inequality `pref12[i] >= pref12[j]` holds successfully, my code maintains a tracking tracker `min` representing the **absolute minimum value of `pref12[j]`** encountered so far among all indices $j$ where a Left Part was legally valid (`pref1[j] >= 0`). 

By comparing the current `pref12[i]` against this optimal historical `min`, we maximize our chances of finding a valid middle segment cut.

---

## 3. Optimized Code Implementation

The following code implements the full solution. Note that to pass the tight 2.0-second time limit with $2 \cdot 10^5$ inputs, standard `Scanner` input should be replaced with `BufferedReader` to prevent I/O-bound performance throttling.

```java
import java.util.*;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            // Step 1: Compute Left Part balance scores
            int[] pref1 = new int[n];
            for(int i = 0; i < n; i++){
                if(i == 0){
                    if(arr[i] == 1) pref1[i]++;
                    else pref1[i]--;
                } else {
                    if(arr[i] == 1) pref1[i] = pref1[i - 1] + 1;
                    else pref1[i] = pref1[i - 1] - 1;
                }
            }
            
            // Step 2: Compute Middle Part balance scores
            int[] pref12 = new int[n];
            for(int i = 0; i < n; i++){
                if(i == 0){
                    if(arr[i] == 3) pref12[i]--;
                    else pref12[i]++;
                } else {
                    if(arr[i] == 3) pref12[i] = pref12[i - 1] - 1;
                    else pref12[i] = pref12[i - 1] + 1;
                }
            }
            
            int min = Integer.MAX_VALUE;
            int flag = 0;
            int ans = 0;
            
            // Step 3: Check combinations iteratively up to n-2 (leaving 1 item for Right Part)
            for(int i = 0; i < n - 1; i++){
                if(flag == 1 && pref12[i] - min >= 0){
                    ans = 1;
                    break;
                }
                if(pref1[i] >= 0 && pref12[i] < min){
                    min = pref12[i];
                    flag = 1;
                }
            }
            
            if(ans == 1)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
