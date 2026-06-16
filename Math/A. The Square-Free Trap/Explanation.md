# The Square-Free Trap: Game Theory Analysis

## 1. Problem Understanding
The game is played with an integer $n$. Players take turns dividing the current number by a **perfect square** $s > 1$. The player who cannot make a move (i.e., the current number has no square divisors greater than 1) loses.

* **Alice's Turn:** She is the first to move.
* **Optimal Play:** Both players aim to force the other into a losing position.

---

## 2. Theoretical Approach
The game is essentially a battle to reach a **"Square-Free"** state. A number is square-free if its prime factorization contains no prime factors with an exponent $\ge 2$.

### The Winning Logic:
* **The Destroyer Move:** If the starting number $n$ contains any perfect square divisor, Alice can divide $n$ by its largest square divisor.
* **The Result:** By extracting the largest square, Alice removes all prime "pairs" (e.g., $2^2, 3^2, 5^2 \dots$) from the number.
* **The State:** The resulting number is guaranteed to be square-free.
* **Bob’s Fate:** Since the number is now square-free, Bob has no perfect square $s > 1$ to choose. Thus, Alice wins on her first turn.

---

## 3. Mathematical Proof
Let the prime factorization of $n$ be $n = p_1^{a_1} \cdot p_2^{a_2} \cdot \dots \cdot p_k^{a_k}$.

* **Condition for Square Divisor:** $n$ has a perfect square divisor if at least one exponent $a_i \ge 2$.
* **Alice's Strategy:** She chooses $s = \prod p_i^{2 \cdot \lfloor a_i/2 \rfloor}$. This is the largest square that divides $n$.
* **Post-Move State:** The new number $n' = n/s$ will have exponents $a_i' = a_i \pmod 2$. This means all $a_i' \in \{0, 1\}$.
* **Conclusion:** Since no exponent in $n'$ is $\ge 2$, it is impossible to find a perfect square $s > 1$ that divides $n'$. Bob is left with a losing state.

---

## 4. Implementation Strategy
Instead of checking every number up to $n$ (which is slow), we only check prime squares.

* **Precomputation:** Generate all primes up to $\sqrt{10^9} \approx 31,622$ using the **Sieve of Eratosthenes**.
* **Check:** For a given $n$, iterate through the precomputed primes.
* **Condition:** If $n \pmod{p^2} == 0$ for any prime $p$, Alice wins.

### Code Implementation
```java
import java.util.Scanner;
public class tle{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            boolean aliceWins = false;

            for (int i = 2; i * i <= n; i++) {
                if (n % (i * i) == 0) {
                    aliceWins = true;
                    break;
                }
            }

            if (aliceWins) {
                System.out.println("Alice");
            } else {
                System.out.println("Bob");
            }
        }

        sc.close();
    }
}
