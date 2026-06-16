# A. The Square-Free Trap

**Time Limit:** 1.5 seconds  
**Memory Limit:** 256 megabytes

Alice and Bob are mathematicians playing a game of division. They are given a single positive integer `n`.

Alice always takes the first turn, and they alternate turns thereafter.

In a single turn, a player must choose a perfect square `s` (where `s > 1`) that perfectly divides the current number `n`. They then divide `n` by `s`, replacing `n` with `n / s`.

> **Note:** A perfect square is an integer that is the square of another integer. For example, `4`, `9`, `16`, `25`, and `36` are valid choices for `s`, provided `n mod s = 0`.

The game continues until a player cannot make a valid move. This happens when `n` contains no perfect square divisors strictly greater than `1`.

The player who cannot make a valid move on their turn loses the game.

Assuming both Alice and Bob play optimally to win, determine who will win the game for a given starting number `n`.

## Input

The first line contains a single integer `t` (`1 ≤ t ≤ 10^4`) — the number of test cases.

Each of the following `t` lines contains a single integer `n` (`1 ≤ n ≤ 10^9`) — the starting number for that game.

## Output

For each test case, output:

- `"Alice"` if Alice wins.
- `"Bob"` otherwise.

## Example

### Input
```text
4
72
10
1
16
```

### Output
```text
Alice
Bob
Bob
Alice
```
