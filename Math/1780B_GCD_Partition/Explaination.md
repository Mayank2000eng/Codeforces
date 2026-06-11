# GCD Partition - Explanation & Solution

## 📝 Problem Statement

Given an array $a$ of length $n$, our goal is to split the array into $k$ contiguous subsegments ($k > 1$) such that the **Greatest Common Divisor (GCD)** of the sums of these subsegments is maximized. 

For a chosen split into $k$ subsegments, let the sums of these subsegments be $b_1, b_2, \dots, b_k$. We want to maximize:
$$\text{Score} = \gcd(b_1, b_2, \dots, b_k)$$

### Constraints
* $1 \le t \le 10^4$ (Number of test cases)
* $2 \le n \le 2 \cdot 10^5$ (Length of the array)
* $1 \le a_i \le 10^9$ (Elements of the array)
* The sum of $n$ over all test cases does not exceed $2 \cdot 10^5$.

---

## 💡 Key Observation & Mathematical Intuition

At first glance, the problem allows us to split the array into *any* number of subsegments $k > 1$. Exploring all possible ways to split an array into multiple parts would lead to an exponential time complexity, which is far too slow. 

However, we can simplify this using a core property of the GCD function:

> **The GCD Property:**
> For any integers $x, y, z$, if a number $g$ divides both $x$ and $y$, it also divides their sum $x + y$. Mathematically:
> $$\gcd(x, y, z) \le \gcd(x + y, z)$$

### What does this mean for our problem?
Suppose we partition the array into $k$ subsegments with sums $b_1, b_2, \dots, b_k$. If we decide to merge two adjacent subsegments (say $b_1$ and $b_2$), the new subsegment sum becomes $(b_1 + b_2)$. 

Because $\gcd(b_1, b_2, \dots, b_k)$ always divides $\gcd(b_1 + b_2, \dots, b_k)$, **merging subsegments will either keep the GCD the same or make it larger**. It will never decrease the GCD.

To maximize our score, we should reduce the number of subsegments as much as possible. Since the problem requires $k > 1$, the optimal strategy is to always split the array into exactly **$k = 2$ subsegments**.

Every valid $k$-split can be reduced to a 2-split that yields a score greater than or equal to the original split. Thus, the problem reduces to finding a prefix sum $S_{\text{prefix}}$ and a remaining suffix sum $S_{\text{suffix}}$ such that $\gcd(S_{\text{prefix}}, S_{\text{suffix}})$ is maximized.

Since $S_{\text{suffix}} = \text{Total Sum} - S_{\text{prefix}}$, we can also write this as:
$$\max_{1 \le i < n} \left( \gcd(S_i, \text{Total Sum} - S_i) \right)$$
*(where $S_i$ is the sum of the first $i$ elements)*

---

## 🚀 Optimal Approach (Prefix Sums + GCD)

1. **Calculate Total Sum:** We first compute the total sum of all elements in the array.
2. **Iterate through Split Points:** We iterate through the array from index $0$ to $n-2$ (ensuring at least one element remains in the second part).
3. **Maintain Prefix Sum:** As we loop, we keep track of the running prefix sum. The suffix sum is simply $(\text{Total Sum} - \text{Prefix Sum})$.
4. **Maximize GCD:** At each split point, we calculate the GCD of the prefix sum and suffix sum, updating our maximum score found so far.

Using Fast I/O (like `BufferedReader` and `StringTokenizer`) in Java ensures that we process the large input within the strict 1-second time limit.

---

## 💻 Java Source Code

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            long[] a = new long[n];
            long totalSum = 0;
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
                totalSum += a[i];
            }
            
            long maxGcd = 1;
            long currentPrefixSum = 0;
            
            // We loop up to n - 2 because the second subsegment must have at least 1 element
            for (int i = 0; i < n - 1; i++) {
                currentPrefixSum += a[i];
                long currentSuffixSum = totalSum - currentPrefixSum;
                
                maxGcd = Math.max(maxGcd, gcd(currentPrefixSum, currentSuffixSum));
            }
            
            sb.append(maxGcd).append("\n");
        }
        System.out.print(sb);
    }
    
    // Iterative Euclidean algorithm to calculate GCD
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
