package extensions.math

import java.math.BigInteger
import kotlin.math.sqrt

/**
 * Mathematical functions
 * Permutation/Combinations taken from: https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit
 */


fun Int.isPrime(): Boolean {
    if (this <= 1)
        return false

    val sqrt = sqrt(toDouble()).toInt()
    return (2..sqrt).all { this % it != 0 }
}

/** https://brilliant.org/wiki/sum-of-n-n2-or-n3 */
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
        .filter { it.size == k }
        .toSet()
}

/** http://tinyurl.com/yd526qh2 */
fun <T> Collection<T>.powerSet(): Set<Set<T>> = powerSet(this, setOf(setOf()))

private tailrec fun <T> powerSet(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> =
    if (left.isEmpty()) acc
    else powerSet(left.drop(1), acc + acc.map { it + left.first() })


/**
 * Generates all permutations (including non-distinct, by default)
 */
fun <T> List<T>.permutations(distinct: Boolean = false): Set<List<T>> =
    if (distinct) permutations().distinct().toSet() else permutations()


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
    require (index in 0..size) { "Invalid index: $index (size: $size)" }
    return when (index) {
        0 -> listOf(element) + this
        size -> this + element
        else -> dropLast(size - index) + element + drop(index)
    }
}
