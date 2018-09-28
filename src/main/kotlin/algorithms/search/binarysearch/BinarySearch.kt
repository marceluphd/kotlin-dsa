package algorithms.search.binarysearch


/**
 * BinarySearch
 */

const val NOT_FOUND: Int = -1

/**
 * Time: `O(log n)`
 * Space: `O(1)`
 * @return the index of the element if found, or -1 if not found
 */
fun <T : Comparable<T>> binarySearch(list: List<T>, searchKey: T): Int {
    var range = 0..list.lastIndex
    while (!range.isEmpty()) {
        val mid = (range.start + range.endInclusive) / 2
        range = when {
            searchKey < list[mid] -> (range.start..(mid - 1))
            searchKey > list[mid] -> ((mid + 1)..list.lastIndex)
            else -> return mid
        }
    }
    return NOT_FOUND
}


// TODO Add recursive version