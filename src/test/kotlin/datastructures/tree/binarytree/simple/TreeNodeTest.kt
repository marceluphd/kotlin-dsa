package datastructures.tree.binarytree.simple

import datastructures.tree.DFSTraversalOrder
import org.junit.Assert.*
import org.junit.Test

class TreeNodeTest {

    @Test
    fun testToString() {
        assertEquals("(1)", tree123.toString())
    }

    @Test
    fun childProperties() {
        assertTrue(tree123.hasLeft)
        assertTrue(tree123.hasRight)
        assertTrue(tree123.hasTwoChildren)
        assertFalse(tree123.hasSingleChild)

        val rootTree = datastructures.tree.binarytree.simple.buildTree(1)!!
        assertFalse(rootTree.hasLeft)
        assertFalse(rootTree.hasRight)
        assertFalse(rootTree.hasTwoChildren)
        assertFalse(rootTree.hasSingleChild)

        val treeWithRightNode = datastructures.tree.binarytree.simple.buildTree(1, null, 3)!!
        assertFalse(treeWithRightNode.hasLeft)
        assertTrue(treeWithRightNode.hasRight)
        assertFalse(treeWithRightNode.hasTwoChildren)
        assertTrue(treeWithRightNode.hasSingleChild)
    }

    @Test
    fun height() {
        assertEquals(1, tree123.height)
        assertEquals(2, tree1to5.height)
        assertEquals(2, bst3LevelsFull.height)
        assertEquals(3, bstWithNulls.height)
    }


    @Test
    fun children() {
        val (one, four) = bst3LevelsFull.left!!.children
        assertEquals(1, one?.value)
        assertEquals(4, four?.value)
    }

    @Test
    fun find() {
        (1..9).forEach { i ->
            if (i in setOf(2, 7))
                assertNull(bst3LevelsFull.find(i))
            else
                assertEquals(i, bst3LevelsFull.find(i)?.value)
        }

        (1..9).forEach { i ->
            if (i in setOf(2, 5, 6))
                assertNull(bstWithNulls.find(i))
            else
                assertEquals(i, bstWithNulls.find(i)?.value)
        }
    }

    @Test
    fun contains() {
        (1..9).forEach { i ->
            if (i in setOf(2, 7)) {
                assertFalse(bst3LevelsFull.contains(i))
                assertFalse(i in bst3LevelsFull)
            } else {
                assertTrue(bst3LevelsFull.contains(i))
                assertTrue(i in bst3LevelsFull)
            }
        }

        (1..9).forEach { i ->
            if (i in setOf(2, 5, 6)) {
                assertFalse(bstWithNulls.contains(i))
                assertFalse(i in bstWithNulls)
            } else {
                assertTrue(bstWithNulls.contains(i))
                assertTrue(i in bstWithNulls)
            }
        }
    }

