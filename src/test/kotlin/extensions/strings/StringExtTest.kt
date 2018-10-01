package extensions.strings

import org.junit.Assert.*
import org.junit.Test

class StringExtTest {

    @Test
    fun characterFrequencies() {
        assertEquals(
            mapOf('k' to 1, 'o' to 1, 't' to 1, 'l' to 1, 'i' to 1, 'n' to 1),
            "kotlin".characterFrequencies()
        )
        assertEquals(mapOf('f' to 1, 'o' to 2), "foo".characterFrequencies())
        assertEquals(mapOf('f' to 1, 'o' to 2), "FOo".characterFrequencies())
    }
}