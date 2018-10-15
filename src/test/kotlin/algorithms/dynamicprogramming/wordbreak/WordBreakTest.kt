package algorithms.dynamicprogramming.wordbreak

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WordBreakTest {

    private val solution = Solution()
    private val bigInputString =
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    private val bigInputWordDict =
        listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")

    @Test
    fun wordBreak() {
        assertTrue(solution.wordBreak(s = "a", wordDict = listOf("a")))
        assertFalse(solution.wordBreak(s = "leetcode", wordDict = listOf("leet")))
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("leet", "code")))
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("leet", "code", "ninja")))
        assertTrue(solution.wordBreak(s = "leetcodeninja", wordDict = listOf("leet", "code", "ninja")))
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("code", "leet")))

        assertTrue(solution.wordBreak(s = "applepenapple", wordDict = listOf("apple", "pen")))
        assertFalse(solution.wordBreak(s = "applepenfoob", wordDict = listOf("apple", "pen")))
        assertFalse(solution.wordBreak(s = "catsandog", wordDict = listOf("cats", "dog", "sand", "and", "cat")))
        assertTrue(solution.wordBreak(s = "catsandog", wordDict = listOf("cats", "dog", "sand", "an", "cat")))

        /* Can a word be used more than once? => Yes */
        assertTrue(solution.wordBreak(s = "aa", wordDict = listOf("a")))
        assertTrue(solution.wordBreak(s = "leetleetcode", wordDict = listOf("leet", "code")))

        /* Are digits, punctuation, whitespace, etc. allowed? => Yes */
        assertTrue(solution.wordBreak(s = "$1.99", wordDict = listOf("$1", ".99")))
        assertTrue(solution.wordBreak(s = "coca-cola$1.99", wordDict = listOf("coca", "cola", "-", "$1", ".99")))
        assertTrue(solution.wordBreak(s = "coca-cola $1.99", wordDict = listOf("coca", "cola", " ", "-", "$1", ".99")))
        assertFalse(solution.wordBreak(s = "coca-cola $1.99", wordDict = listOf("coca", "cola", "-", "$1", ".99")))

        /* Multiple valid solutions */
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("leet", "code", "leetc", "ode")))
        assertTrue(solution.wordBreak(s = "ilikesamsung", wordDict = listOf("i", "like", "sam", "sung", "samsung", "mobile", "ice",
            "cream", "icecream", "man", "go", "mango")))

        /* More than 1 first selection, but only 1 yields an answer */
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("leetc", "od", "leet", "code")))
        assertTrue(solution.wordBreak(s = "leetcode", wordDict = listOf("leet", "code", "leetc", "od")))
        assertTrue(solution.wordBreak(s = "leetcoder", wordDict = listOf("leet", "coder", "leetc", "ode")))
    }

    @Test
    fun wordBreakBigInput() {
        assertFalse(solution.wordBreak(bigInputString, bigInputWordDict))
    }
}