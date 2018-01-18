package extensions.tuples

import extensions.tuples.contains
import extensions.tuples.max
import extensions.tuples.min
import extensions.tuples.product
import extensions.tuples.sum
import extensions.tuples.toBooleanArray
import org.junit.Test

import org.junit.Assert.*

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