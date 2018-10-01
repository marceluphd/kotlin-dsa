package extensions.arrays

import org.junit.Assert.*
import org.junit.Test

class ArrayExtTest {
    @Test
    fun zipWithNext() {
        assertEquals(
            listOf("foo", "bar", "baz", "omega", "theta").zipWithNext(),
            arrayOf("foo", "bar", "baz", "omega", "theta").zipWithNext()
        )
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6).zipWithNext(),
            intArrayOf(1, 2, 3, 4, 5, 6).zipWithNext()
        )
        assertEquals(
            listOf(1).zipWithNext(),
            intArrayOf(1).zipWithNext()
        )
        assertEquals(
            listOf<Int>().zipWithNext(),
            intArrayOf().zipWithNext()
        )
    }

    @Test
    fun isSorted() {
        assertTrue(arrayOf("abc", "def", "ghi").isSorted())
        assertTrue(intArrayOf(1, 2, 3, 4).isSorted())
        assertFalse(intArrayOf(1, 2, 4, 3).isSorted())
        assertTrue(longArrayOf(1L, 2L, 3L, 4L).isSorted())
        assertFalse(longArrayOf(1L, 2L, 4L, 3L).isSorted())
        assertTrue(floatArrayOf(1F, 2F, 3F, 4F).isSorted())
        assertFalse(floatArrayOf(1F, 2F, 4F, 3F).isSorted())
        assertTrue(doubleArrayOf(1.0, 2.0, 3.0, 4.0).isSorted())
        assertFalse(doubleArrayOf(1.0, 2.0, 4.0, 3.0).isSorted())
        assertTrue(shortArrayOf(1, 2, 3, 4).isSorted())
        assertTrue(charArrayOf('1', '2', '3', '4').isSorted())
        assertFalse(charArrayOf('1', '2', '4', '3').isSorted())
        assertTrue(charArrayOf('1', '2', '3', 'A', 'a').isSorted())
    }

    @Test
    fun isSortedDescending() {
        assertTrue(arrayOf("ghi", "def", "abc").isSortedDescending())
        assertTrue(intArrayOf(4, 3, 2, 1).isSortedDescending())
        assertFalse(intArrayOf(4, 3, 1, 2).isSortedDescending())
        assertTrue(longArrayOf(4L, 3L, 2L, 1L).isSortedDescending())
        assertFalse(longArrayOf(1L, 3L, 4L).isSortedDescending())
        assertTrue(floatArrayOf(4F, 3F, 2F, 1F).isSortedDescending())
        assertFalse(floatArrayOf(1F, 2F, 4F, 3F).isSortedDescending())
        assertTrue(doubleArrayOf(4.0, 3.0, 2.0, 1.0).isSortedDescending())
        assertFalse(doubleArrayOf(1.0, 2.0, 4.0, 3.0).isSortedDescending())
        assertTrue(shortArrayOf(4, 3, 2).isSortedDescending())
        assertTrue(charArrayOf('4', '3', '2').isSortedDescending())
        assertFalse(charArrayOf('1', '2', '4', '3').isSortedDescending())
        assertTrue(charArrayOf('a', 'A', '1').isSortedDescending())
    }
}