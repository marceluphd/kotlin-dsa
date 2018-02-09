package algorithms.sorting.mergesort.auxarray

/**
 * Sorts the array.
 */
fun <T : Comparable<T>> mergeSort(elements: Array<T>) {
    val aux = elements.clone()
    mergeSort(elements, aux, (0..elements.lastIndex))
}

private fun <T : Comparable<T>> mergeSort(elements: Array<T>, aux: Array<T>, indices: IntRange) {
    val startIdx = indices.start
    val endIdx = indices.endInclusive

    if (endIdx <= startIdx)
        return

    val midIdx = (startIdx + endIdx) / 2

    mergeSort(elements, aux, (startIdx..midIdx))
    mergeSort(elements, aux, (midIdx + 1..endIdx))
    merge(elements, aux, startIdx, midIdx, endIdx)
}

private fun <T : Comparable<T>> merge(elements: Array<T>, aux: Array<T>, startIdx: Int, midIdx: Int, hiIdx: Int) {
    System.arraycopy(
        elements,               // src
        startIdx,               // srcPos
        aux,                    // aux
        startIdx,               // destPos
        hiIdx - startIdx + 1    // length
    )

    var i = startIdx
    var j = midIdx + 1
    (startIdx..hiIdx).forEach { k ->
        elements[k] = when {
            i > midIdx -> aux[j++]
            j > hiIdx -> aux[i++]
            aux[j] < aux[i] -> aux[j++]
            else -> aux[i++]
        }
    }
}
