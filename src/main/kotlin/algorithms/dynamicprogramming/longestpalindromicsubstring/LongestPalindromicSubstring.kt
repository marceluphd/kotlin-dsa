package algorithms.dynamicprogramming.longestpalindromicsubstring

/**
 * See [Approach 3](https://leetcode.com/problems/longest-palindromic-substring/solution/).
 * Time: O(n^2)
 * Space: O(n^2)
 */
fun longestPalindrome(s: String): String {
    val n = s.length
    if (n <= 1) return s

    var indicesOfLongest = IntRange.EMPTY

    /* dp[i][j] indicates that s[i..j] is a palindrome */
    val dp: Array<BooleanArray> = Array(n) { BooleanArray(n) }
    for (substrLen in 1..n) {
        (0..(n - substrLen)).forEach { i ->
            var j = i + substrLen - 1
            if (isPalindrome(s, i, j, dp)) {
                indicesOfLongest = i..j
            }
        }
    }

    return s.substring(indicesOfLongest)
}

private fun isPalindrome(s: String, i: Int, j: Int, dp: Array<BooleanArray>): Boolean {
    dp[i][j] = when {
        dp[i][j] -> true
        i == j -> true
        i + 1 == j -> s[i] == s[j]
        else -> dp[i + 1][j - 1] && s[i] == s[j]
    }
    return dp[i][j]
}