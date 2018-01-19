package extensions.math

import java.math.BigInteger
import kotlin.math.sqrt

/**
 * Mathematical functions
 * See also https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit
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
fun <T> Collection<T>.combinations(k: Int = size): Set<Set<T>> =
    combinations(this, setOf(setOf()), k).filter { it.size == k }.toSet()

private tailrec fun <T> combinations(left: Collection<T>, acc: Set<Set<T>>, k: Int): Set<Set<T>> =
    if (left.isEmpty()) acc
    else combinations(left.drop(1), acc + acc.map { it + left.first() }, k)

/** http://tinyurl.com/yd526qh2 */
fun <T> Collection<T>.powerSet(): Set<Set<T>> =
    powerSet(this, setOf(setOf()))

private tailrec fun <T> powerSet(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> =
    if (left.isEmpty()) acc
    else powerSet(left.drop(1), acc + acc.map { it + left.first() })


fun <T> List<T>.permutations(): Set<List<T>> = when {
    isEmpty() -> setOf()
    size == 1 -> setOf(listOf(this[0]))
    else -> {
        drop(1).permutations()
            .flatMap { sublist ->
                (0..sublist.size).map { i -> sublist.plusAtIndex(index = i, element = first()) }
            }.toSet()
    }
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