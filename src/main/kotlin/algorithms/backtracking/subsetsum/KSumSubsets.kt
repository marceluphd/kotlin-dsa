package algorithms.backtracking.subsetsum

/**
 * Given an array of N positive integers, find all the subsets of the given array that sum up to the number K.
 * [GeeksForGeeks](https://www.geeksforgeeks.org/subset-sum-backtracking-4/)
 * [CodeRust](http://tinyurl.com/ycy6kqy9)
 */
class Solution {
    /**
     * Time: O(?)
     * Space: O(?)
     */
    fun getKSumSubsets(nums: List<Int>, k: Int): List<List<Int>> {
        val allSubsets: MutableList<List<Int>> = arrayListOf()
        backtrack(nums, k, arrayListOf(), allSubsets)
        return allSubsets
    }

    private fun backtrack(
        nums: List<Int>,
        k: Int,
        subset: MutableList<Int> = arrayListOf(),
        allSubsets: MutableList<List<Int>>
    ) {
        when {
            k == 0 -> allSubsets += subset
            nums.isEmpty() -> return /* && k != 0 */
            k < 0 -> return
            else -> {
                backtrack(
                    nums = nums.drop(1),
                    k = k - nums.first(),
                    subset = (subset + nums.first()).toMutableList(),
                    allSubsets = allSubsets
                )

                backtrack(
                    nums = nums.drop(1),
                    k = k,
                    subset = subset,
                    allSubsets = allSubsets
                )
            }
        }
    }
}