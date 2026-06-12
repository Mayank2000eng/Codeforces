# Codeforces - B. Kill Demodogs

## 📝 Problem Statement

Demodogs from the Upside-down have attacked Hawkins. El wants to reach Mike at the opposite corner of an $n \times n$ grid while eliminating as many Demodogs as possible along her path.

- **Grid Representation:** An $n \times n$ matrix (where $2 \le n \le 10^9$).
- **Demodogs Per Cell:** The cell at the $i$-th row and $j$-th column contains exactly $i \cdot j$ Demodogs.
- **Starting Point:** $(1, 1)$
- **Ending Point:** $(n, n)$
- **Movement Rules:** El can only move **Right** ($(i, j) \rightarrow (i, j+1)$) or **Down** ($(i, j) \rightarrow (i+1, j)$). She cannot step outside the grid boundaries.
- **Goal:** Calculate the maximum possible number of Demodogs ($ans$) she can kill on her way (including starting and finishing cells).
- **Output Requirement:** Print $(2022 \cdot ans) \pmod{10^9 + 7}$.

### Example
| Input | Output |
| :--- | :--- |
| `2` | `14154` |
| `3` | `44484` |
| `50` | `171010650` |
| `1000000000` | `999589541` |

---

## 💡 Mathematical Derivation

To maximize the total count of Demodogs killed, El must hug the main diagonal as tightly as possible to hit the cells with the largest possible coordinate products. The optimal path alternates right and down steps along the diagonal:

$$(1,1) \rightarrow (1,2) \rightarrow (2,2) \rightarrow (2,3) \rightarrow \dots \rightarrow (n,n)$$

This path encounters two sets of summations:
1. **The Diagonal Cells:** $\sum_{i=1}^{n} i^2$
2. **The Off-Diagonal Cells:** $\sum_{i=1}^{n-1} i(i+1)$

Using known summation identities:
* $\sum_{i=1}^{n} i^2 = \frac{n(n+1)(2n+1)}{6}$
* $\sum_{i=1}^{n-1} i(i+1) = \frac{n(n-1)(n+1)}{3} = \frac{2n(n-1)(n+1)}{6}$

Adding these together gives the maximum base Demodogs ($ans$):
$$ans = \frac{n(n+1)[(2n+1) + 2(n-1)]}{6}$$
$$ans = \frac{n(n+1)(4n-1)}{6}$$

### Eliminating Division via the $2022$ Constant
The problem asks for $(2022 \cdot ans) \pmod{10^9+7}$. Substituting our formula eliminates the fraction entirely because $\frac{2022}{6} = 337$:

$$\text{Final Answer} = 337 \cdot n(n+1)(4n-1) \pmod{10^9+7}$$

---

## 🧠 Your Solution Code Logic & Overflow Explanation

Your optimized solution cleanly breaks down the arithmetic terms into two parts to compute them efficiently without explicit divisions:
* **`r1`**: Calculates the value of $(4n - 1) \pmod{10^9+7}$.
* **`r2`**: Calculates the value of $n(n+1) \pmod{10^9+7}$, expanded logically into the format $((n^2 \pmod{mod}) + n) \pmod{mod}$.

### Overflow Verification (Why your code is 100% safe for a 64-bit `long`)
Java's primitive signed 64-bit `long` datatype can support maximum values up to $\approx 9.22 \times 10^{18}$. Let's verify our worst-case tracking bounds when $n = 10^9$:

1. **Calculating `r1`:**
   ```java
   long r1 = ((4 * n) % mod - 1);
