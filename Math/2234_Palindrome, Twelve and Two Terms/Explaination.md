# Codeforces - Palindrome, Twelve and Two Terms

## 📝 Problem Explanation

You are given a large positive integer $n$ ($1 \le n \le 10^{18}$). The goal is to break $n$ down into the sum of two non-negative integers, $a$ and $b$, such that:
1. $a + b = n$
2. $a$ is a **palindrome** (reads the same forwards and backwards, e.g., `0`, `5`, `11`, `121`).
3. $b$ is **divisible by 12** ($b \pmod{12} = 0$).

If such a pair exists, print $a$ and $b$. If it's impossible, print `-1`.

---

## 💡 Mathematical Approach & Optimization

The constraints on $n$ are massive ($10^{18}$), which means any digit-based Dynamic Programming (Digit DP) or standard brute-force search checking numbers one by one will end up with a **Time Limit Exceeded (TLE)** verdict. 

Instead, we can break this problem down entirely using basic modular arithmetic.

### 1. The Modulo 12 Reduction
We are given the equation:
$$a + b = n$$

Since $b$ must be a multiple of 12, we know that $b \equiv 0 \pmod{12}$. If we take the entire equation modulo 12, it simplifies beautifully:
$$a + 0 \equiv n \pmod{12}$$
$$a \equiv n \pmod{12}$$

This tells us that **the palindrome $a$ must leave the exact same remainder as $n$ when divided by 12.**

### 2. Checking the Smallest Candidates
Because $b$ must be non-negative ($b \ge 0$), we know that $a$ must be less than or equal to $n$ ($a \le n$). To make our life easy, we want to find the smallest possible value for $a$. 

Let's look at the first 12 non-negative integers ($0$ to $11$) and see if they are palindromes:

| Value ($a$) | Is Palindrome? | $a \pmod{12}$ |
| :---: | :---: | :---: |
| **0** | Yes | 0 |
| **1** | Yes | 1 |
| **2** | Yes | 2 |
| **3** | Yes | 3 |
| **4** | Yes | 4 |
| **5** | Yes | 5 |
| **6** | Yes | 6 |
| **7** | Yes | 7 |
| **8** | Yes | 8 |
| **9** | Yes | 9 |
| 10 | ❌ **No** | 10 |
| **11** | Yes | 11 |

Every single remainder from `0` to `11` is inherently a palindrome except for **`10`**.

### 3. Handling the Remainder 10 Exception
If $n \pmod{12} == 10$, we cannot choose $a = 10$ because `10` is not a palindrome. 

To find the next smallest number that gives a remainder of 10 when divided by 12, we add 12 to it:
$$10 + 12 = 22$$

Since **`22`** is a valid palindrome and $22 \pmod{12} == 10$, it becomes our perfect alternate candidate.

### 🚀 Final O(1) Algorithm

1. Calculate the remainder: `remainder = n % 12`.
2. Set our palindrome candidate `a = remainder`.
3. If `remainder == 10`, override it to `a = 22`.
4. Check if $a \le n$. 
   * If yes, a valid pair exists! Output `a` and `b = n - a`.
   * If no (which only happens when $n = 10$), it is impossible. Output `-1`.

---

## ⏱️ Complexity Analysis

* **Time Complexity:** $\mathcal{O}(1)$ per testcase. There are no loops or complex functions; the math maps the answer instantly.
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space as we only use a few variables to store the remainders.
