package algorithms.sorting.mergesort.simple

import extensions.lists.isSorted
import org.junit.Test

import org.junit.Assert.*

class MergeSortTest {

    @Test
    fun mergeSort() {
        assertTrue(mergeSort(emptyList<Int>()).isSorted())
        assertTrue(mergeSort(listOf(1)).isSorted())
        assertTrue(mergeSort(listOf(7, 99, 14, 98, 77, 1, 33, 2, 47, 1234)).isSorted())
        assertEquals(listOf(3, 9, 10, 27, 38, 43, 82), mergeSort(listOf(38, 27, 43, 3, 9, 82, 10)))
    }

    @Test
    fun merge() {
        assertEquals(listOf(1, 2, 3, 4), merge(listOf(1, 3), listOf(2, 4)))
        assertEquals(listOf(1, 2, 3, 4), merge(listOf(2, 4), listOf(1, 3)))
        assertEquals(listOf(1, 2, 3, 4), merge(listOf(1, 2, 3, 4), listOf()))
        assertEquals(listOf(1, 2, 3, 4), merge(listOf(), listOf(1, 2, 3, 4)))
        assertEquals(listOf(1, 2, 3, 4), merge(listOf(1, 2), listOf(3, 4)))
        assertEquals(listOf(1, 2, 3, 4, 5), merge(listOf(1, 2, 5), listOf(3, 4)))
    }
}