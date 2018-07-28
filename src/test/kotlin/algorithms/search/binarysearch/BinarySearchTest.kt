package algorithms.search.binarysearch

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BinarySearchTest {

    @Test
    fun shouldReturnNullForAValueNotPresentInTheList() {
        assertNull(binarySearch(listOf(1, 2, 3, 4), 5))
    }

    @Test
    fun shouldReturnNullWhenListIsEmpty() {
        assertNull(binarySearch(emptyList(), 7))
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
    /* Exercism tests */
    @Test(expected = IllegalArgumentException::class)
    fun unsortedListCannotBeSearchedExercism() {
        binarySearchExercism(listOf(1, 2, 4, 3), 1)
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
