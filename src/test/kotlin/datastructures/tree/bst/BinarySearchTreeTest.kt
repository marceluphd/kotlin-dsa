package datastructures.tree.bst

import datastructures.exceptions.ItemNotFoundException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BinarySearchTreeTest {

    private var emptyTree = BinarySearchTree<Int>()
    private var rootTree = BinarySearchTree<Int>()

    // TODO - test tree with duplicates

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

    private var duplicatesTree: BinarySearchTree<String> = BinarySearchTree()

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

        duplicatesTree.add("banana")
        duplicatesTree.add("apple")
        duplicatesTree.add("tomato")
        duplicatesTree.add("banana")
        duplicatesTree.add("apple")
        duplicatesTree.add("banana")
    }

    @After
    fun tearDown() {
        tree1.clear()
        tree2.clear()
        emptyTree.clear()
        rootTree.clear()
        duplicatesTree.clear()
    }

    @Test
    fun size() {
        assertEquals(0, emptyTree.size)
        assertEquals(15, tree1.size)
        assertEquals(7, tree2.size)
        assertEquals(6, duplicatesTree.size)
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

        val bananaNode = BinaryTreeNode("banana")
        bananaNode.count = 3
        val appleNode = BinaryTreeNode("apple")
        appleNode.count = 2
        val tomatoNode = BinaryTreeNode("tomato")
        val mangoNode = BinaryTreeNode("mango")
        assertEquals(6, duplicatesTree.size)
        assertEquals(bananaNode, duplicatesTree.root)
        assertEquals(appleNode, duplicatesTree.root?.left)
        assertEquals(tomatoNode, duplicatesTree.root?.right)
        duplicatesTree.add("mango")
        assertEquals(7, duplicatesTree.size)
        assertEquals(bananaNode, duplicatesTree.root)
        assertEquals(appleNode, duplicatesTree.root?.left)
        assertEquals(tomatoNode, duplicatesTree.root?.right)
        assertEquals(mangoNode, duplicatesTree.root?.right?.left)
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

    @Test
    fun `remove - leaf`() {
        assertEquals("[4, 8, 10, 12, 14, 16, 20]", tree2.contentToString())
        assertEquals(7, tree2.size)
        assertEquals(10, tree2.remove(10))
        assertEquals(6, tree2.size)
        assertEquals("[4, 8, 12, 14, 16, 20]", tree2.contentToString())

        assertEquals("[apple, apple, banana, banana, banana, tomato]", duplicatesTree.contentToString())
        assertEquals("apple", duplicatesTree.remove("apple"))
        assertEquals("[apple, banana, banana, banana, tomato]", duplicatesTree.contentToString())
    }

    @Test
    fun `remove - delete node with L subtree`() {
        assertEquals(15, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
        assertEquals(9, tree1.remove(9))
        assertEquals(14, tree1.size)
        assertEquals("[0, 7, 8, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
    }

    @Test
    fun `remove - delete node with R subtree`() {
        tree1.add(500)
        assertEquals(16, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30, 500]", tree1.contentToString())
        assertEquals(30, tree1.remove(30))
        assertEquals(15, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 500]", tree1.contentToString())
    }

    @Test
    fun `remove - delete node with two subtrees`() {
        assertEquals(15, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
        assertEquals(20, tree1.root?.data)
        assertEquals(15, tree1.root?.left?.data)
        assertEquals(17, tree1.root?.left?.right?.data)
        assertEquals(16, tree1.root?.left?.right?.left?.data)
        assertEquals(18, tree1.root?.left?.right?.right?.data)

        assertEquals(17, tree1.remove(17))
        assertEquals(14, tree1.size)
        assertEquals(20, tree1.root?.data)
        assertEquals(15, tree1.root?.left?.data)
        assertEquals(18, tree1.root?.left?.right?.data)
        assertEquals(16, tree1.root?.left?.right?.left?.data)
        assertNull(tree1.root?.left?.right?.right?.data)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
    }

    @Test
    fun `remove - delete node with two subtrees - root`() {
        assertEquals(15, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
        assertEquals(20, tree1.root?.data)
        assertEquals(15, tree1.root?.left?.data)
        assertEquals(27, tree1.root?.right?.data)
        assertEquals(22, tree1.root?.right?.left?.data)

        assertEquals(20, tree1.remove(20))
        assertEquals(14, tree1.size)
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 22, 27, 28, 29, 30]", tree1.contentToString())
        assertEquals(22, tree1.root?.data)
        assertEquals(15, tree1.root?.left?.data)
        assertEquals(27, tree1.root?.right?.data)
        assertNull(tree1.root?.right?.left)
    }

    @Test(expected = ItemNotFoundException::class)
    fun `remove - not in tree`() {
        tree2.remove(77)
    }

    @Test(expected = ItemNotFoundException::class)
    fun `remove - not in tree 2`() {
        duplicatesTree.remove("foobar")
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
        // TODO - test duplicates
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
        // TODO - test duplicates
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
        // TODO - test duplicates
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

        assertTrue(12 in tree2)
        assertFalse(99 in tree2)
    }

    @Test
    fun min() {
        // TODO - test duplicates
        assertEquals(0, tree1.min())
        assertEquals(4, tree2.min())
        assertEquals(99, rootTree.min())
        assertNull(emptyTree.min())
    }

    @Test
    fun max() {
        // TODO - test duplicates
        assertEquals(30, tree1.max())
        assertEquals(20, tree2.max())
        assertEquals(rootTree.root?.data, rootTree.max())
        assertNull(emptyTree.max())
    }

    @Test
    fun height() {
        assertEquals(-1, emptyTree.height)
        assertEquals(0, rootTree.height)
        assertEquals(5, tree1.height)
        assertEquals(2, tree2.height)
        assertEquals(1, duplicatesTree.height)
    }

    @Test
    fun dfs() {
        // TODO - test duplicates
        val values = arrayListOf<Int>()
        tree2.dfs { values += it }
        assertEquals(listOf(4, 8, 10, 12, 14, 16, 20), values)
    }

    @Test
    fun bfs() {
        // TODO - test duplicates
        val values = arrayListOf<Int>()
        tree2.bfs { values += it }
        assertEquals(listOf(12, 8, 16, 4, 10, 14, 20), values)
    }

    @Test
    fun collect() {
        // TODO - test duplicates
        assertEquals(listOf(0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30), tree1.collect())
        assertEquals(listOf(4, 8, 10, 12, 14, 16, 20).map { it * 2 }, tree2.collect { it * 2 })
    }

    @Test
    fun levels() {
        assertEquals(
            listOf(
                listOf(12 to 1),
                listOf(8 to 1, 16 to 1),
                listOf(4 to 1, 10 to 1, 14 to 1, 20 to 1)
            ),
            tree2.levels()
        )

        assertEquals(
            listOf(
                listOf(8 to 1),
                listOf(4 to 1, 10 to 1)
            ),
            tree2.levels(tree2.root?.left)
        )

        assertEquals(
            listOf(
                listOf("banana" to 3),
                listOf("apple" to 2, "tomato" to 1)
            ),
            duplicatesTree.levels()
        )
    }

    @Test
    fun toList() {
        assertEquals(listOf(0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30), tree1.toList())
        assertEquals(listOf(4, 8, 10, 12, 14, 16, 20), tree2.toList())
        assertEquals(listOf(99), rootTree.toList())
        assertEquals(listOf("apple", "apple", "banana", "banana", "banana", "tomato"), duplicatesTree.toList())
        assertEquals(emptyList<Int>(), emptyTree.toList())
    }

    @Test
    fun toMutableList() {
        assertEquals(mutableListOf(0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30), tree1.toMutableList())
        assertEquals(mutableListOf(4, 8, 10, 12, 14, 16, 20), tree2.toMutableList())
        assertEquals(mutableListOf(99), rootTree.toMutableList())
        assertEquals(mutableListOf("apple", "apple", "banana", "banana", "banana", "tomato"), duplicatesTree.toMutableList())
        assertEquals(mutableListOf<Int>(), emptyTree.toMutableList())
    }

    @Test
    fun toSet() {
        assertEquals(setOf(0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30), tree1.toSet())
        assertEquals(setOf(4, 8, 10, 12, 14, 16, 20), tree2.toSet())
        assertEquals(setOf(99), rootTree.toSet())
        assertEquals(setOf<Int>(), emptyTree.toSet())
        assertEquals(setOf("apple", "banana", "tomato"), duplicatesTree.toSet())
    }

    @Test
    fun contentToString() {
        assertEquals("[0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30]", tree1.contentToString())
        assertEquals("[4, 8, 10, 12, 14, 16, 20]", tree2.contentToString())
        assertEquals("[99]", rootTree.contentToString())
        assertEquals("[]", emptyTree.contentToString())
    }

    @Test
    fun iterator() {
        val tree1Elements = listOf(0, 7, 8, 9, 10, 15, 16, 17, 18, 20, 22, 27, 28, 29, 30)
        var i = 0
        for (element in tree1) {
            assertEquals(element, tree1Elements[i++])
        }

        i = 0
        tree1.forEach {
            assertEquals(it, tree1Elements[i++])
        }

        tree1.forEachIndexed { index, element ->
            assertEquals(element, tree1Elements[index])
        }
    }
}