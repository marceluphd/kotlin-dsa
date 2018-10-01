package algorithms.dynamicprogramming.rodcutting

import kotlin.math.max

object NaiveSolution {
    /**
     * **Time**: `O(2^n)`
     *
     * **Space**: `O(n)`
     */
    fun cutRod(prices: IntArray, n: Int): Int {
        if (n == 0) return 0

        var maxAmount = Int.MIN_VALUE
        for (i in (1..n)) {
            // Cut at 1, 2, ..., n (cut at n == no cut)
            // maxAmount = max(maxAmount, prices[i] + cutRod(prices, n - i))
            val leftAmount = prices[i]
            val rightAmount = cutRod(prices, n - i)
            maxAmount = max(maxAmount, leftAmount + rightAmount)
        }

        return maxAmount
    }
}

object MemoizedSolution {
    /**
     * **Time**: `O(n^2)` - [explanation](http://tinyurl.com/yd8bhxyp)
     *
     * **Space**: `O(n)`
     */
    fun cutRod(prices: IntArray, n: Int): Int {
        val cache = IntArray(n + 1)
        return memoizedCutRod(prices, n, cache)
    }

    private fun memoizedCutRod(prices: IntArray, n: Int, cache: IntArray): Int =
        when {
            n == 0 -> 0
            cache[n] > 0 -> {
                cache[n]
            }
            else -> {
                var maxAmount = Int.MIN_VALUE
                for (i in 1..n) {
                    // maxAmount = max(maxAmount, prices[i] + memoizedCutRod(prices, n - i, cache))
                    val leftAmount = prices[i]
                    val rightAmount = memoizedCutRod(prices, n - i, cache)
                    maxAmount = max(maxAmount, leftAmount + rightAmount)
                }
                cache[n] = maxAmount
                maxAmount
            }
        }
}

object TabulationSolution {
    /**
     * **Time**: `O(n^2)` - [explanation](http://tinyurl.com/yd8bhxyp)
     *
     * **Space**: `O(n)`
     */
    fun cutRod(prices: IntArray, n: Int): Int {
        val cache = IntArray(n + 1)

        // i = currentLength to calculate the optimal value for.
        for (i in (1..n)) {
            var iMax = Int.MIN_VALUE

            // j = cut position (inches from left)
            for (j in (0..i)) {
                iMax = max(iMax, prices[j] + cache[i - j])
            }
            cache[i] = iMax
        }

        return cache[n]
    }
}