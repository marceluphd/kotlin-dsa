package extensions.tuples

import kotlin.math.abs

/**
 * [Pair] extensions
 */

/* To Array */
fun Pair<Boolean, Boolean>.toBooleanArray(): BooleanArray = booleanArrayOf(first, second)
fun Pair<Byte, Byte>.toByteArray(): ByteArray = byteArrayOf(first, second)
fun Pair<Char, Char>.toCharArray(): CharArray = charArrayOf(first, second)
fun Pair<Double, Double>.toDoubleArray(): DoubleArray = doubleArrayOf(first, second)
fun Pair<Int, Int>.toIntArray(): IntArray = intArrayOf(first, second)
fun Pair<Long, Long>.toLongArray(): LongArray = longArrayOf(first, second)
fun Pair<Short, Short>.toShortArray(): ShortArray = shortArrayOf(first, second)

/* Math */
fun Pair<Int, Int>.sum(): Int = first + second

fun Pair<Int, Int>.product(): Int = first * second
fun Pair<Int, Int>.absDifference(): Int = abs(first - second)

fun <T : Comparable<T>> Pair<T, T>.max() = maxOf(first, second)
fun <T : Comparable<T>> Pair<T, T>.min() = minOf(first, second)

/* Null checking */
fun <T : Any?> Pair<T?, T?>.bothNull(): Boolean = first == null && second == null
fun <T : Any?> Pair<T?, T?>.eitherNull(): Boolean = first == null || second == null
fun <T : Any?> Pair<T?, T?>.neitherNull(): Boolean = first != null && second != null

// Source: http://tinyurl.com/y6wn9b2k
infix fun <A, B, C> Pair<A, B>.and(that: C): Triple<A, B, C> = Triple(this.first, this.second, that)

// Flip the pair's order
fun <T, U> Pair<T, U>.reverse() = Pair(second, first)
inline fun <T> Pair<T, T>.reverseIf(predicate: (Pair<T, T>) -> Boolean) =
    if (predicate(this)) Pair(second, first) else this

fun <T> Pair<T, T>.contains(value: T) = first == value || second == value