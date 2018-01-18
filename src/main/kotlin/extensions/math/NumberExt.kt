package extensions.math

import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.math.log10
import kotlin.math.pow

/**
 * Number (Int, Double, Float, Byte, Short, etc.) extensions.
 */

/********************
 * Int
 ********************/

val Int.isEven: Boolean get() = this % 2 == 0
val Int.isOdd: Boolean get() = this % 2 == 1

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
internal fun Int.nLeastSignificantDigits(n: Int): Int {
    require(n >= 1) { "invalid argument n: $n. Must be >= 1" }
    return this % 10.0.pow(n).toInt()
}

internal fun Int.nMostSignificantDigits(n: Int): Int {
    val totalDigits = numberOfDigits()
    require(n in 1..totalDigits) { "invalid argument n: $n. Must be in range [0, total number of digits]" }
    return this / 10.0.pow(totalDigits - n).toInt()
}

fun Double.roundedToNDecimalPlaces(n: Int): Double {
    require(n >= 0) { "Invalid # of decimal places: $n" }
    val formatter = DecimalFormat("#.${"#".repeat(n)}")
    return formatter.format(this).toDouble()
}

/* If using Kotlin < 1.2 (no kotlin.math):
internal fun Int.nRightmostSignificantDigits(n: Int): Int {
    require(n >= 1) { "invalid argument n: $n. Must be >= 1" }
    val modOperand = Math.pow(10.0, n.toDouble()).toInt()
    return this % modOperand
}

internal fun Int.nMostSignificantDigits(n: Int): Int {
    val totalDigits = numberOfDigits()
    require(n in 1..totalDigits) { "invalid argument n: $n. Must be in range [0, total number of digits]" }
    val divOperand = Math.pow(10.0, (totalDigits - n).toDouble()).toInt()
    return this / divOperand
}
*/
