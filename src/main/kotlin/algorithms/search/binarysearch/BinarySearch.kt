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
fun <T : Comparable<T>> binarySearch(sortedList: List<T>, searchKey: T): Int? {
    var range = 0..sortedList.lastIndex
    var mid: Int

    while (!range.isEmpty()) {
        mid = (range.start + range.endInclusive) / 2
        when {
            sortedList[mid] == searchKey -> return mid
            searchKey < sortedList[mid] -> { range = 0 until mid }
            searchKey > sortedList[mid] -> { range = mid + 1..sortedList.lastIndex }
        }
    }

    return null
}


/** Exercism Solution: http://exercism.io/exercises/kotlin/binary-search/readme */
const val NOT_FOUND: Int = -1

fun <T : Comparable<T>> binarySearchExercism(list: List<T>, searchKey: T): Int {
    require(list.isSorted())

    var range = 0..list.lastIndex
    var mid: Int

    while (!range.isEmpty()) {
        mid = (range.start + range.endInclusive) / 2
        when {
            list[mid] == searchKey -> return mid
            searchKey < list[mid] -> { range = 0 until mid }
            searchKey > list[mid] -> { range = mid + 1..list.lastIndex }
        }
    }

    return NOT_FOUND
}

