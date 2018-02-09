package algorithms.sorting.mergesort.auxarray

import extensions.arrays.isSorted
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MergeSortTest {

    @Test
    fun mergeSort() {
        validateSort(arrayOf())
        validateSort(arrayOf(1))
        validateSort(arrayOf(7, 99, 14, 98, 77, 1, 33, 2, 47, 1234))

        val array = arrayOf(38, 27, 43, 3, 9, 82, 10)
        mergeSort(array)
        assertArrayEquals(arrayOf(3, 9, 10, 27, 38, 43, 82), array)
    }

    private fun validateSort(array: Array<Int>) {
        mergeSort(array)
        assertTrue(array.isSorted())
    }

}