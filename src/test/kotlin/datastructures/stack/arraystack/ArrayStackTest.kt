package datastructures.stack.arraystack

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class ArrayStackTest {

    private val stack = ArrayStack<String>()
    private val emptyStack = ArrayStack<Int>()

    @Before
    fun setUp() {
        stack.apply {
            push("C")
            push("B")
            push("A")
        }
    }

    @Test
    fun push() {
        assertEquals(3, stack.size)
        stack.push("Z")
        assertEquals(4, stack.size)
        assertEquals("Z", stack.peek())
    }

    @Test
    fun pop() {
        assertEquals(3, stack.size)
        assertEquals("A", stack.pop())
        assertEquals(2, stack.size)
        assertEquals("B", stack.pop())
        assertEquals(1, stack.size)
        assertEquals("C", stack.pop())
        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
    }

    @Test(expected = EmptyStackException::class)
    fun popWhenEmpty() {
        emptyStack.pop()
    }

    @Test
    fun peek() {
        assertEquals(3, stack.size)
        assertEquals("A", stack.peek())
        assertEquals(3, stack.size)
    }

    @Test(expected = EmptyStackException::class)
    fun peekWhenEmpty() {
        emptyStack.peek()
    }

    @Test
    fun isEmpty() {
        assertTrue(emptyStack.isEmpty())
        assertEquals(0, emptyStack.size)
        assertFalse(stack.isEmpty())
        assertEquals(3, stack.size)
    }

    @Test
    fun isNotEmpty() {
        assertFalse(emptyStack.isNotEmpty())
        assertEquals(0, emptyStack.size)
        assertTrue(stack.isNotEmpty())
        assertEquals(3, stack.size)
    }

    @Test
    fun testToString() {
        assertEquals(
            "ArrayStack(elements=[C, B, A])", stack.toString()
        )
    }
    
    @Test
    fun iterator() {
        val expected = listOf("C", "B", "A")
        var i = 0
        stack.forEach { element ->
            assertEquals(expected[i++], element)
        }
    }
}
