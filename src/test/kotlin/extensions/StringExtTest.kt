package extensions

import org.junit.Assert.*
import org.junit.Test

/**
 *
 */
class StringExtTest {
    @Test
    fun isAllCaps() {
        assertTrue("FOOBAR".isAllCaps)
        assertFalse("FOObar".isAllCaps)
        assertFalse("foobar".isAllCaps)
        assertFalse("FOOBAR 99".isAllCaps)
    }

    @Test
    fun isAllLowercase() {
        assertFalse("FOOBAR".isAllLowercase)
        assertFalse("FOObar".isAllLowercase)
        assertTrue("foobar".isAllLowercase)
        assertFalse("foobar 99".isAllLowercase)
    }


    @Test
    fun characterFrequencies() {
        assertEquals(mapOf('k' to 1, 'o' to 1, 't' to 1, 'l' to 1, 'i' to 1, 'n' to 1),
            "kotlin".characterFrequencies())
        assertEquals(mapOf('f' to 1, 'o' to 2), "foo".characterFrequencies())
        assertEquals(mapOf('f' to 1, 'o' to 2), "FOo".characterFrequencies())
    }

    @Test
    fun toSortedString() {
        assertEquals("abc", "bac".toSortedString())
        assertEquals("aaabbbccde", "cbaebabacd".toSortedString())
        assertEquals("  abc", "b a c".toSortedString())
        assertEquals("!!#$@", "!@#$!".toSortedString())
        assertEquals("14789", "19748".toSortedString())
        assertEquals("14789abc", "19c7a4b8".toSortedString())
        assertEquals("14789ABc", "19c7A4B8".toSortedString())
        assertEquals("\t !$&*14789abc", "1 *&9c7a\t4b!$8".toSortedString())
    }

}