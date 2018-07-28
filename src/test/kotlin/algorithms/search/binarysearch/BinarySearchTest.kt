package algorithms.search.binarysearch

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BinarySearchTest {

    @Test
    fun binarySearch() {
        assertEquals(1, listOf(1, 3, 4, 7, 9).binarySearch(3))
    }


    @Test
    fun binarySearch_ShouldReturnNullForAValueNotPresentInTheList() {
        assertNull(listOf(1, 2, 3, 4).binarySearch(5))
    }

    @Test
    fun binarySearch_ListIsEmpty() {
        assertNull(emptyList<Int>().binarySearch(7))
    }

    @Test
    fun binarySearch_ShouldFindTheItemInASingleElementList() {
        assertEquals(0, listOf(3).binarySearch(3))
    }

    @Test
    fun binarySearch_ShouldFindTheFirstItemInATwoElementList() {
        assertEquals(0, listOf(4, 11).binarySearch(4))
    }

    @Test
    fun binarySearch_ShouldFindTheLastItemInATwoElementList() {
        assertEquals(1, listOf(4, 11).binarySearch(11))
    }

    @Test
    fun binarySearch_ShouldFindTheMidItemInAnOddLengthList() {
        assertEquals(2, listOf(4, 4, 7, 9, 11).binarySearch(7))
    }

    @Test
    fun binarySearch_ShouldFindThePreMidItemInAnEvenLengthList() {
        assertEquals(1, listOf(4, 5, 9, 11).binarySearch(5))
    }

    @Test
    fun binarySearch_ShouldFindThePostMidItemInAnEvenLengthList() {
        assertEquals(2, listOf(4, 5, 17, 29).binarySearch(17))
    }

    /* Exercism tests */
    @Test(expected = IllegalArgumentException::class)
    fun unsortedListCannotBeSearchedExercism() {
        binarySearchExercism(listOf(1, 2, 4, 3), 1)
    }

    @Test
    fun binarySearchExercism() {
        assertEquals(1, binarySearchExercism(listOf(1, 3, 4, 7, 9), 3))
    }

    @Test
    fun shouldReturnMinus1ForAValueNotPresentInTheListExercism() {
        assertEquals(-1, binarySearchExercism(listOf(1, 2, 3, 4), 5))
    }

    @Test
    fun listIsEmptyExercism() {
        assertEquals(-1, binarySearchExercism(emptyList(), 7))
    }

    @Test
    fun shouldFindTheItemInASingleElementListExercism() {
        assertEquals(0, binarySearchExercism(listOf(3), 3))
    }

    @Test
    fun shouldFindTheFirstItemInATwoElementListExercism() {
        assertEquals(0, binarySearchExercism(listOf(4, 11), 4))
    }

    @Test
    fun shouldFindTheLastItemInATwoElementListExercism() {
        assertEquals(1, binarySearchExercism(listOf(4, 11), 11))
    }

    @Test
    fun shouldFindTheMidItemInAnOddLengthListExercism() {
        assertEquals(2, binarySearchExercism(listOf(4, 4, 7, 9, 11), 7))
    }

    @Test
    fun shouldFindThePreMidItemInAnEvenLengthListExercism() {
        assertEquals(1, binarySearchExercism(listOf(4, 5, 9, 11), 5))
    }

    @Test
    fun shouldFindThePostMidItemInAnEvenLengthListExercism() {
        assertEquals(2, binarySearchExercism(listOf(4, 5, 17, 29), 17))
    }
}
