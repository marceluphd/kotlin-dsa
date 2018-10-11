package extensions.lists

/*
 * Extensions for List.
 */

/**
 * Returns views of the first and second halves of the list.
 * If the list is odd-sized, the first half is larger.
 */
val <T> List<T>.halves: Pair<List<T>, List<T>>
    get() = when (size) {
        0 -> Pair(emptyList(), emptyList())
        else -> slice(0..lastIndex / 2) to slice((lastIndex / 2) + 1..lastIndex)
    }

fun <T : Comparable<T>> List<T>.isSorted(): Boolean = zipWithNext().all { (a, b) -> b >= a }
fun <T : Comparable<T>> List<T>.isSortedDescending(): Boolean = zipWithNext().all { (a, b) -> b <= a }
