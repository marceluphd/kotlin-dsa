package algorithms.search.binarysearch

import org.junit.Test
import kotlin.test.assertEquals

class BinarySearchTest {

    /* TODO: binarySearch() tests */

    /* Exercism tests */
    @Test(expected = IllegalArgumentException::class)
    fun unsortedListCannotBeSearched() {
        binarySearchExercism(listOf(1, 2, 4, 3), 1)
    }

    @Test
    fun shouldReturnMinus1ForAValueNotPresentInTheList() {
        assertEquals(-1, binarySearchExercism(listOf(1, 2, 3, 4), 5))
    }

    @Test
    fun listIsEmpty() {
        assertEquals(-1, binarySearchExercism(emptyList(), 7))
    }

    @Test
    fun shouldFindTheItemInASingleElementList() {
        assertEquals(0, binarySearchExercism(listOf(3), 3))
    }

    @Test
    fun shouldFindTheFirstItemInATwoElementList() {
        assertEquals(0, binarySearchExercism(listOf(4, 11), 4))
    }

    @Test
    fun shouldFindTheLastItemInATwoElementList() {
        assertEquals(1, binarySearchExercism(listOf(4, 11), 11))
    }

    @Test
    fun shouldFindTheMidItemInAnOddLengthList() {
        assertEquals(2, binarySearchExercism(listOf(4, 4, 7, 9, 11), 7))
    }

    @Test
    fun shouldFindThePreMidItemInAnEvenLengthList() {
        assertEquals(1, binarySearchExercism(listOf(4, 5, 9, 11), 5))
    }

    @Test
    fun shouldFindThePostMidItemInAnEvenLengthList() {
        assertEquals(2, binarySearchExercism(listOf(4, 5, 17, 29), 17))
    }
}
