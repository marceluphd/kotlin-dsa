package datastructures.tree

import org.junit.Assert.*
import org.junit.Test

class TreeNodeTest {

    @Test
    fun dfs() {
        val values = arrayListOf<Int>()
        tree123.dfs { values += it.`val` }
        assertEquals(arrayListOf(2, 1, 3), values)

        val bstValues = arrayListOf<Int>()
        bst3LevelsFull.dfs { bstValues += it.`val` }
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bstValues.toList())
    }

    @Test
    fun bfs() {
        val nodesTree123 = arrayListOf<Int>()
        tree123.bfs { nodesTree123 += it.`val` }
        assertEquals(listOf(1, 2, 3), nodesTree123)

        val nodesTree1to5 = arrayListOf<Int>()
        tree1to5.bfs { nodesTree1to5 += it.`val` }
        assertEquals(listOf(1, 2, 3, 4, 5), nodesTree1to5)

        val nodesBst3LevelsFull = arrayListOf<Int>()
        bst3LevelsFull.bfs { nodesBst3LevelsFull += it.`val` }
        assertEquals(listOf(5, 3, 8, 1, 4, 6, 9), nodesBst3LevelsFull)

        val nodesBstWithNulls = arrayListOf<Int>()
        bstWithNulls.bfs { nodesBstWithNulls += it.`val` }
        assertEquals(listOf(7, 4, 9, 3, 8, 1), nodesBstWithNulls)
    }

    @Test
    fun toList() {
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bst3LevelsFull.toList())
    }

    @Test
    fun collect() {
        assertEquals(arrayListOf(2, 1, 3), tree123.collect())
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9), bst3LevelsFull.collect())
    }


    @Test
    fun collectWithTransform() {
        assertEquals(listOf(1, 3, 4, 5, 6, 8, 9).map { it * 2 },
            bst3LevelsFull.collect { it.`val` * 2 })
    }

    @Test
    fun buildTree() {
        val tree = buildTree(1, 2, 3)
        assertNotNull(tree)
        assertEquals(3, tree?.size)
        assertEquals(1, tree?.`val`)
        assertEquals(2, tree?.left?.`val`)
        assertEquals(3, tree?.right?.`val`)

        val tree2 = buildTree(5, 3, 8, 1, 4, 6, 9)
        assertNotNull(tree2)
        assertEquals(7, tree2?.size)
        assertEquals(bst3LevelsFull, tree2)

        val tree3 = buildTree(1, null, 1, null, 1, 2)
        assertNotNull(tree3)
        assertEquals(4, tree3?.size)
        assertEquals(treeUnbalanced, tree3)

        val tree4 = buildTree(1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, 2)
        assertEquals(12, tree4?.size)
        assertEquals(treeUnbalanced2, tree4)
    }

    @Test
    fun buildTreeWithNulls() {
        val tree = buildTree(1, null, 2, 3)
        assertNotNull(tree)
        assertEquals(3, tree?.size)
        assertEquals(1, tree?.`val`)
        assertNull(tree?.left?.`val`)
        assertEquals(2, tree?.right?.`val`)
        assertEquals(3, tree?.right?.left?.`val`)
        assertNull(tree?.right?.left?.right)

        val tree2 = buildTree(7, 4, 9, 3, null, 8, null, 1)
        assertEquals(bstWithNulls, tree2)
        assertEquals(6, tree2?.size)
        assertEquals(7, tree2?.`val`)
        assertEquals(4, tree2?.left?.`val`)
        assertEquals(9, tree2?.right?.`val`)
        assertEquals(3, tree2?.left?.left?.`val`)
        assertEquals(1, tree2?.left?.left?.left?.`val`)
        assertEquals(8, tree2?.right?.left?.`val`)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildTree throws exception if root is null`() {
        buildTree(null)
    }

    @Test
    fun buildBST() {
        val tree1 = buildBST(2, 1, 3)
        assertNotNull(tree1)
        assertEquals(3, tree1?.size)
        assertEquals(2, tree1?.`val`)
        assertEquals(1, tree1?.left?.`val`)
        assertEquals(3, tree1?.right?.`val`)

        val tree2 = buildBST(7, 4, 9, 3, null, 8, null, 1)
        assertEquals(bstWithNulls, tree2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildBST throws exception if root is null`() {
        buildBST(null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildBST throws exception if elements violate BST property`() {
        buildBST(1, 2, 3)
    }

    @Test
    fun buildTreeFromString() {
        val tree = buildTreeFromString("[1, 2, 3]")
        assertNotNull(tree)
        assertEquals(3, tree?.size)
        assertEquals(1, tree?.`val`)
        assertEquals(2, tree?.left?.`val`)
        assertEquals(3, tree?.right?.`val`)

        val tree2 = buildTreeFromString("[5, 3, 8, 1, 4, 6, 9]")
        assertNotNull(tree2)
        assertEquals(7, tree2?.size)
        assertEquals(bst3LevelsFull, tree2)

        assertEquals(
            buildTree(1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, 2),
            buildTreeFromString("[1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]")
        )

        assertEquals(
            buildTree(99, 77, 123),
            buildTreeFromString("[99, 77, 123]")
        )

        assertEquals(
            buildTree(4, -7, -3, null, null, -9, -3, 9, -7, -4, null, 6, null, -6, -6, null, null, 0, 6, 5, null, 9, null, null, -1, -4, null, null, null, -2),
            buildTreeFromString("[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]")
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `buildTreeFromString throws exception when input invalid`() {
        buildTreeFromString("1, 2, 3")
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
    fun allPaths() {
        val nullTree: TreeNode? = null
        assertEquals(emptyList<List<Int>>(), nullTree.allPaths())

        assertEquals(listOf(listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3)), tree123.allPaths())

        assertEquals(
            listOf(
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
            ), bst3LevelsFull.allPaths()
        )

        assertEquals(
            listOf(
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
            ), bstWithNulls.allPaths()
        )
    }

    @Test
    fun find() {
        (1..9).forEach { i ->
            if (i in setOf(2, 7))
                assertNull(bst3LevelsFull.find(i))
            else
                assertEquals(i, bst3LevelsFull.find(i)?.`val`)
        }

        (1..9).forEach { i ->
            if (i in setOf(2, 5, 6))
                assertNull(bstWithNulls.find(i))
            else
                assertEquals(i, bstWithNulls.find(i)?.`val`)
        }
    }

    @Test
    fun contains() {
        (1..9).forEach { i ->
            if (i in setOf(2, 7))
                assertFalse(bst3LevelsFull.contains(i))
            else
                assertTrue(bst3LevelsFull.contains(i))
        }

        (1..9).forEach { i ->
            if (i in setOf(2, 5, 6))
                assertFalse(bstWithNulls.contains(i))
            else
                assertTrue(bstWithNulls.contains(i))
        }
    }

    @Test
    fun children() {
        val (one, four) = bst3LevelsFull.left!!.children
        assertEquals(1, one?.`val`)
        assertEquals(4, four?.`val`)
    }

    @Test
    fun height() {
        assertEquals(1, tree123.height)
        assertEquals(2, tree1to5.height)
        assertEquals(2, bst3LevelsFull.height)
        assertEquals(3, bstWithNulls.height)
    }
}
