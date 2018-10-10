package datastructures.tree.bst

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.After
import org.junit.Assert.*


class BinarySearchTreeTest {

    private var emptyTree = BinarySearchTree<Int>()
    private var rootTree = BinarySearchTree<Int>()

    /**
     * ```
     *                20
     *             /      \
     *           15         27
     *          /  \        /  \
     *        10    17    22     29
     *       /     /  \         /  \
     *      7     16   18     28    30
     *     / \
     *    0   9
     *       /
     *      8
     *
     *
     * ```
     */
    private var tree1: BinarySearchTree<Int> = BinarySearchTree()

    /**
     * ```
     *              (12)
     *        ┌───────┴───────┐
     *       (8)            (16)
     *    ┌───┴───┐       ┌───┴───┐
     *   (4)    (10)    (14)    (20)
     *
     * ```
     */
    private var tree2: BinarySearchTree<Int> = BinarySearchTree()

    @Before
    fun setUp() {
        rootTree.add(99)

        tree1.add(20)
        tree1.add(15)
        tree1.add(10)
        tree1.add(7)
        tree1.add(0)
        tree1.add(27)
        tree1.add(29)
        tree1.add(30)
        tree1.add(28)
        tree1.add(22)
        tree1.add(17)
        tree1.add(16)
        tree1.add(18)
        tree1.add(9)
        tree1.add(8)

        tree2.add(12)
        tree2.add(8)
        tree2.add(4)
        tree2.add(10)
        tree2.add(16)
        tree2.add(20)
        tree2.add(14)
    }

    @After
    fun tearDown() {
        tree1.clear()
        tree2.clear()
        emptyTree.clear()
        rootTree.clear()
    }

    @Test
    fun size() {
        assertEquals(0, emptyTree.size)
        assertEquals(15, tree1.size)
        assertEquals(7, tree2.size)
    }

    @Test
    fun add() {
        assertEquals(0, emptyTree.size)
        emptyTree.add(99)
        assertNotNull(emptyTree.root)
        assertEquals(1, emptyTree.size)
        assertEquals(99, emptyTree.root!!.data)
        emptyTree.add(1)
        assertEquals(99, emptyTree.root!!.data)
        assertNotNull(emptyTree.root!!.left)
        assertEquals(1, emptyTree.root?.left?.data)
        assertEquals(2, emptyTree.size)

        tree1.add(0)
        assertEquals(2, emptyTree.size)
    }

    @Test
    fun `add - duplicate`() {
        assertEquals(1, rootTree.root?.count)
        rootTree.add(99)
        assertEquals(2, rootTree.root?.count)
        assertEquals(2, rootTree.size)
    }

    @Test
    fun addAll() {
        rootTree.addAll(listOf(1, 7, 2, 4, 3, 5))
        assertTrue(1 in rootTree)
        assertTrue(7 in rootTree)
        assertTrue(2 in rootTree)
        assertTrue(4 in rootTree)
        assertTrue(3 in rootTree)
        assertTrue(5 in rootTree)
        assertEquals(7, rootTree.size)
    }

    @Ignore
    @Test
    fun remove() {
        TODO()
    }

    @Test
    fun isEmpty() {
        assertTrue(emptyTree.isEmpty())
        assertFalse(tree1.isEmpty())
        tree1.clear()
        assertTrue(tree1.isEmpty())
        assertFalse(tree2.isEmpty())
    }

    @Test
    fun isNotEmpty() {
        assertFalse(emptyTree.isNotEmpty())
        emptyTree.add(1)
        assertTrue(emptyTree.isNotEmpty())
        assertTrue(tree1.isNotEmpty())
    }

    @Test
    fun clear() {
        emptyTree.add(1)
        emptyTree.add(1)
        emptyTree.add(1)
        assertEquals(emptyTree.size, 3)
        emptyTree.clear()
        assertEquals(emptyTree.size, 0)

        assertEquals(7, tree2.size)
        tree2.clear()
        assertEquals(0, tree2.size)
    }

    @Test
    fun search() {
        assertEquals(12, tree2.search(12))
        assertEquals(8, tree2.search(8))
        assertEquals(16, tree2.search(16))
        assertEquals(4, tree2.search(4))
        assertEquals(10, tree2.search(10))
        assertEquals(14, tree2.search(14))
        assertEquals(20, tree2.search(20))
        assertNull(tree2.search(99))
    }

    @Test
    fun contains() {
        assertTrue(tree2.contains(12))
        assertTrue(12 in tree2)
        assertFalse(tree2.contains(99))
        assertFalse(99 in tree2)


        assertTrue(tree1.contains(0))
        assertTrue(tree1.contains(30))
        assertTrue(tree1.contains(7))
        assertTrue(tree1.contains(15))
        assertTrue(tree1.contains(10))
        assertTrue(tree1.contains(10))
        assertTrue(tree1.contains(0))

        assertFalse(emptyTree.contains(9))

        assertFalse(tree2.contains(7))
        assertFalse(tree2.contains(15))
        assertTrue(tree1.contains(10))
        assertTrue(tree2.contains(12))
        assertTrue(tree2.contains(14))
        assertTrue(tree2.contains(8))
    }

    @Ignore
    @Test
    fun toList() {

    }

    @Ignore
    @Test
    fun toMutableList() {

    }
}