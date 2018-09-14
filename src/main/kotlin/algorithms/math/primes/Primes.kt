package algorithms.math.primes

import extensions.math.isPrime
import kotlin.coroutines.experimental.buildSequence

/**
 * Primes.
 * See https://github.com/rojiani/kotlin-99 for more.
 */


/**
 * [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
 */
object SieveOfEratosthenes {
    /**
     * Returns a list of all prime numbers up to *and including* [n].
     */
    fun primesUpTo(n: Int): List<Int> {
        return sieveOfEratosthenes(n)
    }

    private fun sieveOfEratosthenes(n: Int): List<Int> {
        /* Index is the number, boolean value is whether the number is possibly prime */
        val nums = BooleanArray(n + 1, { i -> i !in 0..1 })   // initially true for all x in 2..n

        var p = 2
        while (p != -1) {
            /* Mark composite numbers at 2p, 3p, 4p, ... */
            ((p * 2)..n step p).forEach { np ->
                nums[np] = false
            }

            /* Find the first number greater than p in the list that is not marked */
            p = nums.withIndex().indexOfFirst { (index, unmarked) ->
                index > p && unmarked
            }
        }

        return nums.withIndex()
            .filter { (_, isPrime) -> isPrime }
            .map { it.index }
    }
}

/**
 * from http://exercism.io/exercises/kotlin/nth-prime/readme
 */
fun primeNumberSequence(): Sequence<Int> {
    return buildSequence {
        yieldAll(generateSequence(2) { it + 1 }
            .filter { it.isPrime() })
    }
}

fun nthPrime(n: Int): Int {
    require(n > 0) { "nth undefined for input: $n." }
    return primeNumberSequence().take(n).last()
}