    @Test
    fun dfs() {
        val values = arrayListOf<Int>()
        tree123.dfs { values += it.value }
        assertEquals(arrayListOf(2, 1, 3), values)

        val bstValues = arrayListOf<Int>()
        bst3LevelsFull.dfs { bstValues += it.value }
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bstValues.toList())
    }

    @Test
    fun dfsWithOrder() {
        val preorderValues = arrayListOf<Int>()
        tree1to5.dfs(order = DFSTraversalOrder.PREORDER, visit = { preorderValues += it.value })
        assertEquals(arrayListOf(1, 2, 4, 5, 3), preorderValues)

        val inorderValues = arrayListOf<Int>()
        tree1to5.dfs(order = DFSTraversalOrder.INORDER, visit = { inorderValues += it.value })
        assertEquals(arrayListOf(4, 2, 5, 1, 3), inorderValues)

        val postorderValues = arrayListOf<Int>()
        tree1to5.dfs(order = DFSTraversalOrder.POSTORDER, visit = { postorderValues += it.value })
        assertEquals(arrayListOf(4, 5, 2, 3, 1), postorderValues)
    }

    @Test
    fun bfs() {
        val nodesTree123 = arrayListOf<Int>()
        tree123.bfs { nodesTree123 += it.value }
        assertEquals(listOf(1, 2, 3), nodesTree123)

        val nodesTree1to5 = arrayListOf<Int>()
        tree1to5.bfs { nodesTree1to5 += it.value }
        assertEquals(listOf(1, 2, 3, 4, 5), nodesTree1to5)

        val nodesBst3LevelsFull = arrayListOf<Int>()
        bst3LevelsFull.bfs { nodesBst3LevelsFull += it.value }
        assertEquals(listOf(5, 3, 8, 1, 4, 6, 9), nodesBst3LevelsFull)

        val nodesBstWithNulls = arrayListOf<Int>()
        bstWithNulls.bfs { nodesBstWithNulls += it.value }
        assertEquals(listOf(7, 4, 9, 3, 8, 1), nodesBstWithNulls)
    }

    @Test
    fun depthAwareBFS() {
        val nodesWithDepth = ArrayList<Pair<TreeNode, Int>>()
        val tree = bst3LevelsFull
        bst3LevelsFull.depthAwareBFS { nodesWithDepth += it }
        assertEquals(
            listOf(
                tree to 0, tree.left to 1, tree.right to 1,
                tree.left?.left to 2, tree.left?.right to 2,
                tree.right?.left to 2, tree.right?.right to 2
            ), nodesWithDepth
        )
    }

    @Test
    fun levels() {
        assertEquals(listOf(listOf(1), listOf(2, 3)), tree123.levels())
        assertEquals(
            listOf(
                listOf(1),
                listOf(2, 3),
                listOf(4, 5)
            ),
            tree1to5.levels()
        )
        assertEquals(
            listOf(
                listOf(5),
                listOf(3, 8),
                listOf(1, 4, 6, 9)
            ),
            bst3LevelsFull.levels()
        )
    }

    @Test
    fun collect() {
        assertEquals(listOf(2, 1, 3), tree123.collect())
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bst3LevelsFull.collect())
    }

    @Test
    fun collectWithTransform() {
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9).map { it * 2 },
            bst3LevelsFull.collect { it.value * 2 })
    }

    @Test
    fun collectUnique() {
        assertEquals(setOf(2, 1, 3), tree123.collectUnique())
        assertEquals(setOf(1, 3, 4, 5, 6, 8, 9), bst3LevelsFull.collectUnique())
        assertEquals(setOf(1, 2, 3, 4, 5), datastructures.tree.binarytree.simple.buildTree(
            1,
            2,
            3,
            2,
            3,
            2,
            3,
            1,
            3,
            4,
            5
        ).collectUnique())
    }

    @Test
    fun rootToLeafPaths() {
        assertEquals(listOf(listOf(1, 2), listOf(1, 3)), tree123.rootToLeafPaths())
        assertEquals(
            listOf(listOf(5, 3, 1), listOf(5, 3, 4), listOf(5, 8, 6), listOf(5, 8, 9)),
            bst3LevelsFull.rootToLeafPaths()
        )
        assertEquals(listOf(listOf(7, 4, 3, 1), listOf(7, 9, 8)), bstWithNulls.rootToLeafPaths())
    }

    @Test
    fun allDownwardPaths() {
        val nullTree: TreeNode? = null
        assertEquals(emptyList<List<Int>>(), nullTree.allDownwardPaths())

        val expected123 = listOf(listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3))
        val actual123 = tree123.allDownwardPaths()
        assertEquals(expected123.size, actual123.size)
        assertEquals(expected123.toSet(), actual123.toSet())

        val expectedBST = listOf(
            listOf(5),
            listOf(3),
            listOf(5, 3),
            listOf(1),
            listOf(3, 1),
            listOf(5, 3, 1),
            listOf(4),
            listOf(3, 4),
            listOf(5, 3, 4),
            listOf(8),
            listOf(5, 8),
            listOf(6),
            listOf(8, 6),
            listOf(5, 8, 6),
            listOf(9),
            listOf(8, 9),
            listOf(5, 8, 9)
        )
        val actualBST = bst3LevelsFull.allDownwardPaths()
        assertEquals(expectedBST.size, actualBST.size)
        assertEquals(expectedBST.toSet(), actualBST.toSet())

        val expectedBSTWithNulls = listOf(
            listOf(7),
            listOf(4),
            listOf(7, 4),
            listOf(3),
            listOf(4, 3),
            listOf(7, 4, 3),
            listOf(1),
            listOf(3, 1),
            listOf(4, 3, 1),
            listOf(7, 4, 3, 1),
            listOf(9),
            listOf(7, 9),
            listOf(8),
            listOf(9, 8),
            listOf(7, 9, 8)
        )
        val actualBSTWithNulls = bstWithNulls.allDownwardPaths()
        assertEquals(expectedBSTWithNulls.size, actualBSTWithNulls.size)
        assertEquals(expectedBSTWithNulls.toSet(), actualBSTWithNulls.toSet())
    }

    @Test
    fun buildTree() {
        val tree = datastructures.tree.binarytree.simple.buildTree(1, 2, 3)
        assertNotNull(tree)
        assertEquals(3, tree?.size)
        assertEquals(1, tree?.value)
        assertEquals(2, tree?.left?.value)
        assertEquals(3, tree?.right?.value)

        val tree2 = datastructures.tree.binarytree.simple.buildTree(5, 3, 8, 1, 4, 6, 9)
        assertNotNull(tree2)
        assertEquals(7, tree2?.size)
        assertEquals(bst3LevelsFull, tree2)

        val tree3 = datastructures.tree.binarytree.simple.buildTree(1, null, 1, null, 1, 2)
        assertNotNull(tree3)
        assertEquals(4, tree3?.size)
        assertEquals(treeUnbalanced, tree3)

        val tree4 = datastructures.tree.binarytree.simple.buildTree(
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            null,
            1,
            2
        )
        assertEquals(12, tree4?.size)
        assertEquals(treeUnbalanced2, tree4)

        val tree5 = datastructures.tree.binarytree.simple.buildTree(7, null, 8, 9, 10)
        assertEquals(4, tree5?.size)
        val expected = TreeNode(7).apply {
            right = TreeNode(8).apply {
                left = TreeNode(9)
                right = TreeNode(10)
            }
        }
        assertEquals(expected, tree5)
    }

    @Test
    fun buildTreeWithNulls() {
        val tree = datastructures.tree.binarytree.simple.buildTree(1, null, 2, 3)
        assertNotNull(tree)
        assertEquals(3, tree?.size)
        assertEquals(1, tree?.value)
        assertNull(tree?.left?.value)
        assertEquals(2, tree?.right?.value)
        assertEquals(3, tree?.right?.left?.value)
        assertNull(tree?.right?.left?.right)

        val tree2 = datastructures.tree.binarytree.simple.buildTree(7, 4, 9, 3, null, 8, null, 1)
        assertEquals(bstWithNulls, tree2)
        assertEquals(6, tree2?.size)
        assertEquals(7, tree2?.value)
        assertEquals(4, tree2?.left?.value)
        assertEquals(9, tree2?.right?.value)
        assertEquals(3, tree2?.left?.left?.value)
        assertEquals(1, tree2?.left?.left?.left?.value)
        assertEquals(8, tree2?.right?.left?.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildTree throws exception if root is null`() {
        datastructures.tree.binarytree.simple.buildTree(null)
    }

    @Test
    fun buildBST() {
        val tree1 = datastructures.tree.binarytree.simple.buildBST(2, 1, 3)
        assertNotNull(tree1)
        assertEquals(3, tree1?.size)
        assertEquals(2, tree1?.value)
        assertEquals(1, tree1?.left?.value)
        assertEquals(3, tree1?.right?.value)

        val tree2 = datastructures.tree.binarytree.simple.buildBST(7, 4, 9, 3, null, 8, null, 1)
        assertEquals(bstWithNulls, tree2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildBST throws exception if root is null`() {
        datastructures.tree.binarytree.simple.buildBST(null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildBST throws exception if elements violate BST property`() {
        datastructures.tree.binarytree.simple.buildBST(1, 2, 3)
    }

    @Test
    fun toList() {
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bst3LevelsFull.toList())
    }

    @Test
    fun iterator() {
        val tree1to5Values = listOf(4, 2, 5, 1, 3)
        tree1to5.forEachIndexed { i, value ->
            assertEquals(value, tree1to5Values[i])
        }
    }
}
