package algorithms.dynamicprogramming.longestcommonsubstring

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LongestCommonSubstringTest {

    @Test
    fun lengthOfLongestCommonSubstring() {
        assertEquals(0, lengthOfLongestCommonSubstring("", ""))
        assertEquals(0, lengthOfLongestCommonSubstring("A", ""))
        assertEquals(0, lengthOfLongestCommonSubstring("", "A"))
        assertEquals(1, lengthOfLongestCommonSubstring("A", "A"))
        assertEquals(1, lengthOfLongestCommonSubstring("ABC", "A"))
        assertEquals(2, lengthOfLongestCommonSubstring("ABC", "BABA"))
        assertEquals(6, lengthOfLongestCommonSubstring("ABCABCA", "CABCABC"))
        assertEquals(6, lengthOfLongestCommonSubstring("CABCABC", "ABCABCA"))
        assertEquals(4, lengthOfLongestCommonSubstring("abcdxyz", "xyzabcd"))
        assertEquals(6, lengthOfLongestCommonSubstring("zxabcdezy", "yzabcdezx"))
        assertEquals(3, lengthOfLongestCommonSubstring("ABC", "ABCD"))
        assertEquals(3, lengthOfLongestCommonSubstring("ABAB", "BABA"))
    }

    @Test
    fun longestCommonSubstring() {
        assertEquals("", longestCommonSubstring("", ""))
        assertEquals("", longestCommonSubstring("A", ""))
        assertEquals("", longestCommonSubstring("", "A"))
        assertEquals("A", longestCommonSubstring("A", "A"))
        assertEquals("A", longestCommonSubstring("ABC", "A"))
        assertEquals("AB", longestCommonSubstring("ABC", "BABA"))
        assertEquals("ABCABC", longestCommonSubstring("ABCABCA", "CABCABC"))
        assertEquals("ABCABC", longestCommonSubstring("CABCABC", "ABCABCA"))
        assertEquals("abcd", longestCommonSubstring("abcdxyz", "xyzabcd"))
        assertEquals("abcdez", longestCommonSubstring("zxabcdezy", "yzabcdezx"))
        assertEquals("ABC", longestCommonSubstring("ABC", "ABCD"))
        assertTrue(longestCommonSubstring("ABAB", "BABA") in setOf("ABA", "BAB"))
        assertTrue(longestCommonSubstring("ABCD", "CDAB") in setOf("AB", "CD"))
    }

}