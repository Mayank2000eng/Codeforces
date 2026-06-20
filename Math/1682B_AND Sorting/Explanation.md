markdown_content = """
# Problem Analysis: B. AND Sorting

## Problem Overview
You are given a permutation $p$ of integers from $0$ to $n-1$. The permutation is not initially sorted. A permutation is called **$X$-sortable** if you can sort it by repeatedly swapping elements $p_i$ and $p_j$ such that $p_i \\& p_j = X$. 

The goal is to find the **maximum possible value of $X$** that allows the permutation to be sorted.

## Logical Insight
The key to this problem lies in observing which elements must move from their current positions to their sorted positions (where $p_i = i$).

1.  **Condition for Movement:** An element $p_i$ that is currently not in its correct sorted position ($p_i \neq i$) **must** be involved in at least one swap to reach its correct position.
2.  **Bitwise AND Constraint:** Any swap between two elements $p_i$ and $p_j$ is only allowed if $p_i \\& p_j = X$. 
3.  **Transitivity:** If we need to move several elements that are not in their correct positions, and they must all be sorted, they must all effectively be "connected" via this $X$ operation. If we choose a specific $X$, then for every element $v$ that is not in its correct position ($p_v \neq v$), the bitwise representation of $v$ must satisfy $v \\& X = X$. This is equivalent to saying that $X$ must be a submask of $v$ (i.e., all bits set in $X$ must also be set in $v$).
4.  **Maximizing $X$:** Since this condition must hold for *all* elements $p_i$ that are not already in their correct position ($p_i \neq i$), $X$ must be a submask of *every* such element. Therefore, the maximum $X$ is the bitwise AND of all elements $p_i$ such that $p_i \neq i$.

## Solution Implementation
To implement this, we iterate through the array, identify all elements where $p_i \neq i$, and compute the cumulative bitwise AND of these elements.

### Java Solution
```java
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            // Initialize ans to -1 (representing all bits set)
            int ans = -1;
            
            for (int i = 0; i < n; i++) {
                // If element is not in its sorted position
                if (arr[i] != i) {
                    if (ans == -1) {
                        ans = arr[i];
                    } else {
                        // Keep narrowing down the possible bits
                        ans = ans & arr[i];
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
