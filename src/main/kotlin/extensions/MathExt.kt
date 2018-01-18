package extensions

import java.math.BigInteger
import kotlin.math.sqrt

/** Return whether the number is prime. */
fun Int.isPrime(): Boolean {
    if (this <= 1) return false

    val sqrt = sqrt(toDouble()).toInt()
    return (2..sqrt).all { this % it != 0 }
}

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


// TODO - list to set
fun <T> powerSet(elements: List<T>): List<List<T>> {
    return if (elements.isEmpty()) {
        emptyList()
    } else {
        elements.foldIndexed(listOf(emptyList())) { index, acc, _ ->
            acc + powerSet(elements.drop(index + 1))
                .map { listOf(elements[index]) + it }
        }
    }
}

/**
 * combinations("abcde".toList(), 3)
 * [[a, b, c], [a, b, d], [a, b, e], [a, c, d], [a, c, e], [a, d, e], [b, c, d], [b, c, e], [b, d, e], [c, d, e]]
 */
fun <T> List<T>.combinations(k: Int = size): List<List<T>> =
    if (isEmpty() || k == 0) {
        listOf(emptyList())
    } else {
        (0 until size).fold(arrayListOf(emptyList<T>())) { acc, i ->
            val tailCombinations = drop(i + 1).combinations(k - 1).toMutableList()
            val tailPlusCurrent: Collection<List<T>> = tailCombinations.map { listOf(this[i]) + it }
            acc.apply {
                acc.addAll(tailPlusCurrent)
            }
        }.filter { it.size == k }
    }

fun <T> List<T>.permutations(): List<List<T>> =
    (0 until size).fold(listOf()) { acc, i ->
        acc + when (size) {
            in 0..1 -> listOf(this)
            else -> {
                val listMinusCurrent = take(i) + drop(i + 1)
                val complementPerms = listMinusCurrent.permutations()
                complementPerms.map { listOf(this[i]) + it }
            }
        }
    }

// TODO: permutations of size k
fun <T> List<T>.permutations(k: Int): List<List<T>> = TODO()

/**
 * https://brilliant.org/wiki/sum-of-n-n2-or-n3/
 */
fun sumOfFirstNPositiveIntegers(n: Int) = ((n * (n + 1)) / 2)
