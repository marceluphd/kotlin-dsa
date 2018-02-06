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
    fun toIntArray() {
        assertArrayEquals(intArrayOf(4, 3, 10), intTriple.toIntArray())
    }

    @Test
    fun toBooleanArray() {
        assertArrayEquals(booleanArrayOf(true, false, true), Triple(true, false, true).toBooleanArray())
    }

    @Test
    fun toByteArray() {
        assertArrayEquals(byteArrayOf(0b01, 0b10, 0b11), Triple<Byte, Byte, Byte>(0b01, 0b10, 0b11).toByteArray())
    }

    @Test
    fun toCharArray() {
        assertArrayEquals(charArrayOf('a', 'b', 'c'), Triple('a', 'b', 'c').toCharArray())
    }

    @Test
    fun toDoubleArray() {
        assertArrayEquals(doubleArrayOf(1.0, 2.0, 3.0), Triple(1.0, 2.0, 3.0).toDoubleArray(), 0.000001)
    }

    @Test
    fun toFloatArray() {
        assertArrayEquals(floatArrayOf(123.4f, 567.8f, 999.9f), Triple(123.4f, 567.8f, 999.9f).toFloatArray(), 0.000001f)
    }

    @Test
    fun toLongArray() {
        assertArrayEquals(longArrayOf(1234L, 5678L, 9999L), Triple(1234L, 5678L, 9999L).toLongArray())
    }

    @Test
    fun toShortArray() {
        assertArrayEquals(shortArrayOf(0, 1, 2), Triple<Short, Short, Short>(0, 1, 2).toShortArray())
    }

    @Test
    fun sum() {
        assertEquals(17, intTriple.sum())
        assertEquals(17L, Triple(4L, 3L, 10L).sum())
        assertEquals(18.0, Triple(4.1, 3.2, 10.7).sum(), 0.000001)
        assertEquals(18.0f, Triple(4.1f, 3.2f, 10.7f).sum(), 0.000001f)
    }

    @Test
    fun product() {
        assertEquals(120, intTriple.product())
        assertEquals(120L, Triple(4L, 3L, 10L).product())
        assertEquals(140.384, Triple(4.1, 3.2, 10.7).product(), 0.000001)
        assertEquals(140.384f, Triple(4.1f, 3.2f, 10.7f).product(), 0.000001f)
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