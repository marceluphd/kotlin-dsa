package algorithms.dynamicprogramming.longestcommonsubsequence

import kotlin.math.max

/**
 * [Longest Common Subsequence Problem](https://en.wikipedia.org/wiki/Longest_common_subsequence_problem)
 *
 * Returns the length of the LCS.
 *
 * ```
 * LCS(X[:i], Y[:j]) =
 *      0                                                           if i = 0 or j = 0
 *      LCS(X[:i-1], Y[:j-1]) + X[i]                                if X[i]  = Y[j]
 *      max ( LCS(X[:i-1], Y[:j], LCS(X[:i], Y[:j-1]) )             if X[i] != Y[j]
 * ```
 */
fun longestCommonSubsequence(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }

    // Leave dp[0][j] and dp[i][0] (first row and column) as zeroes
    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]
            } else {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
            }
        }
    }

    println(solutionGrid(a, b, dp))
    return dp[a.length][b.length]
}

/**
 * Returns the actual LCS.
 */
fun lcs(a: String, b: String): String {
    val dp: Array<Array<String>> = Array(a.length + 1) { Array(b.length + 1) { "" } }

    // Leave dp[0][j] and dp[i][0] (first row and column) as ""
    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + a[i - 1]
            } else {
                dp[i][j] = setOf(dp[i][j - 1], dp[i - 1][j]).maxBy { it.length }!!
            }
        }
    }

    return dp[a.length][b.length]
}

/**
 * Returns a formatted string of the results table.
 *
 * ```
 * |   | X | B | D | C | A | B | A |
 * | X | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 * | A | 0 | 0 | 0 | 0 | 1 | 1 | 1 |
 * | B | 0 | 1 | 1 | 1 | 1 | 2 | 2 |
 * | C | 0 | 1 | 1 | 2 | 2 | 2 | 2 |
 * | B | 0 | 1 | 1 | 2 | 2 | 3 | 3 |
 * | D | 0 | 1 | 2 | 2 | 2 | 3 | 3 |
 * | A | 0 | 1 | 2 | 2 | 3 | 3 | 4 |
 * | B | 0 | 1 | 2 | 2 | 3 | 4 | 4 |
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
