package datastructures.queue.arrayqueue

import datastructures.queue.IQueue
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ArrayQueueTest {
    private val q1 = ArrayQueue<Int>()
    private var q2: IQueue<Int> = ArrayQueue()

    @Before
    fun setUp() {
        q2.enqueue(7)
        q2.enqueue(8)
        q2.enqueue(9)
    }

    @After
    fun tearDown() {
        q1.clear()
        q2.clear()
    }

    @Test
    fun enqueue() {
        assertFalse(q1.isNotEmpty())
        assertTrue(q2.isNotEmpty())

        assertEquals(0, q1.size)
        assertTrue(q1.isEmpty())
        q1.enqueue(1)
        assertEquals(1, q1.size)
        assertFalse(q1.isEmpty())
        q1.enqueue(2)
        q1.enqueue(3)
        assertEquals(3, q1.size)

        assertEquals(3, q2.size)
        q2.enqueue(99)
        assertEquals(4, q2.size)
        assertEquals(7, q2.dequeue())
        assertEquals(8, q2.dequeue())
        assertEquals(2, q2.size)
    }

    @Test
    fun peek() {
        assertNull(q1.peek())
        assertEquals(0, q1.size)
        q1.enqueue(1)
        assertEquals(1, q1.size)
        assertEquals(1, q1.peek())
        assertEquals(1, q1.size)
    }

    @Test
    fun dequeue() {
        val seven = q2.dequeue()
        assertEquals(7, seven)
        assertEquals(2, q2.size)
        val eight = q2.dequeue()
        assertEquals(8, eight)
        assertEquals(1, q2.size)
        val nine = q2.dequeue()
        assertEquals(9, nine)
        assertNull(q2.dequeue())
    }

    @Test
    fun size() {
        assertEquals(0, q1.size)
        assertEquals(3, q2.size)
    }

    @Test
    fun testToString() {
        assertEquals("ArrayQueue(elements=[7, 8, 9])", q2.toString())
    }

    @Test
    fun isEmpty() {
        assertTrue(q1.isEmpty())
        assertFalse(q2.isEmpty())
    }
    
    @Test
    fun isNotEmpty() {
        assertFalse(q1.isNotEmpty())
        assertTrue(q2.isNotEmpty())
    }

}
