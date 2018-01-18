package datastructures.list

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ListNodeTest {

    private var listA: ListNode? = null

    @Before
    fun setUp() {
        listA = ListNode(0)
        listA?.next = ListNode(1)
        listA?.next?.next = ListNode(2)
        listA?.next?.next?.next = ListNode(3)
    }

    @After
    fun tearDown() {
        listA = null
    }

    @Test
    fun from() {
        assertNull(ListNode.from(emptyList()))

        val createdList = ListNode.from(listOf(0, 1, 2, 3))
        assertNotNull(createdList)
        assertEquals(4, createdList?.size)
        assertEquals(listA, createdList)
    }

    @Test
    fun testLinkedListOf() {
        assertEquals(listOf(1), linkedListOf(1).toList())
        assertEquals(listOf(1, 2), linkedListOf(1, 2).toList())
        assertEquals(listOf(1, 2, 3), linkedListOf(1, 2, 3).toList())
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1), linkedListOf(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1).toList())
    }

    @Test(expected = IllegalArgumentException::class)
    fun linkedListOfException() {
        linkedListOf()
    }

    @Test
    fun equals() {
        val a = linkedListOf(1, 2, 3, 4)
        val b = linkedListOf(1, 2, 3, 5)
        assertFalse(a == b)
        assertNotEquals(a, b)

        val c = linkedListOf(1, 2, 3, 4)
        assertTrue(a == c)
        assertEquals(a, c)

        // 1->2->4
        a.next?.next = a.next?.next?.next
        c.next?.next = c.next?.next?.next
        assertEquals(listOf(1, 2, 4), a.toList())
        assertEquals(listOf(1, 2, 4), c.toList())
        assertEquals(linkedListOf(1, 2, 4), a)
        assertEquals(linkedListOf(1, 2, 4), c)
    }

    @Test
    fun size() {
        val createdList = ListNode.from(listOf(0, 1, 2, 3))
        assertEquals(4, createdList?.size)
    }

    @Test
    fun toIntArray() {
        val linkedList = ListNode.from(listOf(0, 1, 2, 3))
        val array = IntArray(4, { i -> i })
        val arrFromLL = linkedList?.toIntArray()
        assertEquals(4, arrFromLL?.size)
        assertEquals(array.contentToString(), arrFromLL?.contentToString())
    }

    @Test
    fun contentToString() {
        val linkedList = ListNode.from(listOf(0, 1, 2, 3))
        assertEquals("(0)->(1)->(2)->(3)", linkedList?.contentToString())
    }

    @Test
    fun middleNode() {
        val expected = mapOf(
            null to null,
            linkedListOf(1) to 1,
            linkedListOf(1, 2) to 2,
            linkedListOf(1, 2, 3) to 2,
            linkedListOf(1, 2, 3, 4) to 3,
            linkedListOf(1, 2, 3, 4, 5) to 3,
            linkedListOf(1, 2, 3, 4, 5, 6) to 4
        )

        expected.forEach { list, middleValue ->
            assertEquals(middleValue, list.middleNode()?.`val`)
        }
    }

    @Test
    fun tail() {
        assertNull(null.tail)

        val singleList = linkedListOf(1)
        assertNull(singleList.tail)

        val list12 = linkedListOf(1, 2)
        assertEquals(linkedListOf(2), list12.tail)

        val list123 = linkedListOf(1, 2, 3)
        assertEquals(linkedListOf(2, 3), list123.tail)
    }

    @Test
    fun firstOrNull() {
        val nullList: ListNode? = null
        assertNull(nullList?.firstOrNull { it.`val` == 7 })
        assertNull(linkedListOf(1, 2, 3).firstOrNull { it.`val` < 0 })
        assertEquals(2, linkedListOf(1, 2, 3).firstOrNull { it.`val` >= 2 }?.`val`)
    }
}
