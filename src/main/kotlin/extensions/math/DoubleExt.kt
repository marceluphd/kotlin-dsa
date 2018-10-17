package extensions.math

import java.text.DecimalFormat

/**
 * Rounds the Double to [n] decimal places.
 */
fun Double.roundedToNDecimalPlaces(n: Int): Double {
    require(n >= 0) { "Invalid # of decimal places: $n" }
    val formatter = DecimalFormat("#.${"#".repeat(n)}")
    return formatter.format(this).toDouble()
}

/**
 * Format the [Double] as a String with [n] decimal places.
 */
fun Double.formatToNDecimalPlaces(n: Int): String = "%.${n}f".format(this)
