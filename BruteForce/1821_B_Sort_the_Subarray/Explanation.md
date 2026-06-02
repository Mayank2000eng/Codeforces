# Codeforces 1821B - Sort the Subarray

## Problem Overview

The problem asks us to find the **maximum possible length** of a subarray $[l, r]$ that was sorted in an original array $A$ to produce a modified array $A'$. We are given both the original array $A$ and the modified array $A'$. 

The catch is that the sorting operation *definitely* happened, and it might have included elements that were already in their correct relative order, thereby expanding the boundary of the sorted subarray. Our goal is to find the absolute widest valid indices $1$-indexed $[l, r]$ that could have been chosen.

---

## My Approach (Two-Pointer Expansion)

The core idea of my solution is to find the **guaranteed core** of the sorted subarray first, and then **greedily expand** it outward as far as possible. 

### Step-by-Step Breakdown

### 1. Identify the Mismatch Core
Since $A'$ is formed by sorting a single subarray of $A$, any element that changed its position *must* be inside the chosen subarray. 
* I use two pointers (`i` from the left, `j` from the right) to find the first and last positions where $A[i] \neq A'[i]$.
* This $[i, j]$ window gives us the minimum necessary subarray that *had* to be sorted.

### 2. Find the Range Bounds
Before expanding, I need to know the current minimum and maximum values within our identified core subarray in $A'$ (which is already sorted).
* `min` stores the smallest element in the current range.
* `max` stores the largest element in the current range.

### 3. Leftward Expansion
We can extend the left boundary $i$ to the left (i.e., `i--`) if the element just outside our current window ($A[i-1]$) is **less than or equal to** our current minimum element. 
* If $A[i-1] \le \text{min}$, including it in the subarray won't break the sorted property.
* As we expand, we update our `min` to be this new element.

### 4. Rightward Expansion
Similarly, we can extend the right boundary $j$ to the right (i.e., `j++`) if the element just outside ($A[j+1]$) is **greater than or equal to** our current maximum element.
* If $A[j+1] \ge \text{max}$, including it keeps the subarray valid and sorted.
* We update our `max` to be this new element.

### 5. Output
Since Codeforces uses $1$-based indexing, the final answer is simply `(i + 1)` and `(j + 1)`.

---

## Why This Approach Works (Proof of Correctness)

1. **Safety of the Core:** The initial mismatch pointers `i` and `j` catch the absolute boundaries of actual elements that moved. Any valid subarray *must* at least cover this range.
2. **Optimality of Greedy Expansion:** Because the array $A'$ is already sorted within our window, extending the window to an element $x$ on the left is valid if and only if $x \le \text{all elements currently in the window}$. Since the window is sorted, checking $x \le \text{min}$ is both necessary and sufficient. The same logic perfectly applies to the right side with $x \ge \text{max}$.
3. **Maximality:** By expanding one element at a time until we hit a violation, we guarantee that we find the longest possible contiguous subarray.

## Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N)$ per testcase. We traverse the array a constant number of times (to find mismatches, find min/max, and expand). This easily passes well within the time limit.
* **Space Complexity:** $\mathcal{O}(N)$ to store the arrays $A$ and $A'$.
