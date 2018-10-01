package algorithms.backtracking.parentheses

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * [LeetCode #22](https://leetcode.com/problems/generate-parentheses/)
 */
class Solution {
    /**
     * Backtracking
     * https://leetcode.com/problems/generate-parentheses/discuss/10098/Java-DFS-way-solution
     *
     * Time: O(?)
     * Space: O(?)
     */
    fun generateParenthesis(n: Int): List<String> {
        val result = ArrayList<String>()
        generateOneByOne("", n, n, result)
        return result
    }

    private fun generateOneByOne(
        substring: String,
        openBraces: Int,
        closeBraces: Int,
        result: MutableList<String>
    ) {
        // Opening braces should be added to the substring first, so if the # of openBraces > # of closeBraces,
        // there is a problem.
        if (openBraces > closeBraces) {
            return
        }

        if (openBraces == 0 && closeBraces == 0) {
            result.add(substring)
        }

        if (openBraces > 0) generateOneByOne("$substring(", openBraces - 1, closeBraces, result)

        if (closeBraces > 0) generateOneByOne("$substring)", openBraces, closeBraces - 1, result)
    }
}
