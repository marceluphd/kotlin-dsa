package algorithms.dynamicprogramming.fibonacci

import kotlin.coroutines.experimental.buildSequence

/**
 * Naive recursive function to find the nth Fibonacci number.
 *
 * **Time**: `O(2^n)` (size of the recursion tree)
 *
 * **Space**: `O(n)` (height of the recursion tree)
 */
object NaiveFibonacci {
    fun fibo(n: Int): Long = when (n) {
        0 -> 0L
        1 -> 1L
        else -> fibo(n - 1) + fibo(n - 2)
    }
}

/**
 * Using memoization (top-down).
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
object MemoizedFibonacci {
    fun fibo(n: Int): Long = fiboMemoized(n, hashMapOf(0 to 0L, 1 to 1L))

    private fun fiboMemoized(n: Int, cache: MutableMap<Int, Long>): Long {
        if (n !in cache) {
            cache[n] = fiboMemoized(n - 1, cache) + fiboMemoized(n - 2, cache)
        }
        return cache[n]!!
    }
}

/**
 * Using tabulation (bottom-up).
 * Tabulation is preferable to memoization here, because we know we must calculate
 * fibo(x) for all x in 1..n. Memoization is preferable for problems where we don't
 * have to calculate every single subproblem.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
object TabulationFibonacci {
    fun fibo(n: Int): Long {
        val cache: MutableMap<Int, Long> = hashMapOf(0 to 0L, 1 to 1L)
        for (i in (2..n)) {
            cache[i] = cache[i - 1]!! + cache[i - 2]!!
        }

        return cache[n]!!
    }
}


/**
 * Using tabulation (bottom-up), using constant space.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
object OptimizedFibonacci {
    fun fibo(n: Int): Long {
        if (n in 0..1) return n.toLong()

        var minusTwo = 0L   // fibo(0)
        var minusOne = 1L   // fibo(1)
        var fiboX = 1L
        for (x in (2..n)) {
            fiboX = minusOne + minusTwo
            minusTwo = minusOne
            minusOne = fiboX
        }

        return fiboX
    }
}


/**
 * Using [buildSequence]
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
object SequenceFibonacci {

    fun fibo(n: Int): Long = fibonacciSequence().take(n + 1).last()

    /**
     * Lazily builds a sequence one-by-one.
     */
    private fun fibonacciSequence(): Sequence<Long> = buildSequence {
        var terms = Pair(0L, 1L)

        // this sequence is infinite
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
        }
    }
}
