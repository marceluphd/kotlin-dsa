package extensions.arrays

import org.junit.Assert.*
import org.junit.Test

class ArraysExtTest {
    private val nullRefArray: Array<String?>? = null
    private val arrayWithNulls = arrayOf("foo", null, "bar")
    private val arrayAllNulls: Array<String?> = arrayOf(null, null, null)
    private val arrayNoNulls: Array<String?> = arrayOf("foo", "bar", "baz")
    private val emptyStringArray: Array<String?> = arrayOf()

    private val intArray = intArrayOf(1, 2, 3)
    private val emptyIntArray = intArrayOf()

    private val doubleArray = doubleArrayOf(1.0, 2.0, 3.0)
    private val emptyDoubleArray = doubleArrayOf()

    private val charArray = charArrayOf('1', '2', '3')
    private val emptyCharArray = charArrayOf()

    private val boolArray = booleanArrayOf(true, false, false, true)
    private val emptyBoolArray = booleanArrayOf()


    @Test
    fun head() {
        assertEquals("foo", arrayWithNulls.head)
        assertNull(arrayOf<String>().head)

        assertEquals(1, intArrayOf(1, 2, 3, 4).head)
        assertEquals(1, intArrayOf(1).head)
        assertNull(emptyIntArray.head)

        assertEquals(true, boolArray.head)
        assertNull(emptyBoolArray.head)

        assertEquals('1', charArray.head)
        assertNull(emptyCharArray.head)
    }

    @Test
    fun tail() {
        assertArrayEquals(arrayOf(null, "bar"), arrayWithNulls.tail)
        assertArrayEquals(arrayOf("bar", "baz"), arrayNoNulls.tail)
        assertArrayEquals(emptyStringArray, arrayOf("foo").tail)
        assertArrayEquals(emptyStringArray, emptyStringArray.tail)

        assertArrayEquals(intArrayOf(2, 3), intArray.tail)
        assertArrayEquals(emptyIntArray, intArrayOf(1).tail)
        assertArrayEquals(emptyIntArray, emptyIntArray.tail)

        assertArrayEquals(booleanArrayOf(false, false, true), boolArray.tail)
        assertArrayEquals(emptyBoolArray, booleanArrayOf(false).tail)
        assertArrayEquals(emptyBoolArray, emptyBoolArray.tail)
    }

    @Test
    fun headAndTail() {
        val (h1, t1) = arrayWithNulls.headAndTail
        assertEquals("foo", h1)
        assertArrayEquals(arrayOf(null, "bar"), t1)

        val (h2, t2) = arrayWithNulls.headAndTail
        assertEquals("foo", h2)
        assertArrayEquals(arrayOf(null, "bar"), t2)

        val (h3, t3) = emptyStringArray.headAndTail
        assertNull(h3)
        assertArrayEquals(emptyStringArray, t3)


        val (hi1, ti1) = intArray.headAndTail
        assertEquals(1, hi1)
        assertArrayEquals(intArrayOf(2, 3), ti1)

        val (hb1, tb1) = boolArray.headAndTail
        assertEquals(true, hb1)
        assertArrayEquals(booleanArrayOf(false, false, true), tb1)

        val (hc1, tc1) = charArray.headAndTail
        assertEquals('1', hc1)
        assertArrayEquals(charArrayOf('2', '3'), tc1)

        val (hd2, td2) = emptyDoubleArray.headAndTail
        assertNull(hd2)
        assertArrayEquals(doubleArrayOf(), td2, 0.00001)

        val (hd1, td1) = doubleArray.headAndTail
        assertEquals(1.0, hd1)
        assertArrayEquals(doubleArrayOf(2.0, 3.0), td1, 0.00001)
    }

    @Test
    fun groupingBy() {
        assertEquals(
            mapOf(1 to 2, 7 to 1),
            intArrayOf(1, 7, 1).groupingBy { it }.eachCount()
        )
    }

