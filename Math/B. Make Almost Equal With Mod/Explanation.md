B. MAKE ALMOST EQUAL WITH MOD
🔑 KEY OBSERVATION

For any base b:

xmodb
k
=last k digits of x in base b
	​


For binary:

xmod2
k
=last k bits of x
	​

1. UNDERSTANDING THE PROPERTY

Suppose:

x = 13

Binary representation:

1101

Now take:

13 % 4

Since:

4 = 2²

we keep the last 2 bits:

1101
  ↓↓
  01

And:

01₂ = 1

Therefore:

13 % 4 = 1

So:

xmod2
k
=last k bits of x
	​

2. WHY DO WE TRY 2^i?

The problem allows:

1 <= k <= 10^18

There are far too many possible values of k.

Instead, we try only:

2
4
8
16
32
64
128
...

That is:

k=2
i

Then:

a
j
	​

mod2
i

means:

Take the last i bits of a[j].

3. WHY DOES THIS GUARANTEE EXACTLY 2 VALUES?

This is the most important part of the solution.

Suppose:

a[i] % 2^k

gives only 1 distinct value.

That means:

All numbers have exactly the same last k bits.

For example:

101101
001101
111101

All have the same last 4 bits:

1101

Now increase the modulus:

2
k
→2
k+1

We are basically adding one more bit.

So the existing group can split into only two groups:

same k bits + 0
same k bits + 1

Therefore the number of distinct values can change only like:

1 → 1

or:

1 → 2

It cannot become:

1 → 3
4. THEREFORE

The first time the number of distinct remainders becomes greater than 1, it must be exactly 2.

So we can simply keep trying:

2^1
2^2
2^3
2^4
...

until:

set.size() == 2

Then that power of 2 is our answer.

5. EXAMPLE

Consider:

8 15 22 30

Try:

k = 2

We get:

8  % 2 = 0
15 % 2 = 1
22 % 2 = 0
30 % 2 = 0

So the resulting array is:

0 1 0 0

Distinct values:

{0, 1}

Therefore:

ANSWER = 2

The problem's sample gives 7, but the problem says any valid k is accepted.

So 2 is also a valid answer.

6. WHY HASHSET?

We only need to know:

How many distinct remainders are there?

A HashSet automatically removes duplicates.

For example:

Remainders:

0 1 0 0 1

HashSet becomes:

{0, 1}

Therefore:

set.size()

gives:

2
7. YOUR CODE
import java.lang.reflect.Array;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();

            long[] a = new long[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }

            long ans = 2;

            HashSet<Long> set = new HashSet<>();

            for (int i = 1; i <= 64; i++) {

                long r = (long) Math.pow(2, i);

                for (int j = 0; j < n; j++) {
                    set.add(a[j] % r);
                }

                if (set.size() == 2) {
                    ans = r;
                    break;
                }

                set.clear();
            }

            System.out.println(ans);
        }
    }
}
8. UNDERSTANDING YOUR CODE

This loop:

for (int i = 1; i <= 64; i++) {
    long r = (long) Math.pow(2, i);

generates:

2
4
8
16
32
64
128
...

So r is our candidate k.

Then:

for (int j = 0; j < n; j++) {
    set.add(a[j] % r);
}

calculates:

a[0] % r
a[1] % r
a[2] % r
...

and puts all distinct remainders into the HashSet.

Then:

if (set.size() == 2) {
    ans = r;
    break;
}

means:

We found a k for which the final array contains exactly 2 distinct values.

So we save it and stop searching.

9. WHY set.clear()?

Suppose we first try:

k = 2

and get:

{0}

Then we try:

k = 4

We don't want the old {0} to remain.

Therefore:

set.clear();

makes the HashSet empty before the next iteration.

10. WHY NOT TRY ALL k?

Because:

k <= 10^18

Trying every value would be impossible.

But powers of 2 are very few:

2
60
≈1.15×10
18

So there are only about 60 candidates.

Therefore, instead of checking:

10^18 possibilities

we check approximately:

60 possibilities
11. TIME COMPLEXITY

For each power of 2, we process all n elements.

There are at most around 60 powers.

Therefore:

O(60n)

Since 60 is a constant:

O(n)
	​


per test case.

12. SPACE COMPLEXITY

The HashSet can contain at most n values.

Therefore:

O(n)
	​
