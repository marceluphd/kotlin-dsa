package algorithms.dynamicprogramming.wordbreak

/**
 * The semi-famous [Word Break](https://leetcode.com/problems/word-break/) problem.
 */
class Solution {
    /**
     * With memoization.
     * Time: O(n^3)
     * Space: O(n^2)
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean = wordBreak(s, wordDict, hashMapOf())

    private fun wordBreak(s: String, wordDict: List<String>, cache: MutableMap<String, Boolean>): Boolean {
        if (s.isEmpty()) return true

        val matchingPrefixes = wordDict.filter { s.startsWith(it) }

        return matchingPrefixes.any { prefix ->
            val afterPrefix = s.drop(prefix.length)
            if (!cache.contains(afterPrefix)) {
                cache[afterPrefix] = wordBreak(afterPrefix, wordDict, cache)
            }
            cache[afterPrefix]!!
        }
    }
}