# Codeforces - B. Lunatic Never Content

## Problem Description

Given an array $a$ of $n$ non-negative integers, we need to find the **largest positive integer $x$** such that after replacing every element $a[i]$ with $a[i] \pmod x$, the resulting array becomes a **palindrome**. 

An array is a palindrome if it reads the same backward as forward. Formally, for every index $i$ (0-indexed), the condition $a[i] = a[n - 1 - i]$ must hold.

If $x$ can be arbitrarily large (i.e., the array is already a palindrome for any value of $x$), the program should output `0`.

---

## Explanation of the Approach

The solution utilizes a highly optimized **Two-Pointer Approach** combined with the **Euclidean Algorithm for GCD** to solve the problem in $O(n \log(\max A))$ time.

1. **Two-Pointer Traversal:** We initialize two pointers: `i = 0` at the start of the array and `j = n - 1` at the end. We move them toward the center (`i++`, `j--`) to compare symmetric pairs.
2. **Isolating Differences:** For each symmetric pair, if $a[i] == a[j]$, their values are already identical, so they do not place any restrictions on our choice of $x$. We safely skip them. If $a[i] \neq a[j]$, we compute their absolute difference: $|a[i] - a[j]|$.
3. **Cumulative GCD:** We maintain a running Greatest Common Divisor (`ans`). 
   - The first time we encounter a mismatched pair, we initialize `ans` with that pair's absolute difference.
   - For every subsequent mismatched pair, we update `ans` to be the GCD of the current `ans` and the new absolute difference.
4. **Edge Cases:** If the array length $n = 1$, or if the array is already a perfect palindrome, no updates to `ans` are made (or it stays `0`), which correctly outputs `0` as required by the problem statement.

---

## Why This Approach Works (Mathematical Proof)

The core logic relies on fundamental principles of modular arithmetic and number theory.

### 1. The Condition for a Palindrome
For the final array to be a palindrome, every symmetric pair of elements must be equal after taking the modulo $x$. For any valid index $i$ and its counterpart $j = n - 1 - i$:

$$a[i] \pmod x = a[j] \pmod x$$

### 2. Transition to Divisibility
By definition, if two numbers leave the same remainder when divided by $x$, their difference must be perfectly divisible by $x$. We can rewrite the equation as:

$$(a[i] - a[j]) \pmod x = 0$$

This mathematically implies that $x$ must be a **divisor (factor)** of the absolute difference between the two symmetric elements:

$$x \mid |a[i] - a[j]|$$

### 3. Finding the Global Maximum ($x$)
This divisibility rule cannot just apply to a single pair; it must satisfy **every single symmetric pair** across the entire array simultaneously. 

If we collect the absolute differences of all mismatched symmetric pairs:
- $D_1 = |a[0] - a[n-1]|$
- $D_2 = |a[1] - a[n-2]|$
- $D_3 = |a[2] - a[n-3]|$

Our variable $x$ must be a *common divisor* of all these differences ($D_1, D_2, D_3, \dots$). 

Since the problem explicitly demands the **biggest possible $x$**, we must find the **Greatest Common Divisor (GCD)** of all these calculated differences:

$$x = \text{GCD}(D_1, D_2, D_3, \dots)$$

### 4. Why Skipping Equal Pairs works
When $a[i] == a[j]$, the difference is $0$. Mathematically, $\text{GCD}(\text{number}, 0) = \text{number}$. Adding a $0$ into a cumulative GCD calculation changes absolutely nothing. Therefore, skipping identical elements is a valid and efficient optimization that bypasses redundant calculations.

By the time the two pointers meet in the middle, the computed GCD represents the largest possible integer $x$ that forces all symmetric elements to match, perfectly solving the problem.

---

## Complexity

- **Time Complexity:** $O(n \log(\max A))$ per testcase. The two pointers traverse the array in $O(n)$ steps, and calculating the GCD using the Euclidean algorithm takes $O(\log(\max A))$ steps. Given $\sum n \le 10^5$, this comfortably runs well within the 2.0-second time limit.
- **Space Complexity:** $O(n)$ to store the array elements.
