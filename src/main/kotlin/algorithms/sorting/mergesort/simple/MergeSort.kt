package algorithms.sorting.mergesort.simple

import extensions.lists.halves

/**
 * Simplest version of mergesort
 */
fun <T : Comparable<T>> mergeSort(elements: List<T>): List<T> {
    if (elements.size in 0..1)
        return elements

    val (left, right) = elements.halves
    return merge(mergeSort(left), mergeSort(right))
}

internal fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> =
    arrayListOf<T>().apply {
        var (leftIdx, rightIdx) = 0 to 0

        while (leftIdx <= left.lastIndex && rightIdx <= right.lastIndex) {
            if (left[leftIdx] < right[rightIdx]) add(left[leftIdx++])
            else add(right[rightIdx++])
        }

        while (leftIdx <= left.lastIndex) add(left[leftIdx++])
        while (rightIdx <= right.lastIndex) add(right[rightIdx++])
    }