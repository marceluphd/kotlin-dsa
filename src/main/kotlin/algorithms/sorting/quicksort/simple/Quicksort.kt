package algorithms.sorting.quicksort.simple

/**
 * from Grokking Algorithms, Ch. 4
 * Very simple version (not super efficient)
 */
fun <T : Comparable<T>> quickSort(elements: List<T>): List<T> =
    when (elements.size) {
        in 0..1 -> elements
        2 -> if (elements[0] < elements[1]) elements else elements.reversed()
        else -> {
            val pivot = elements.first()
            val nonPivotElements = elements.drop(1)
            val (smaller, larger) = nonPivotElements.partition { it < pivot }
            quickSort(smaller) + pivot + quickSort(
                larger
            )
        }
    }


fun main(args: Array<String>) {
    val list = listOf(1, 9, 22, 81, 14, 72, 12, 99, 33, 400, 900, 23, 82)
    println(quickSort(list))
}