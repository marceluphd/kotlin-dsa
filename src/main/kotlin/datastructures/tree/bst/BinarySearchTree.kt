package datastructures.tree.bst

import common.exceptions.ItemNotFoundException
import datastructures.tree.DFSTraversalOrder
import datastructures.tree.DFSTraversalOrder.INORDER
import datastructures.tree.DFSTraversalOrder.POSTORDER
import datastructures.tree.DFSTraversalOrder.PREORDER
import datastructures.tree.SearchTree
import java.util.*

/**
 * Binary Search Tree.
 * Duplicates are allowed and stored as described [here](https://www.geeksforgeeks.org/how-to-handle-duplicates-in-binary-search-tree/).
 */
class BinarySearchTree<T : Comparable<T>> : SearchTree<T>, Iterable<T> {

    internal var root: BinaryTreeNode<T>? = null
    private var count: Int = 0

    /** Return the size of the collection. */
    override val size: Int get() = count

    /**
     * The number of edges in the path from the root to the deepest leaf.
     * @return the height of the tree, or -1 if empty
     */
    val height: Int get() = if (root == null) -1 else heightOf(root)

    /**
     * Add the [element] to the data structure.
     *
     * **Time**: `O(n)` (average: `O(log n)`)
     *
     * **Space**: `O(n)`
     */
    override fun add(element: T) {
        insert(root, element)
    }

    /**
     * Adds all of the elements in the specified collection to the tree.
     *
     * **Time**: `O(n^2)` (average: `O(n log n)`)
     *
     * **Space**: `O(n)`
     */
    override fun addAll(elements: Collection<T>) {
        elements.forEach { e ->
            add(e)
        }
    }

    /**
     * Find an element in the tree if it exists.
     * @return The element, or null if the element was not in the tree.
     *
     * **Time**: `O(n)` (average: `O(log n)`)
     *
     * **Space**: `O(n)`
     */
    override fun search(element: T): T? = find(root, element)

    /**
     * Remove the specified element from the tree if it exists.
     * @return The removed element, or null if the element was not in the tree.
     * @throws ItemNotFoundException if the element is not in the tree
     *
     * **Time**: `O(n)` (average: `O(log n)`)
     *
     * **Space**: `O(n)`
     */
    override fun remove(element: T): T {
        // TODO - change to do find & delete at same time
        val nodeToDelete = find(root, element)
        nodeToDelete ?: throw ItemNotFoundException("Value $element was not in the tree.")

        root = delete(root, element)
        count--
        return element
    }

    /** Returns `true` if the collection is empty (contains no elements), `false` otherwise. */
    override fun isEmpty(): Boolean = count == 0

    /** Returns `true` if the collection is not empty. */
    override fun isNotEmpty(): Boolean = !isEmpty()

    /** Removes all elements from this tree */
    override fun clear() {
        root = null
        count = 0
    }

    operator fun contains(element: T): Boolean = find(root, element) != null

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> = toList().iterator()

    /**
     * Returns the smallest element in the tree, or `null` if the tree is empty.
     *
     * **Time**: `O(n)` (average: `O(log n)`)
     *
     * **Space**: `O(n)`
     */
    fun min(): T? = minNode(root)?.data

    /**
     * Returns the largest element in the tree, or `null` if the tree is empty.
     *
     * **Time**: `O(n)` (average: `O(log n)`)
     *
     * **Space**: `O(n)`
     */
    fun max(): T? = maxNode(root)?.data

    /**
     * Perform a depth-first traversal on the tree, executing [visit] on each node.
     *
     * @param node The node to start the traversal on (default: [root])
     * @param order The traversal order (preorder, inorder, or postorder)
     * @param visit Action to perform on each node's data
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    fun dfs(
        node: BinaryTreeNode<T>? = root,
        order: DFSTraversalOrder = INORDER,
        visit: (T) -> Unit
    ) {
        node ?: return

        when (order) {
            PREORDER -> {
                visit(node.data)
                dfs(node.left, PREORDER, visit)
                dfs(node.right, PREORDER, visit)
            }

            INORDER -> {
                dfs(node.left, INORDER, visit)
                visit(node.data)
                dfs(node.right, INORDER, visit)
            }

            POSTORDER -> {
                dfs(node.left, POSTORDER, visit)
                dfs(node.right, POSTORDER, visit)
                visit(node.data)
            }
        }
    }

    /**
     * Perform a breadth-first traversal on the tree, executing [visit] on each node.
     *
     * @param treeRoot The node to start the traversal on (default: [root])
     * @param visit Action to perform on each node's data
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    fun bfs(treeRoot: BinaryTreeNode<T>? = root, visit: (T) -> Unit) {
        treeRoot ?: return

        var current: BinaryTreeNode<T> = treeRoot
        val queue: Queue<BinaryTreeNode<T>> = ArrayDeque<BinaryTreeNode<T>>()
        queue.add(current)

        while (queue.isNotEmpty()) {
            current = queue.remove() ?: return
            visit(current.data)

            current.left?.let { queue.add(it) }
            current.right?.let { queue.add(it) }
        }
    }


    /**
     * Return a [String] representation of the elements in the tree.
     */
    fun contentToString(): String = collect().toString()

