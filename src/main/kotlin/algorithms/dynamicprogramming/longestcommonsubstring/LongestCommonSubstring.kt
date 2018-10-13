package algorithms.dynamicprogramming.longestcommonsubstring

import kotlin.math.max

/**
 * [Wiki - Longest Common Substring Problem](https://en.wikipedia.org/wiki/Longest_common_substring_problem)
 */

/**
 * Returns the length of the longest common substring.
 */
fun lengthOfLongestCommonSubstring(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    var maxLength = 0

    // Leave dp[0][j] and dp[i][0] (first row and column) as zeroes
    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]
                maxLength = max(maxLength, dp[i][j])
            } // otherwise, leave dp[i][j] = 0
        }
    }

    return maxLength
}

/**
 * Returns the [Longest Common Substring](https://en.wikipedia.org/wiki/Longest_common_substring_problem) of
 * [a] and [b].
 *
 * **Note**: there may be multiple substrings with the same length - this returns *one* of the longest substrings from
 * the solution set
 * @return longest common substring
 */
fun longestCommonSubstring(a: String, b: String): String {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    var maxLength = 0
    var endIndex = 0

    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j]
                    endIndex = i
                }
            }
        }
    }

    println(solutionGrid(a, b, dp))

    return a.substring((endIndex - maxLength) until endIndex)
}

/**
 * Returns a formatted string of the results table.
 * ```
 * |   | X | B | A | B | A |
 * | X | 0 | 0 | 0 | 0 | 0 |
 * | A | 0 | 0 | 1 | 0 | 1 |
 * | B | 0 | 1 | 0 | 2 | 0 |
 * | A | 0 | 0 | 2 | 0 | 3 |
 * | B | 0 | 1 | 0 | 3 | 0 |
 * ```
 */
private fun solutionGrid(a: String, b: String, grid: Array<IntArray>): String =
    with (StringBuilder()) {
        // Top Header Row
        append("|   | X | ")
        if (b.isNotEmpty())
            append(b.toList().joinToString(" | ") + " |") // rest of top header row
        append("\n")

        // Rows with left header column
        val rowStart: (Int) -> String = { i -> if (i == 0) "| X | " else "| " + a[i - 1] + " | " }
        (0..a.length).forEach { i ->
            append(rowStart(i))
            append(grid[i].joinToString(" | ") + " |\n")
        }
        toString()
    }
