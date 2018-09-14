package algorithms.search.binarysearch

import extensions.lists.isSorted


/**
 * BinarySearch
 */

/**
 * Returns an [Int?] representing the index (null if the target is not found)
 *
 * Time: `O(log n)`
 * Space: `O(1)`
 */
fun <T : Comparable<T>> List<T>.binarySearch(key: T): Int? {
    var indexSearchRange = 0..lastIndex

    while (!indexSearchRange.isEmpty()) {
        val mid = (indexSearchRange.start + indexSearchRange.endInclusive) / 2
        indexSearchRange = when {
            key < this[mid] -> (indexSearchRange.start until mid)
            key > this[mid] -> (mid + 1..lastIndex)
            else -> return mid
        }
    }

    return null
}


/** Exercism Solution: http://exercism.io/exercises/kotlin/binary-search/readme */
const val NOT_FOUND: Int = -1

fun <T : Comparable<T>> binarySearchExercism(list: List<T>, searchKey: T): Int {
    require(list.isSorted()) { "Binary Search can only be performed on a sorted list" }

    var range = 0..list.lastIndex

    while (!range.isEmpty()) {
        val mid = (range.start + range.endInclusive) / 2
        when {
            searchKey < list[mid] -> range = (range.start..(mid - 1))
            searchKey > list[mid] -> range = ((mid + 1)..list.lastIndex)
            else -> return mid
        }
    }
    return NOT_FOUND
}