    /**
     * Traverse the tree (using inorder depth-first search), collecting the values
     * into a list.
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    fun collect(node: BinaryTreeNode<T>? = root, acc: MutableList<T> = arrayListOf()): List<T> {
        node ?: return acc

        collect(node.left, acc)
        repeat(node.count) {
            acc += node.data
        }
        collect(node.right, acc)
        return acc
    }

    /**
     * Traverses the tree with DFS (in the given [traversalOrder]),
     * applies the [transform] to each node's value, and
     * collect the transform result.
     *
     * **Time**: `O(n)` - assuming `transform` is `<= O(n)`
     *
     * **Space**: `O(n)`
     */
    fun collect(
        node: BinaryTreeNode<T>? = root,
        traversalOrder: DFSTraversalOrder = INORDER,
        acc: MutableList<T> = arrayListOf(),
        transform: (T) -> T
    ): List<T> {
        node ?: return acc

        dfs(node, traversalOrder) { acc += transform(it) }
        return acc
    }


    /**
     * Returns a 2D List where each sublist contains the depth=i nodes' data paired
     * with the element's frequency.
     */
    fun levels(node: BinaryTreeNode<T>? = root): List<List<Pair<T, Int>>> {
        node ?: return emptyList()

        val levels: MutableList<MutableList<Pair<T, Int>>> = arrayListOf()

        val queue: Queue<Pair<BinaryTreeNode<T>, Int>> = ArrayDeque<Pair<BinaryTreeNode<T>, Int>>()
        queue.add(node to 0)

        while (queue.isNotEmpty()) {
            val (currentNode, depth) = queue.remove()

            if (depth > levels.lastIndex) {
                levels.add(arrayListOf())
            }
            levels[depth].add(currentNode.data to currentNode.count)

            currentNode.left?.let { queue.add(it to depth + 1) }
            currentNode.right?.let { queue.add(it to depth + 1) }
        }

        return levels
    }

    /**
     * Returns a list containing the elements in sorted order.
     */
    fun toList(): List<T> = collect(node = root)

    /**
     * Returns a MutableList containing the elements in sorted order.
     */
    fun toMutableList(): List<T> = collect(node = root)

    /**
     * Returns a Set containing the elements in sorted order.
     */
    fun toSet(): Set<T> {
        val uniqueElements = HashSet<T>()
        dfs(node = root, order = INORDER) { uniqueElements += it }
        return uniqueElements
    }

    /**
     * Find the depth of a node containing the search value.
     * (Depth is defined as the number of edges on the path from the root node to the search node)
     * @param element The search key
     * @return the depth of the node containing the [element], or -1 if the element is not in the tree.
     */
    fun depth(element: T): Int = depth(root, element) ?: -1

    /**
     * The level of a node is defined by 1 + depth (the number of edges between the node and the root).
     * @param element The value of the node to find the level of.
     * @return The level of the node with the specified value, or -1 if not contained in the tree.
     */
    fun level(element: T): Int {
        val depth = depth(element)
        return if (depth == -1) -1 else depth + 1
    }

    /**
     * A Binary Tree is **full** if all nodes have either 0 or 2 children.
     * @return true if the binary search tree is full, false if the tree is not full.
     */
    fun isFull(node: BinaryTreeNode<T>? = root): Boolean = when {
        node == null -> true
        node.isLeaf -> true
        node.hasOneChild -> false
        else -> isFull(node.left) && isFull(node.right)
    }

    // TODO
    fun isBalanced(node: BinaryTreeNode<T>? = root): Boolean = TODO()
    fun isComplete(node: BinaryTreeNode<T>? = root): Boolean = TODO()
    fun isPerfect(node: BinaryTreeNode<T>? = root): Boolean = TODO()

