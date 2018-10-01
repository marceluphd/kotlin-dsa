package extensions.lists

import org.junit.Assert.*
import org.junit.Test

class ListExtTest {

    @Test
    fun halves() {
        assertEquals(Pair(emptyList<Int>(), emptyList<Int>()), emptyList<Int>().halves)
        assertEquals(Pair(listOf(1), emptyList<Int>()), listOf(1).halves)
        assertEquals(Pair(listOf(1), listOf(2)), listOf(1, 2).halves)
        assertEquals(Pair(listOf(1, 2), listOf(3)), listOf(1, 2, 3).halves)
        assertEquals(Pair(listOf(1, 2), listOf(3, 4)), listOf(1, 2, 3, 4).halves)
    }

    @Test
    fun isSorted() {
        assertTrue(emptyList<Int>().isSorted())
        assertTrue(listOf(1).isSorted())
        assertTrue(listOf(1, 99).isSorted())
        assertTrue(listOf(1, 2, 3, 4).isSorted())
        assertFalse(listOf(4, 3, 2, 1).isSorted())
        assertFalse(listOf(1, 2, 4, 3).isSorted())
        assertTrue(listOf("bar", "foo").isSorted())
    }

    @Test
    fun isSortedDescending() {
        assertTrue(emptyList<Int>().isSortedDescending())
        assertTrue(listOf(1).isSortedDescending())
        assertTrue(listOf(100, 99).isSortedDescending())
        assertFalse(listOf(1, 2, 3, 4).isSortedDescending())
        assertTrue(listOf(4, 3, 2, 1).isSortedDescending())
        assertFalse(listOf(1, 2, 4, 3).isSortedDescending())
    }
}