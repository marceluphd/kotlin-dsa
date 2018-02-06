package extensions.chars

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharExtTest {

    @Test
    fun numericValue() {
        assertEquals(0, '0'.numericValue())
        assertEquals(8, '8'.numericValue())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `numericValue when not digit`() {
        'C'.numericValue()
    }

    @Test
    fun isVowel() {
        setOf('a', 'e', 'i', 'o', 'u').forEach {
            assertTrue(it.isVowel())
        }

    }
}