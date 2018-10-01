package algorithms.dynamicprogramming.maxsubarraysum

import kotlin.math.max

/**
 * 53 - https://leetcode.com/problems/maximum-subarray/
 */
object BruteForceAlgorithm {
    /**
     * Brute-force solution
     * 
     * **Time**: `O(n^3)`
     * **Space**: `O(1)`
     */
    fun maxSubArray(nums: IntArray): Int {
        var maxSum = Int.MIN_VALUE        // Assume size >= 1
        (1..nums.size).forEach { k ->
            (0 until (nums.size - k + 1)).forEach { i ->
                var subarraySum = 0
                (i until (i + k)).forEach { j ->
                    subarraySum += nums[j]
                }
                maxSum = max(maxSum, subarraySum)
            }
        }

        return maxSum
    }
}

object DPAlgorithm {
    /**
     * Using Dynamic Programming.
     *
     * **Time**: `O(n)`
     * **Space**: `O(n)`
     */
    fun maxSubArray(nums: IntArray): Int {
        val maxes = IntArray(nums.size)     // The local maxes - the max sum of a subarray ending at i
        maxes[0] = nums[0]
        var globalMax = nums[0]

        for (i in 1..nums.lastIndex) {
            maxes[i] = max(maxes[i - 1] + nums[i], nums[i])
            globalMax = max(globalMax, maxes[i])
        }

        return globalMax
    }
}

object KadanesAlgorithm {
    /**
     * [Kadane's Algorithm](https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm)
     * 
     * **Time**: `O(n)`
     * 
     * **Space**: `O(1)`
     */
    fun maxSubArray(nums: IntArray): Int {
        var maxSoFar = nums[0]
        var maxEndingHere = nums[0]

        (1..nums.lastIndex).forEach { i ->
            maxEndingHere = max(nums[i], maxEndingHere + nums[i])
            maxSoFar = max(maxSoFar, maxEndingHere)
        }

        return maxSoFar
    }
}

object SlidingWindowAlgorithm {
    /**
     * Using the [Sliding Window Technique](http://tinyurl.com/y8e3raev)
     *
     * **Time**: `O(n^2)`
     * 
     * **Space**: `O(1)`
     */
    fun maxSubArray(nums: IntArray): Int {
        var maxSum = Int.MIN_VALUE
        (1..nums.size).forEach { k ->
            val maxSumK = nums.maxSubarraySumOfSize(k)
            maxSum = max(maxSum, maxSumK)
        }
        return maxSum
    }

    /**
     * Calculates the max subarray sum for subarrays of size [k].
     * 
     * **Time**: `O(n)`
     * 
     * **Space**: `O(1)`
     */
    private fun IntArray.maxSubarraySumOfSize(k: Int): Int {
        var maxSum: Int

        /* Calculate sum of 1st window */
        var windowSum = 0
        (0 until k).forEach { i ->
            windowSum += this[i]
        }
        maxSum = windowSum

        /* Slide window from start to end in array. Here i is the last element in the current window. */
        (k until size).forEach { i ->
            windowSum += this[i] - this[i - k]
            maxSum = max(maxSum, windowSum)
        }

        return maxSum
    }
}
