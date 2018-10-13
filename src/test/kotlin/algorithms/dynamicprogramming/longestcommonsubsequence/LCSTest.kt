package algorithms.dynamicprogramming.longestcommonsubsequence

import org.junit.Test

import org.junit.Assert.*
import kotlin.test.assertTrue

class LCSTest {

    @Test
    fun longestCommonSubsequence() {
        assertEquals(4, longestCommonSubsequence("ABCBDAB", "BDCABA"))
        assertEquals(6, longestCommonSubsequence("GTTCCTAATA", "CGATAATTGAGA"))
        assertEquals(4, longestCommonSubsequence("XMJYAUZ", "MZJAWXU"))
        assertEquals(5, longestCommonSubsequence("ABABCDA", "BBCDAXY"))

    }

    @Test
    fun lcs() {
        assertTrue(lcs("ABCBDAB", "BDCABA") in setOf("BCBA", "BDAB"))
        assertTrue(lcs("GTTCCTAATA", "CGATAATTGAGA") in setOf("GTTTAA", "CTAATA"))
        assertEquals("MJAU", lcs("XMJYAUZ", "MZJAWXU"))
        assertEquals("BBCDA", lcs("ABABCDA", "BBCDAXY"))
    }
}