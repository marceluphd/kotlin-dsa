package extensions.tuples

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TripleExtTest {

    private val intTriple = Triple(4, 3, 10)
    private val strTriple = Triple("foo", "bar", "baz")

    @Test
    fun toBooleanArray() {
        assertArrayEquals(booleanArrayOf(true, false, true), Triple(true, false, true).toBooleanArray())
    }

    @Test
    fun sum() {
        assertEquals(17, intTriple.sum())
    }

    @Test
    fun product() {
        assertEquals(120, intTriple.product())
    }

    @Test
    fun maxAndMin() {
        assertEquals(10, intTriple.max())
        assertEquals(3, intTriple.min())
    }

    @Test
    fun contains() {
        assertTrue(intTriple.contains(4))
        assertTrue(intTriple.contains(3))
        assertTrue(intTriple.contains(10))
        assertFalse(intTriple.contains(99))

        assertTrue(strTriple.contains("foo"))
        assertFalse(strTriple.contains("foobar"))
        assertFalse(strTriple.contains(4))
    }
}