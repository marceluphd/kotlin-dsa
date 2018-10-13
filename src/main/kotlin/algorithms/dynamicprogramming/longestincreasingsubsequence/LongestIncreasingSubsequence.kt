package algorithms.dynamicprogramming.longestincreasingsubsequence

import kotlin.math.max

/**
 * Returns the length of the [Longest Increasing Subsequence](https://en.wikipedia.org/wiki/Longest_increasing_subsequence).
 */
fun lengthOfLIS(nums: IntArray): Int {
    if (nums.size < 2) return nums.size

    val maxEndingAt = IntArray(nums.size)    // maxEndingAt[i] = the LIS for nums[0..i]
    var globalMax = 0

    for (i in nums.indices) {
        maxEndingAt[i] = 1
        for (k in 0 until i) {
            if (nums[i] > nums[k]) {
                maxEndingAt[i] = max(maxEndingAt[i], maxEndingAt[k] + 1)
            }
        }
        globalMax = max(globalMax, maxEndingAt[i])
    }

    return globalMax
}
