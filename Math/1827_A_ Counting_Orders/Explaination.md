# 🛑 Problem Explanation

### The problem asks us to find the number of ways to reorder an array **$a$** of **$n$** distinct integers such that every element in the reordered array is strictly greater than the element at the matching index in another array **$b$** of size **$n$** (i.e., $a_i > b_i$ for all $1 \le i \le n$).

### Since all elements in **$a$** are unique, two reorderings are considered different if their resulting sequences look different. The final answer can be very large, so we must output it modulo **$10^9 + 7$**.

---

# 🔍 Key Insight

## 📌 1. Constraints Analysis

### **A very large element in $b$** has very few elements in $a$ that can beat it.

### **A very small element in $b$** can be beaten by almost any element in $a$.

## 📌 2. Strategy

### To satisfy the condition $a_i > b_i$, it is highly strategic to look at the constraints from the most restrictive elements to the least restrictive elements.

---

# 💡 Algorithmic Approach

### We can solve this problem efficiently using a **Sorting + Greedy Combinatorics** approach in $\mathcal{O}(n \log n)$ time.

## 🔄 Step 1: Sorting

### First, we sort both arrays **$a$** and **$b$** in **descending order** (largest to smallest). 

### Sorting allows us to process the elements of $b$ from largest to smallest. By tackling the largest elements of $b$ first, we are resolving the hardest constraints first.

## 📊 Step 2: Counting Valid Options

### Let's iterate through the sorted array **$b$** using an index $i$ (from $0$ to $n-1$):

### **Count Valid Elements:** For the current element $b_i$, we count how many elements in the entire array $a$ are strictly greater than $b_i$. Let's call this count **$count_i$**.

### **Property of Sorted Arrays:** Because array $b$ is sorted in descending order, any element in $a$ that is valid for $b_i$ will **also** be valid for all subsequent smaller elements in $b$ ($b_{i+1}, b_{i+2}, \dots$).

### **Consumption:** When we actually assign an element from $a$ to pair with $b_i$, that element is consumed and cannot be reused for future elements of $b$.

## 🧮 Step 3: Permutation Math

### For the largest element $b_0$, we have **$count_0$** choices from array $a$.

### For the second largest element $b_1$, we theoretically have $count_1$ choices. However, **1** of those valid elements has already been used up by $b_0$. So, we only have **$(count_1 - 1)$** available choices.

### For the $i$-th element $b_i$, exactly $i$ elements from $a$ have already been assigned to the larger elements of $b$ before it. 

## 📐 Formula Derivation

$$\text{Choices for } b_i = count_i - i$$

## ✖️ Step 4: Rule of Product

### By the fundamental counting principle, the total number of valid ways to reorder the array is the product of the independent choices at each step:

$$\text{Total Ways} = \prod_{i=0}^{n-1} (count_i - i) \pmod{10^9+7}$$

> ⚠️ **Edge Case:** If at any point $(count_i - i) \le 0$, it means there are not enough elements in $a$ to beat $b_i$. The entire product becomes `0`, and we can immediately return `0`.

---

# 🏎️ Efficiency & Optimization

### Instead of counting $count_i$ from scratch for every element using a nested loop—which would take $\mathcal{O}(n^2)$ time and cause a Time Limit Exceeded (TLE)—we utilize a **Two-Pointer technique**.

### Since both arrays are sorted, as we move $b_i$ from largest to smallest, the pointer tracking valid elements in $a$ only moves forward. This optimizes the counting phase to a linear $\mathcal{O}(n)$ scan, making the overall time complexity dominated only by the sorting step: **$\mathcal{O}(n \log n)$**.
