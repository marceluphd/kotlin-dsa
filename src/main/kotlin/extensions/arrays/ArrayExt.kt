package extensions.arrays

/*
 * Extensions for Array & the primitive arrays (IntArray, BooleanArray, etc.)
 */

/**
 * Returns a list of pairs of each two adjacent elements in this collection.
 * The returned list is empty if this collection contains less than two elements.
 * 
 * ```
 * val letters = arrayOf('a', 'b', 'c', 'd', 'e', 'f')
 * val pairs = letters.zipWithNext()
 *
 * println(letters) // [a, b, c, d, e, f]
 * println(pairs)   // [(a, b), (b, c), (c, d), (d, e), (e, f)]
 * ```
 */
fun <T> Array<T>.zipWithNext(): List<Pair<T, T>> = zip(drop(1))

/**
 * Returns a list of pairs of each two adjacent elements in this collection.
 * The returned list is empty if this collection contains less than two elements.
 *
 * ```
 * val nums = intArrayOf(0, 1, 2, 3, 4, 5)
 * val pairs = nums.zipWithNext()
 *
 * println(nums.contentToString())  // [0, 1, 2, 3, 4, 5]
 * println(pairs)                   // [(0, 1), (1, 2), (2, 3), (3, 4), (4, 5)]
 * ```
 */
fun IntArray.zipWithNext(): List<Pair<Int, Int>> = zip(drop(1))


/**
 * Check if all elements in the [Array] are sorted (in increasing order).
 */
fun <T : Comparable<T>> Array<T>.isSorted(): Boolean = zipWithNext().all { (a, b) -> b >= a }
fun IntArray.isSorted(): Boolean = zipWithNext().all { (a, b) -> b >= a }
fun CharArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun LongArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun ShortArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun DoubleArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }
fun FloatArray.isSorted(): Boolean = (0 until lastIndex).all { i -> this[i] <= this[i + 1] }

/**
 * Check if all elements in the [Array] are sorted in _descending_ order.
 */
fun <T : Comparable<T>> Array<T>.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun IntArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun CharArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun ShortArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun DoubleArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun FloatArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
fun LongArray.isSortedDescending(): Boolean = (0 until lastIndex).all { i -> this[i] >= this[i + 1] }
