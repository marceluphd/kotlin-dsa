package algorithms.search.binarysearch

import org.junit.Test
import kotlin.test.assertEquals

class BinarySearchTest {

    @Test
    fun binarySearch() {
        assertEquals(1, binarySearch(listOf(1, 3, 4, 7, 9), 3))
    }

    @Test
    fun shouldReturnMinus1ForAValueNotPresentInTheList() {
        assertEquals(-1, binarySearch(listOf(1, 2, 3, 4), 5))
    }

    @Test
    fun listIsEmpty() {
        assertEquals(-1, binarySearch(emptyList(), 7))
    }

    @Test
    fun shouldFindTheItemInASingleElementList() {
        assertEquals(0, binarySearch(listOf(3), 3))
    }

    @Test
    fun shouldFindTheFirstItemInATwoElementList() {
        assertEquals(0, binarySearch(listOf(4, 11), 4))
    }

    @Test
    fun shouldFindTheLastItemInATwoElementList() {
        assertEquals(1, binarySearch(listOf(4, 11), 11))
    }

    @Test
    fun shouldFindTheMidItemInAnOddLengthList() {
        assertEquals(2, binarySearch(listOf(4, 4, 7, 9, 11), 7))
    }

    @Test
    fun shouldFindThePreMidItemInAnEvenLengthList() {
        assertEquals(1, binarySearch(listOf(4, 5, 9, 11), 5))
    }

    @Test
    fun shouldFindThePostMidItemInAnEvenLengthList() {
        assertEquals(2, binarySearch(listOf(4, 5, 17, 29), 17))
    }
}
