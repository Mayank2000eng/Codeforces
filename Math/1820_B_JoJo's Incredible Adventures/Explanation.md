# 🎭 JoJo's Incredible Rectangle Hunt: ELI5 Edition

> *"You thought this was a complex math problem, but it was me, Dio!"*

Let's break this down so simply that even a child (or an anime protagonist) can understand it instantly. 

---

## 🗺️ The Question (The Shadow Clone Matrix)

Imagine you have a row of lights, some are **ON (1)** and some are **OFF (0)**. Let's say your row is: `1 1 1 0`.

To build our grid, every time we move down to a new row, the lights take one step to the right. The light at the very end wraps around to the front, like a treadmill.

Look at how the pattern forms:
* Row 0: `1 1 1 0`
* Row 1: `0 1 1 1`
* Row 2: `1 0 1 1`
* Row 3: `1 1 0 1`

Our goal is to find the **biggest solid rectangle of 1s** we can catch inside this grid.

---

## 💡 My Strategy (The Snake & The Box)

My approach solves this perfectly without wasting time drawing the whole grid. I broke it down into two smart steps:

### Step 1: Find the Longest "Snake" of 1s
Because the row wraps around like a loop, a streak of `1`s can start at the end of the word and finish at the beginning (e.g., in `1 0 1 1`, the two `1`s at the end loop around to touch the `1` at the start, making a continuous snake of **three** `1`s).

My code looks for the single longest continuous streak of `1`s ($max$) we can possibly make, even if it wraps around the edges.

### Step 2: Playing Tetris (Trading Width for Height)
Once I know the longest snake length ($max$), I know how much room there is to play with. Because each row shifts by exactly 1 step, every time we make our rectangle taller, it has to get narrower by 1 block!

Think of it like allocating points:
* If we want a rectangle that is **1 block wide**, it can go all the way down to be **$max$ blocks tall**.
* If we want it to be **2 blocks wide**, it can only be **$max - 1$ blocks tall**.
* If we want it to be **3 blocks wide**, it can only be **$max - 2$ blocks tall**.

My code simply checks every combination! It tries width 1, width 2, width 3... calculates the height for each, multiplies them to get the area, and keeps the biggest result.

### Step 3: The Cheat Codes (Edge Cases)
I also added quick shortcuts for simple patterns:
* **All 1s:** If there are no zeros at all, the entire grid is a giant square of 1s. The answer is just the total length times itself ($max \times max$).
* **No 1s or just single 1s:** The biggest area we can get is just $0$ or $1$, so no math loops are required!
