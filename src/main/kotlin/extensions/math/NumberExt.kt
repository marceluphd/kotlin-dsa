package extensions.math

import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Number (Int, Double, Float, Byte, Short, etc.) extensions.
 */

/********************
 * Int
 ********************/

val Int.isEven: Boolean get() = this % 2 == 0
val Int.isOdd: Boolean get() = this % 2 != 0

fun minOf(vararg numbers: Int): Int {
    require(numbers.isNotEmpty()) { "Cannot take min of 0 numbers" }
    return numbers.min()!!
}

/**
 * Applies Java's [Character.forDigit].
 */
fun Int.charForDigit(): Char = Character.forDigit(this, 10)

/**
 * Returns the digits in the [Int] receiver.
 * e.g., `497.digits() --> [4, 9, 7]`
 *
 * Requires that the integer be >= 0
 *
 * Time/Space: `O(n)/O(n)` where `n` is the # of digits.
 */
fun Int.digits(): List<Int> {
    require(this >= 0) { "Must be > 0" }
    if (this == 0) return listOf(0)

    var remainder = absoluteValue
    val digits = mutableListOf<Int>()

    while (remainder > 0) {
        val rightmostDigit = remainder % 10
        digits.add(rightmostDigit)
        remainder /= 10
    }

    return digits.reversed()
}

/**
 * Return the number of digits in an integer.
 *
 * Ex. `4321.numberOfDigits() -> 4`
 *
 * Time/Space: `O(1)/O(1)`
 */
fun Int.numberOfDigits(): Int = when (this) {
    0 -> 1                  // log(0.0) -> -inf
    Int.MIN_VALUE -> 10     // abs(Int.MIN_VALUE) -> Int overflow
    else -> (log10(absoluteValue.toDouble()) + 1.0).toInt()
}

fun Int.leastSignificantDigit(): Int = this % 10
fun Int.mostSignificantDigit(): Int = this / 10.0.pow(numberOfDigits() - 1).toInt()

// note: 03 will be returned as 3
fun Int.nLeastSignificantDigits(n: Int): Int {
    require(n >= 1) { "invalid argument n: $n. Must be >= 1" }
    return this % 10.0.pow(n).toInt()
}

fun Int.nMostSignificantDigits(n: Int): Int {
    val totalDigits = numberOfDigits()
    require(n in 1..totalDigits) { "invalid argument n: $n. Must be in range [0, total number of digits]" }
    return this / 10.0.pow(totalDigits - n).toInt()
}

fun median(a: Int, b: Int, c: Int) = max(min(a, b), min(max(a, b), c))

/********************
 * Long
 ********************/
fun minOf(vararg numbers: Long): Long {
    require(numbers.isNotEmpty()) { "Cannot take min of 0 numbers" }
    return numbers.min()!!
}


/********************
 * Double
 ********************/

fun minOf(vararg numbers: Double): Double {
    require(numbers.isNotEmpty()) { "Cannot take min of 0 numbers" }
    return numbers.min()!!
}

fun Double.roundedToNDecimalPlaces(n: Int): Double {
    require(n >= 0) { "Invalid # of decimal places: $n" }
    val formatter = DecimalFormat("#.${"#".repeat(n)}")
    return formatter.format(this).toDouble()
}

/********************
 * Float
 ********************/

fun minOf(vararg numbers: Float): Float {
    require(numbers.isNotEmpty()) { "Cannot take min of 0 numbers" }
    return numbers.min()!!
}
