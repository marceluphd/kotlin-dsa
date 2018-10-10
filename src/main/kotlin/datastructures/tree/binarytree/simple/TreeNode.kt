package datastructures.tree.binarytree.simple

import datastructures.tree.binarytree.simple.DFSTraversalOrder.INORDER
import datastructures.tree.binarytree.simple.DFSTraversalOrder.POSTORDER
import java.util.*

/**
 * TODO: Make Generic
 * Definition for a binary tree node (based on LeetCode definition with some changes).
 */
class TreeNode(var value: Int = 0, var left: TreeNode? = null, var right: TreeNode? = null) {

    val hasLeft: Boolean get() = left != null
    val hasRight: Boolean get() = right != null
    val isLeaf: Boolean get() = !hasLeft && !hasRight
    val hasSingleChild: Boolean get() = (hasLeft || hasRight) && !(hasLeft && hasRight)
    val hasTwoChildren: Boolean get() = left != null && right != null



    /**
     * Returns the number of nodes in the tree.
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)` (recursive stack space)
     */
    val size: Int get() = 1 + (left?.size ?: 0) + (right?.size ?: 0)

    /**
     * Height: the number of edges on the longest path between this node and a leaf.
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    val height: Int
        get() {
            if (isLeaf) return 0
            return 1 + maxOf(left?.height ?: 0, right?.height ?: 0)
        }


    /**
     * Returns the left and right children of this node as a [Pair] of nullable TreeNodes.
     * ```
     * val (l, r) = tree?.children
     * ```
     *
     * **Time**: `O(1)`
     *
     * **Space**: `O(1)`
     */
    val children: Pair<TreeNode?, TreeNode?> get() = Pair(left, right)

