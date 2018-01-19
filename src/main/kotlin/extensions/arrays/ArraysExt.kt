package extensions.arrays

/**
 * Extensions for:
 * Array<T>
 * BooleanArray
 * ByteArray
 * CharArray
 * DoubleArray
 * FloatArray
 * IntArray
 * LongArray
 * ShortArray
 */

/* https://github.com/Carleslc/kotlin-extensions/blob/master/src/me/carleslc/kotlin/extensions/arrays/ArrayExtensions.kt */
fun <T> Array<T>?.isBlank(): Boolean = this == null || isEmpty()

fun <T> Array<T?>.anyNull(): Boolean = any { it == null }
fun <T> Array<T?>.allNull(): Boolean = all { it == null }

fun IntArray?.isBlank(): Boolean = this == null || isEmpty()

/**
 * Return true if the object is either an Array<T> or primitive array type.
 */
fun Any.isArrayType(): Boolean {
    if (this is Array<*>)
        return true

    val primitiveArrayTypes = setOf(
        BooleanArray::class,
        ByteArray::class,
        CharArray::class,
        DoubleArray::class,
        FloatArray::class,
        IntArray::class,
        LongArray::class,
        ShortArray::class
    )

    return this::class in primitiveArrayTypes
}

/**
 * Source: http://tinyurl.com/y92rp67r
 */
inline fun <K> IntArray.groupingBy(crossinline keySelector: (Int) -> K): Grouping<Int, K> = object : Grouping<Int, K> {
    override fun sourceIterator(): IntIterator = this@groupingBy.iterator()
    override fun keyOf(element: Int): K = keySelector(element)
}

/**
 * Return a map where the entries are (element -> # of occurrences)
 */
fun IntArray.frequencyMap(): Map<Int, Int> = groupingBy { it }.eachCount()

/**
 * Return a map where the entries are (element -> # of occurrences)
 */
fun <T> Array<T>.frequencyMap(): Map<T, Int> = groupingBy { it }.eachCount()


/**
 * Return a map where the entries are (element -> List of indices containing element)
 */
fun IntArray.valueToIndicesMap(): Map<Int, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })

/**
 * Return a map where the entries are (element -> List of indices containing element)
 */
fun <T> Array<T>.valueToIndicesMap(): Map<T, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })


/**
 * **Mutating** - Swap the elements at indices [i] and [j].
 */
fun <T> Array<T>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

/**
 * **Mutating** - Swap the elements at indices [i] and [j].
 */
fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

/**
 * **Mutating** - Swap the elements at indices [i] and [j].
 */
fun CharArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

/**
 * **Mutating** - Reverse the elements in the given index range.
 */
fun <T> Array<T>.reverseElementsInRange(indexRange: IntRange) {
    require(indexRange.start >= 0 && indexRange.endInclusive <= lastIndex) {
        "Invalid indexRange: $indexRange for array with indices: $indices"
    }
    var i = indexRange.start
    var j = indexRange.endInclusive
    while (i < j) {
        swap(i++, j--)
    }
}

/**
 * **Mutating** - Reverse the elements in the given index range.
 */
fun IntArray.reverseElementsInRange(indexRange: IntRange) {
    var i = indexRange.start
    var j = indexRange.endInclusive
    while (i < j) {
        swap(i++, j--)
    }
}

/**
 * **Mutating** - Reverse the elements in the given index range.
 */
fun CharArray.reverseElementsInRange(indexRange: IntRange) {
    var i = indexRange.start
    var j = indexRange.endInclusive
    while (i < j) {
        swap(i++, j--)
    }
}

fun <T : Comparable<T>> Array<T>.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun IntArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun CharArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun LongArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun ShortArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun DoubleArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun FloatArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }

fun <T : Comparable<T>> Array<T>.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun IntArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun CharArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun ShortArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun DoubleArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun FloatArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun LongArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }

// TODO: chunked, windowed, etc.

/* Matrices - Array<IntArray> */

typealias Matrix = Array<IntArray>

internal val Matrix.rows: Int get() = size
internal val Matrix.columns: Int get() = if (isEmpty()) 0 else this[0].size

internal val Matrix.lastRow: Int get() = lastIndex
internal val Matrix.lastColumn: Int get() = if (isEmpty()) -1 else this[0].lastIndex

internal fun Matrix.toList(): List<List<Int>> = fold(emptyList()) { acc, intArr ->
    acc.plusElement(intArr.toList())
}

internal fun Matrix.transpose(): Matrix {
    val transposed: Matrix = Array(columns, { IntArray(rows) })
    (0..lastRow).forEach { i ->
        (0..lastColumn).forEach { j ->
            transposed[j][i] = this[i][j]
        }
    }
    return transposed
}
