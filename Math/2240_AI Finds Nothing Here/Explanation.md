# Problem Analysis: AI Finds Nothing Here

In this problem, we are tasked with finding the number of $n \times m$ binary matrices such that every $r \times c$ submatrix has an XOR sum of 0. This is a classic problem involving counting valid configurations in a constrained grid, which can be solved using concepts from linear algebra over the field $GF(2)$.

## Deep Dive into the Constraints

### 1. The XOR Property over GF(2)
When we work with XOR sums in a binary grid, we are essentially working in the field $GF(2)$. In this field, addition is equivalent to XOR ($0+0=0, 0+1=1, 1+0=1, 1+1=0$). The condition that every $r \times c$ submatrix has an XOR sum of 0 implies a system of linear equations. 

If we define a submatrix starting at $(i, j)$ as $S_{i,j}$, the condition is:
$$\bigoplus_{x=i}^{i+r-1} \bigoplus_{y=j}^{j+c-1} a_{x,y} = 0$$

### 2. Linear Independence of Constraints
The key insight here is determining how many of these constraints are **linearly independent**. 

Consider the grid as a set of $n \times m$ variables. Each $r \times c$ submatrix provides one equation. There are $(n - r + 1) \times (m - c + 1)$ such submatrices. 
It can be proven that each additional constraint (moving to a new $r \times c$ window) introduces exactly one new linearly independent equation. Because each equation involves at least one variable not covered by previous equations, we don't have redundant constraints.

Thus, we have:
- **Total Variables:** $n \times m$
- **Total Independent Constraints:** $(n - r + 1) \times (m - c + 1)$

### 3. Calculating the Degrees of Freedom
In a system of linear equations, the number of free variables (degrees of freedom) is equal to:
$$\text{Total Variables} - \text{Number of Independent Constraints}$$

Each "free" cell can be assigned either a 0 or a 1. Once these free cells are assigned, all other cells in the matrix are **forced** to take specific values to satisfy the XOR sum constraints. Therefore, the total number of valid matrices is exactly:
$$2^{\text{free cells}} \pmod{998244353}$$

## Solution Logic

I implemented this by calculating the number of free cells using the derived formula and then computing $2^{\text{free cells}} \pmod{998244353}$ using Binary Exponentiation. 

Binary Exponentiation is necessary because `free_cell` can be as large as $10^{18}$ (since $n, m \le 10^9$). A simple loop would result in a Time Limit Exceeded (TLE) error, but exponentiation by squaring achieves $O(\log(\text{exponent}))$ time complexity, which is perfectly efficient.

### Code Implementation

```java
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextLong();
            long m = sc.nextLong();
            long r = sc.nextLong();
            long c = sc.nextLong();
            
            // Step 1: Calculate the number of independent constraints
            // Each r x c submatrix acts as one constraint.
            long totalCells = (n * m);
            long constraints = (n - r + 1) * (m - c + 1);
            
            // Step 2: The number of free variables is total minus constraints
            long freeCells = totalCells - constraints;
            
            // Step 3: Result is 2^freeCells % MOD
            System.out.println(power(2, freeCells));
        }
    }

    static long mod = 998244353;

    // In-depth look at Binary Exponentiation
    // We decompose the exponent into powers of 2 to achieve logarithmic time
    static long power(long base, long exp) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            // If the current bit of the exponent is 1, multiply the result
            if (exp % 2 == 1) res = (res * base) % mod;
            // Square the base for the next bit position
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}
