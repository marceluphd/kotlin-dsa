package algorithms.dynamicprogramming.longestincreasingsubsequence

import org.junit.Assert.*
import org.junit.Test

class LongestIncreasingSubsequenceTest {

    @Test
    fun lengthOfLIS() {
        assertEquals(0, lengthOfLIS(intArrayOf()))
        assertEquals(1, lengthOfLIS(intArrayOf(7)))
        assertEquals(1, lengthOfLIS(intArrayOf(7, 1)))
        assertEquals(2, lengthOfLIS(intArrayOf(1, 7)))
        assertEquals(2, lengthOfLIS(intArrayOf(1, 7, 1)))
        assertEquals(2, lengthOfLIS(intArrayOf(1, 2, 2, 2)))
        assertEquals(4, lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)))
        assertEquals(4, lengthOfLIS(intArrayOf(2, 5, 3, 4, 6)))
        assertEquals(5, lengthOfLIS(intArrayOf(2, 4, 3, 5, 1, 7, 6, 9, 8)))
        assertEquals(4, lengthOfLIS(intArrayOf(6, 3, 5, 2, 7, 8, 1)))
        assertEquals(3, lengthOfLIS(intArrayOf(3, 10, 2, 1, 20)))
        assertEquals(4, lengthOfLIS(intArrayOf(50, 3, 10, 7, 40, 80)))
        assertEquals(6, lengthOfLIS(intArrayOf(10, 22, 9, 33, 21, 50, 41, 60, 80)))
    }
}