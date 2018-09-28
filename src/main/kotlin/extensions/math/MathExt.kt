package extensions.math

import extensions.arrays.headAndTail
import java.math.BigInteger
import kotlin.math.sqrt

/**
 * Mathematical functions
 * Permutation/Combinations taken from: https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit
 */
const val EPSILON = 1e-10

/**
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
fun Int.isPrime(): Boolean {
    if (this <= 1)
        return false

    val sqrt = sqrt(toDouble()).toInt()
    return (2..sqrt).all { this % it != 0 }
}

/**
 * Returns the sum of the first n positive integers:
 * Sum of `1..n`: `1 + 2 + 3 + ... + n-1 + n`
 */
fun sumOfFirstNPositiveIntegers(n: Int) = ((n * (n + 1)) / 2)

/**
 * Calculates the factorial, `n!`.
 */
fun Int.factorial(): BigInteger {
    require(this >= 0) { "Must be > 0" }
    return when (this) {
        in 0..1 -> BigInteger.ONE
        else -> {
            var result = BigInteger.ONE
            var i = BigInteger.valueOf(this.toLong())
            while (i > BigInteger.ONE) {
                result = i.multiply(result)
                i = i.minus(BigInteger.ONE)
            }
            return result
        }
    }
}

/* Discrete Math/Combinatorics */
/**
 * Calculate the number of k-combinations: C(n, k) (aka nCk, nCr, 'n choose k').
 */
fun choose(n: Int, k: Int): BigInteger {
    require(n >= 0 && k >= 0) { "C(n, k) for C($n, $k): n and k must be > 0" }
    require(n >= k) { "C(n, k) for C($n, $k): n must be >= k" }

    if (n == k || k == 0) {
        return BigInteger.ONE
    } else if (k == 1) {
        return BigInteger.valueOf(n.toLong())
    }

    val numerator = n.factorial()
    val denominator = (k.factorial()).multiply((n - k).factorial())
    return numerator.divide(denominator)
}

/**
 * Generates all combinations of size [k].
 */
fun <T> Set<T>.combinations(k: Int): Set<Set<T>> = when {
    k < 0 -> throw Error("k cannot be smaller then 0, but was $k")
    k == 0 -> setOf(setOf())
    k >= size -> setOf(toSet())
    else -> powerSet()
        .asSequence()
        .filter { it.size == k }
        .toSet()
}

/**
 * Generates the Power Set of the [Collection].
 *
 * **Time**: `O(2^n)`
 *
 * **Space**: `O(n)`
 */
fun <T> Collection<T>.powerSet(): Set<Set<T>> {
    val powerSet: MutableSet<Set<T>> = hashSetOf(emptySet()) // contains only the null set {{}}
    for (x in this)
        powerSet += powerSet.map { it + x }
    return powerSet
}

/**
 * Generates all permutations (including non-distinct, by default)
 */
fun <T> List<T>.permutations(distinct: Boolean = false): Set<List<T>> =
    when {
        distinct -> permutations()
            .asSequence()
            .distinct()
            .toSet()
        else -> permutations()
    }


private fun <T> List<T>.permutations(): Set<List<T>> = when (size) {
    0 -> setOf()
    1 -> setOf(listOf(first()))
    else ->
        drop(1).permutations()
            .flatMap { sublist ->
                (0..sublist.size).map { i ->
                    sublist.plusAtIndex(index = i, element = first())
                }
            }.toSet()
}

/** http://tinyurl.com/y9kzqfmp */
private fun <T> List<T>.plusAtIndex(index: Int, element: T): List<T> {
    require(index in 0..size) { "Invalid index: $index (size: $size)" }
    return when (index) {
        0 -> listOf(element) + this
        size -> this + element
        else -> dropLast(size - index) + element + drop(index)
    }
}

// Returns List<List<Int>> due to signature of LeetCode Problems 46-47
fun IntArray.permutations(): List<List<Int>> = when (size) {
    0 -> listOf()
    1 -> listOf(listOf(this[0]))
    else -> {
        val (head, tail) = headAndTail
        tail.permutations().flatMap { sublist ->
            (0..sublist.size).map { i ->
                sublist.plusAtIndex(index = i, element = head!!)
            }
        }.distinct()
    }
}
