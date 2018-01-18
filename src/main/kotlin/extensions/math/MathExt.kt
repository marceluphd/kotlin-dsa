package extensions.math

import java.math.BigInteger

/**
 * Mathematical functions & utilities not in java.lang.Math
 */

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

/* kotlin.math: available with Kotlin 1.2+, but LeetCode uses 1.1
import java.lang.Math as nativeMath

public inline fun abs(x: Double): Double = nativeMath.abs(x)
public inline fun abs(x: Float): Float = nativeMath.abs(x)
public inline fun abs(n: Int): Int = nativeMath.abs(n)
public inline fun abs(n: Long): Long = nativeMath.abs(n)

public inline val Double.absoluteValue: Double get() = nativeMath.abs(this)
public inline val Float.absoluteValue: Float get() = nativeMath.abs(this)
public inline val Int.absoluteValue: Int get() = nativeMath.abs(this)
public inline val Long.absoluteValue: Long get() = nativeMath.abs(this)

public inline fun Double.pow(x: Double): Double = nativeMath.pow(this, x)
public inline fun Double.pow(n: Int): Double = nativeMath.pow(this, n.toDouble())

public inline fun sqrt(x: Double): Double = nativeMath.sqrt(x)
public inline fun sqrt(x: Float): Float = nativeMath.sqrt(x.toDouble()).toFloat()


// BigInteger/BigDecimal
public inline operator fun BigInteger.plus(other: BigInteger): BigInteger = this.add(other)
public inline operator fun BigInteger.minus(other: BigInteger): BigInteger = this.subtract(other)
public inline operator fun BigInteger.times(other: BigInteger): BigInteger = this.multiply(other)
public inline operator fun BigInteger.div(other: BigInteger): BigInteger = this.divide(other)
public inline operator fun BigInteger.rem(other: BigInteger): BigInteger = this.remainder(other)
public inline operator fun BigInteger.unaryMinus(): BigInteger = this.negate()
public inline operator fun BigInteger.inc(): BigInteger = this.add(BigInteger.ONE)
public inline operator fun BigInteger.dec(): BigInteger = this.subtract(BigInteger.ONE)
public inline fun BigInteger.inv(): BigInteger = this.not()
public inline infix fun BigInteger.and(other: BigInteger): BigInteger = this.and(other)
public inline infix fun BigInteger.or(other: BigInteger): BigInteger = this.or(other)
public inline infix fun BigInteger.xor(other: BigInteger): BigInteger = this.xor(other)
public inline infix fun BigInteger.shl(n: Int): BigInteger = this.shiftLeft(n)
public inline infix fun BigInteger.shr(n: Int): BigInteger = this.shiftRight(n)
public inline fun Int.toBigInteger(): BigInteger = BigInteger.valueOf(this.toLong())
public inline fun Long.toBigInteger(): BigInteger = BigInteger.valueOf(this)
public inline fun BigInteger.toBigDecimal(): BigDecimal = BigDecimal(this)
public inline fun BigInteger.toBigDecimal(scale: Int = 0, mathContext: MathContext = MathContext.UNLIMITED): BigDecimal =
        BigDecimal(this, scale, mathContext)

public inline operator fun BigDecimal.plus(other: BigDecimal): BigDecimal = this.add(other)
public inline operator fun BigDecimal.minus(other: BigDecimal): BigDecimal = this.subtract(other)
public inline operator fun BigDecimal.times(other: BigDecimal): BigDecimal = this.multiply(other)
public inline operator fun BigDecimal.div(other: BigDecimal): BigDecimal = this.divide(other, RoundingMode.HALF_EVEN)
public inline operator fun BigDecimal.mod(other: BigDecimal): BigDecimal = this.remainder(other)
public inline operator fun BigDecimal.rem(other: BigDecimal): BigDecimal = this.remainder(other)
public inline operator fun BigDecimal.unaryMinus(): BigDecimal = this.negate()
public inline operator fun BigDecimal.inc(): BigDecimal = this.add(BigDecimal.ONE)
public inline operator fun BigDecimal.dec(): BigDecimal = this.subtract(BigDecimal.ONE)
public inline fun Int.toBigDecimal(): BigDecimal = BigDecimal(this)
public inline fun Int.toBigDecimal(mathContext: MathContext): BigDecimal = BigDecimal(this, mathContext)
public inline fun Long.toBigDecimal(): BigDecimal = BigDecimal(this)
public inline fun Long.toBigDecimal(mathContext: MathContext): BigDecimal = BigDecimal(this, mathContext)
*/