    /**
     * Checks whether the specified value is contained in the tree.
     *
     * Usage:
     * ```
     * val tree: TreeNode = TreeNode(1)
     * tree.left = TreeNode(2)
     * tree.right = TreeNode(3)
     * 2 in tree
     * ```
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    operator fun contains(value: Int): Boolean = find(value) != null


    override fun toString(): String = "($value)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TreeNode

        if (value != other.value) return false
        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }
}

/**
 * Search for a value in the binary tree, returning the node
 * containing the value if it is found, or else null.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.find(searchValue: Int): TreeNode? {
    this ?: return null

    return when {
        searchValue < value -> left?.find(searchValue)
        searchValue > value -> right?.find(searchValue)
        else -> this
    }
}


sealed class DFSTraversalOrder {
    object PREORDER : DFSTraversalOrder()
    object INORDER : DFSTraversalOrder()
    object POSTORDER : DFSTraversalOrder()
}

/**
 * Perform depth-first traversal on the tree, executing [visit] on each node.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.dfs(order: DFSTraversalOrder = DFSTraversalOrder.INORDER, visit: (TreeNode) -> Unit) {
    this ?: return

    when (order) {
        DFSTraversalOrder.PREORDER -> {
            visit(this)
            left?.dfs(DFSTraversalOrder.PREORDER, visit)
            right?.dfs(DFSTraversalOrder.PREORDER, visit)
        }

        DFSTraversalOrder.INORDER -> {
            left?.dfs(DFSTraversalOrder.INORDER, visit)
            visit(this)
            right?.dfs(DFSTraversalOrder.INORDER, visit)
        }

        DFSTraversalOrder.POSTORDER -> {
            left?.dfs(DFSTraversalOrder.POSTORDER, visit)
            right?.dfs(POSTORDER, visit)
            visit(this)
        }
    }
}

/**
 * Traverse the tree (using inorder depth-first search), collecting the values
 * into a [Collection].
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.collect(acc: MutableList<Int> = arrayListOf()): List<Int> =
    this?.let {
        left?.collect(acc)
        acc += value
        right?.collect(acc)
    } ?: acc

/**
 * Traverses the tree with DFS (in the given [traversalOrder]),
 * applies the [transform] to each [TreeNode], and
 * collect the transform result.
 *
 * **Time**: `O(n)` - assuming `transform` is `<= O(n)`
 *
 * **Space**: `O(n)`
 */
fun <T> TreeNode?.collect(
    traversalOrder: DFSTraversalOrder = INORDER,
    acc: MutableList<T> = arrayListOf(),
    transform: (TreeNode) -> T
): List<T> {
    this ?: return emptyList()
    dfs(order = traversalOrder) { acc += transform(it) }
    return acc
}

/**
 * Collect the unique values to a Set.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.collectUnique(acc: MutableSet<Int> = hashSetOf()): Set<Int> =
    this?.let {
        left?.collectUnique(acc)
        acc += value
        right?.collectUnique(acc)
    } ?: acc

/**
 * Execute a breadth-first traversal of the tree.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.bfs(visit: (TreeNode) -> Unit) {
    this ?: return

    val queue: Queue<TreeNode> = ArrayDeque<TreeNode>()
    var node: TreeNode = this
    queue.add(node)
    while (queue.isNotEmpty()) {
        node = queue.remove() ?: return
        visit(node)
        node.left?.let { queue.add(it) }
        node.right?.let { queue.add(it) }
    }
}

/**
 * TODO - test
 * Execute a depth-aware breadth-first traversal of the tree.
 *
 * Example usage:
 *
 * ```
 * var treeLevels = hashMapOf<Int, List<Int>>()     // nodes values by depth (Map of Depth => Values)
 * binarySearchTree.depthAwareBFS() { (node, depth) ->
 *     treeLevels[depth] = (treeLevels[depth] ?: emptyList()) + node.value
 * }
 * println(treeLevels)
 * ```
 */
fun TreeNode?.depthAwareBFS(visit: (Pair<TreeNode, Int>) -> Unit) {
    this ?: return

    val queue: Queue<Pair<TreeNode, Int>> = ArrayDeque<Pair<TreeNode, Int>>()
    queue.add(Pair(this, 0))

    while (queue.isNotEmpty()) {
        val (node, depth) = queue.remove()!!
        visit(node to depth)
        node.left?.let {
            queue.add(Pair(it, depth + 1))
        }
        node.right?.let {
            queue.add(Pair(it, depth + 1))
        }
    }
}

/**
 * Get the values in the tree by depth.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.levels(): List<List<Int>> {
    this ?: return emptyList()

    val queue: Queue<Pair<TreeNode, Int>> = ArrayDeque<Pair<TreeNode, Int>>()
    queue.add(this to 0)

    val levels = arrayListOf<MutableList<Int>>()

    while (queue.isNotEmpty()) {
        val (node, depth) = queue.poll()

        if (depth > levels.lastIndex) {
            levels.add(arrayListOf())
        }

        levels[depth].add(node.value)

        node.left?.let { queue.add(it to depth + 1) }
        node.right?.let { queue.add(it to depth + 1) }
    }

    return levels
}

/**
 * Validates that the tree has the properties of a Binary Search Tree.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.isBST(validRange: IntRange = (Int.MIN_VALUE..Int.MAX_VALUE)): Boolean {
    this ?: return true

    val leftSubtreeRange = validRange.first..(value - 1)
    val rightSubtreeRange = (value + 1)..validRange.last

    return value in validRange && left.isBST(leftSubtreeRange) && right.isBST(rightSubtreeRange)
}

/**
 * Returns a list of all root-to-leaf paths.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.rootToLeafPaths(
    currentPath: List<Int> = emptyList(),
    paths: MutableList<List<Int>> = mutableListOf()
): List<List<Int>> {
    this ?: return paths

    left?.rootToLeafPaths(currentPath = currentPath + value, paths = paths)
    right?.rootToLeafPaths(currentPath = currentPath + value, paths = paths)

    if (isLeaf) {
        paths += (currentPath + value)
    }
    return paths
}

/**
 * Returns a list of all (downward) allPaths. A path is defined as any sequence of nodes (length >= 1).
 * Sequences where a descendant precedes an ancestor are not permitted.
 *
 * @param pathsToParent List containing all paths that end with the parent of the current node.
 * @param allPaths List containing all downward paths.
 *
 * **Time**: `O(?)`
 *
 * **Space**: `O(?)`
 */
fun TreeNode?.allDownwardPaths(
    pathsToParent: List<List<Int>> = emptyList(),
    allPaths: MutableList<List<Int>> = mutableListOf()
): List<List<Int>> {
    this ?: return allPaths

    val pathsToRoot = pathsToParent.map { it + value } + listOf(listOf(value))
    allPaths += pathsToRoot

    left?.allDownwardPaths(pathsToParent = pathsToRoot, allPaths = allPaths)
    right?.allDownwardPaths(pathsToParent = pathsToRoot, allPaths = allPaths)

    return allPaths
}

/**
 * Return a list of the values in the tree. For binary search trees, the values
 * will be sorted.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun TreeNode?.toList(): List<Int> = collect { it.value }.toList()

/**
 * Create a binary tree from the given elements.
 * Insertion order is the same as LeetCode's 'Tree Visualizer'
 * @param elements The values to add to the tree.
 * @return The root of a binary tree containing the [elements]
 * @throws IllegalArgumentException If [elements] is empty
 */
fun buildTree(vararg elements: Int?): TreeNode? {
    require(elements.isNotEmpty()) { "Cannot build empty tree" }

    require(elements[0] != null) { "Root cannot be null" }

    val root = TreeNode(elements.first()!!)
    var parent = root
    val parentQueue: Queue<TreeNode> = ArrayDeque<TreeNode>()
    parentQueue.add(parent)
    var i = 1
    while (i < elements.size) {
        parent = parentQueue.remove() ?: return root

        parent.left = if (elements[i] != null) TreeNode(elements[i]!!) else null
        i++
        parent.left?.let {
            parentQueue.add(it)
        }

        if (i > elements.lastIndex)
            return root

        parent.right = if (elements[i] != null) TreeNode(elements[i]!!) else null
        i++
        parent.right?.let {
            parentQueue.add(it)
        }
    }

    return root
}

/**
 * Create a binary search tree from the given elements.
 * Insertion order is the same as LeetCode's 'Tree Visualizer'.
 * For the BST property to be satisfied, all left subtree values must be less than the root,
 * and all right subtree values must be greater. Duplicates are not permitted.
 * @param elements The values to add to the tree.
 * @return The root of a BST containing the [elements], or `null` if
 *         elements is empty.
 * @throws IllegalArgumentException if the elements cannot create a valid binary search tree.
 */
fun buildBST(vararg elements: Int?): TreeNode? {
    val tree = buildTree(*elements)
    require(tree.isBST()) { "The elements in the order provided violate the BST property." }
    return tree
}
