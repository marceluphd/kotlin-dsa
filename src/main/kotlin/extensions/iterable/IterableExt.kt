package extensions.iterable

val <T> Iterable<T>.head: T? get() = firstOrNull()
val <T> Iterable<T>.tail: List<T> get() = drop(1)
val <T> Iterable<T>.headAndTail: Pair<T?, List<T>> get() = (head to tail)

/**
 * Return a map where for each entry, the key is an element in the array, and
 * its value is the number of occurrences of the element in the array.
 */
fun <T> Iterable<T>.frequencyMap(): Map<T, Int> = groupingBy { it }.eachCount()

/**
 * Return a map where for each entry, the key is an element in the array, and
 * its value is a list of the indices at which the element is found in the array.
 */
fun <T> Iterable<T>.valueToIndicesMap(): Map<T, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })

