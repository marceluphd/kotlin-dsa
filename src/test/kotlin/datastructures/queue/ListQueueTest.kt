package datastructures.queue

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ListQueueTest {
    private val q1 = ListQueue<Int>()
    private val q2 = ListQueue(mutableListOf(7, 8, 9))

    @Test
    fun test() {
        assertEquals(0, q1.size)
        assertTrue(q1.isEmpty())
        q1.enqueue(1)
        assertEquals(1, q1.size)
        assertFalse(q1.isEmpty())
        q1.enqueue(2)
        q1.enqueue(3)
        assertEquals(3, q1.size)

        assertEquals(q1.toList(), listOf(1, 2, 3))
        assertEquals(1, q1.peek())

        val one = q1.dequeue()
        assertEquals(1, one)
        assertEquals(2, q1.size)
        val two = q1.dequeue()
        assertEquals(2, two)
        assertEquals(1, q1.size)
        assertEquals(q1.toList(), listOf(3))

        val three = q1.dequeue()
        assertEquals(3, three)

        assertEquals(0, q1.size)
        assertTrue(q1.isEmpty())

        assertNull(q1.peek())
        assertNull(q1.dequeue())
    }

    @Test
    fun testQueueInitializedWithElements() {
        assertEquals(3, q2.size)

        q2.enqueue(99)
        assertEquals(4, q2.size)
        assertEquals(7, q2.dequeue())
        assertEquals(8, q2.dequeue())
        assertEquals(2, q2.size)

        q2.clear()
        assertEquals(0, q2.size)
    }
}