    @Test
    fun frequencyMap() {
        assertEquals(
            mapOf("foo" to 2, "bar" to 1, "baz" to 1),
            arrayOf("foo", "bar", "foo", "baz").frequencyMap()
        )

        assertEquals(
            mapOf(1 to 2, 7 to 1),
            intArrayOf(1, 7, 1).frequencyMap()
        )
    }

    @Test
    fun valueToIndicesMap() {
        assertEquals(
            mapOf("foo" to listOf(0, 2), "bar" to listOf(1), "baz" to listOf(3)),
            arrayOf("foo", "bar", "foo", "baz").valueToIndicesMap()
        )

        assertEquals(
            mapOf(1 to listOf(0, 2), 7 to listOf(1)),
            intArrayOf(1, 7, 1).valueToIndicesMap()
        )
    }

    @Test
    fun swap() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.swap(0, 2)
        assertArrayEquals(arrayOf("foo", "bar", "foo", "baz"), strArr)
        strArr.swap(1, 3)
        assertArrayEquals(arrayOf("foo", "baz", "foo", "bar"), strArr)

        val intArr = intArrayOf(1, 7, 1, 3, 6, 4)
        intArr.swap(1, 5)
        val expected = intArrayOf(1, 4, 1, 3, 6, 7)
        assertArrayEquals(expected, intArr)
    }

    @Test
    fun reverseElementsInRange() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.reverseElementsInRange(1..3)
        assertArrayEquals(arrayOf("foo", "baz", "foo", "bar"), strArr)

        intArray.reverseElementsInRange(0..2)
        assertArrayEquals(intArrayOf(3, 2, 1), intArray)

        charArray.reverseElementsInRange(0..2)
        assertArrayEquals(charArrayOf('3', '2', '1'), charArray)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `reverseElementsInRange with invalid range`() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.reverseElementsInRange(2..4)
    }

//    @Test
//    fun headAndTailArrays() {
//        assertArrayEquals(intArrayOf(1), intArrayOf(1, 2, 3, 4).headAndTailArrays().first)
//        assertArrayEquals(intArrayOf(2, 3, 4), intArrayOf(1, 2, 3, 4).headAndTailArrays().second)
//        assertArrayEquals(intArrayOf(1), intArrayOf(1).headAndTailArrays().first)
//        assertArrayEquals(intArrayOf(), intArrayOf(1).headAndTailArrays().second)
//        assertArrayEquals(intArrayOf(), intArrayOf().headAndTailArrays().first)
//        assertArrayEquals(intArrayOf(), intArrayOf().headAndTailArrays().second)
//    }

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

    @Test
    fun subarrays() {
        val expected = listOf(
            intArrayOf(1),
            intArrayOf(1, 2),
            intArrayOf(1, 2, 3),
            intArrayOf(1, 2, 3, 4),
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(2),
            intArrayOf(2, 3),
            intArrayOf(2, 3, 4),
            intArrayOf(2, 3, 4, 5),
            intArrayOf(3),
            intArrayOf(3, 4),
            intArrayOf(3, 4, 5),
            intArrayOf(4),
            intArrayOf(4, 5),
            intArrayOf(5)
        )
        val actual = intArrayOf(1, 2, 3, 4, 5).subarrays()
        assertTrue(expected.size == actual.size)
        (expected zip actual).forEach { (e, a) ->
            assertArrayEquals(e, a)
        }
    }

    @Test
    fun sublists() {
        assertEquals(listOf(listOf(1), listOf(1, 2), listOf(2)), intArrayOf(1, 2).sublists())
        assertEquals(listOf(listOf(1), listOf(1, 2), listOf(2)), intArrayOf(1, 2).sublists())

        assertEquals(
            listOf(
                listOf(1),
                listOf(1, 2),
                listOf(1, 2, 3),
                listOf(1, 2, 3, 4),
                listOf(1, 2, 3, 4, 5),
                listOf(2),
                listOf(2, 3),
                listOf(2, 3, 4),
                listOf(2, 3, 4, 5),
                listOf(3),
                listOf(3, 4),
                listOf(3, 4, 5),
                listOf(4),
                listOf(4, 5),
                listOf(5)
            ), intArrayOf(1, 2, 3, 4, 5).sublists()
        )
    }

}