    private fun depth(node: BinaryTreeNode<T>?, element: T, depth: Int = 0): Int? = when {
        node == null -> -1
        element < node.data -> depth(node.left, element, depth + 1)
        element > node.data -> depth(node.right, element, depth + 1)
        else -> depth
    }

    private fun minNode(node: BinaryTreeNode<T>?): BinaryTreeNode<T>? {
        node ?: return null
        return minNode(node.left) ?: node
    }

    private fun maxNode(node: BinaryTreeNode<T>?): BinaryTreeNode<T>? {
        node ?: return null
        return maxNode(node.right) ?: node
    }

    private fun find(node: BinaryTreeNode<T>?, element: T): T? {
        node ?: return null

        return when {
            element < node.data -> find(node.left, element)
            element > node.data -> find(node.right, element)
            else -> node.data
        }
    }

    /** Helper method for [add] */
    private fun insert(node: BinaryTreeNode<T>?, element: T): BinaryTreeNode<T> {
        when {
            node == null -> {
                /* Only reached if tree is empty */
                count++
                val newRoot = BinaryTreeNode(element)
                root = newRoot
                return newRoot
            }

            /* Duplicate added - increments the node's internal count when a duplicate element
               is inserted into the tree.
               Reference: https://www.geeksforgeeks.org/how-to-handle-duplicates-in-binary-search-tree */
            element == node.data -> {
                node.count++
                count++
            }

            element < node.data && node.hasLeft -> node.left = insert(node.left, element)
            element < node.data && !node.hasLeft -> {
                node.left = BinaryTreeNode(element)
                count++
            }

            element > node.data && node.hasRight -> node.right = insert(node.right, element)
            element > node.data && !node.hasRight -> {
                node.right = BinaryTreeNode(element)
                count++
            }
        }
        return node!!
    }

    /** Helper method for [remove] */
    private fun delete(node: BinaryTreeNode<T>?, element: T): BinaryTreeNode<T>? {
        node ?: return null

        var deletedNode = node
        when {
            element < node.data -> node.left = delete(node.left, element)
            element > node.data -> node.right = delete(node.right, element)

            node.count > 1 -> node.count--
            node.isLeaf -> deletedNode = null
            node.hasOneChild -> deletedNode = if (node.hasLeft) node.left else node.right
            node.hasTwoChildren -> deletedNode = deleteNodeWithTwoSubtrees(node)
        }

        return deletedNode
    }

    /** Helper method for [remove] */
    private fun deleteNodeWithTwoSubtrees(node: BinaryTreeNode<T>): BinaryTreeNode<T>? {
        // Find the smallest value in the right subtree
        val rightSubtreeMinNode = minNode(node.right)!!
        val rightMin = rightSubtreeMinNode.data
        val rightMinFrequency = rightSubtreeMinNode.count

        // Create a new node with the right-min value
        val replacementNode = BinaryTreeNode(rightMin)
        replacementNode.count = rightMinFrequency
        replacementNode.left = node.left
        replacementNode.right = node.right

        // Delete the right-max node since it is the replacement for the deleted node
        replacementNode.right = delete(replacementNode.right, rightMin)

        return replacementNode
    }

    private fun heightOf(node: BinaryTreeNode<T>?): Int =
        if (node == null || node.isLeaf) 0
        else 1 + maxOf(heightOf(node.left), heightOf(node.right))


    /* 
        TODO:
        - copyOf
        - isComplete
        - isPerfect
        - isBalanced
        - fromSorted(Array/List)
    */
}


/**
 * A node in a Binary Tree.
 */
class BinaryTreeNode<T : Comparable<T>>(
    val data: T,
    var left: BinaryTreeNode<T>? = null,
    var right: BinaryTreeNode<T>? = null
) {
    /**
     * The number of occurrences of the data. [BinarySearchTree] increments the count when
     * a duplicate element is inserted into the tree.
     * [Reference - GeeksForGeeks](https://www.geeksforgeeks.org/how-to-handle-duplicates-in-binary-search-tree/)
     */
    internal var count: Int = 1

    val hasLeft: Boolean get() = left != null
    val hasRight: Boolean get() = right != null
    val isLeaf: Boolean get() = !hasLeft && !hasRight
    val hasOneChild: Boolean get() = hasLeft xor hasRight
    val hasTwoChildren: Boolean get() = hasLeft && hasRight

    /** Only compares the [data] & [count], not children. */
    override fun equals(other: Any?): Boolean {
        other ?: return false
        other as BinaryTreeNode<*>
        return (data == other.data) && (count == other.count)
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        result = 31 * result + count
        return result
    }

    override fun toString(): String = "(data=$data, count=$count)"
}