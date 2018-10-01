package algorithms.dynamicprogramming.maxsubarraysum

import org.junit.Assert.*
import org.junit.Test

class MaxSubarraySumTest {

    @Test
    fun `maxSubArray - Brute Force`() {
        assertEquals(-1, BruteForceAlgorithm.maxSubArray(intArrayOf(-1)))
        assertEquals(-1, BruteForceAlgorithm.maxSubArray(intArrayOf(-1, -2, -3)))
        assertEquals(19, BruteForceAlgorithm.maxSubArray(intArrayOf(0, 1, 2, 4, 5, 7)))
        assertEquals(Int.MIN_VALUE, BruteForceAlgorithm.maxSubArray(intArrayOf(Int.MIN_VALUE)))
        assertEquals(6, BruteForceAlgorithm.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }

    @Test
    fun `maxSubArray - Dynamic Programming`() {
        assertEquals(-1, DPAlgorithm.maxSubArray(intArrayOf(-1)))
        assertEquals(-1, DPAlgorithm.maxSubArray(intArrayOf(-1, -2, -3)))
        assertEquals(19, DPAlgorithm.maxSubArray(intArrayOf(0, 1, 2, 4, 5, 7)))
        assertEquals(Int.MIN_VALUE, DPAlgorithm.maxSubArray(intArrayOf(Int.MIN_VALUE)))
        assertEquals(6, DPAlgorithm.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }

    @Test
    fun `maxSubArray - Kadane's Algorithm`() {
        assertEquals(-1, KadanesAlgorithm.maxSubArray(intArrayOf(-1)))
        assertEquals(-1, KadanesAlgorithm.maxSubArray(intArrayOf(-1, -2, -3)))
        assertEquals(19, KadanesAlgorithm.maxSubArray(intArrayOf(0, 1, 2, 4, 5, 7)))
        assertEquals(Int.MIN_VALUE, KadanesAlgorithm.maxSubArray(intArrayOf(Int.MIN_VALUE)))
        assertEquals(6, KadanesAlgorithm.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }

    @Test
    fun `maxSubArray - Sliding Window Algorithm`() {
        assertEquals(-1, SlidingWindowAlgorithm.maxSubArray(intArrayOf(-1)))
        assertEquals(-1, SlidingWindowAlgorithm.maxSubArray(intArrayOf(-1, -2, -3)))
        assertEquals(19, SlidingWindowAlgorithm.maxSubArray(intArrayOf(0, 1, 2, 4, 5, 7)))
        assertEquals(Int.MIN_VALUE, SlidingWindowAlgorithm.maxSubArray(intArrayOf(Int.MIN_VALUE)))
        assertEquals(6, SlidingWindowAlgorithm.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }
}