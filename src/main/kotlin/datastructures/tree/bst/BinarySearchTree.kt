package datastructures.tree.bst

class BinarySearchTree<T : Comparable<T>> : SearchTree<T> {

    internal var root: BinaryTreeNode<T>? = null
    private var count: Int = 0

    /** Return the size of the collection. */
    override val size: Int get() = count

    /** Add the [element] to the data structure. */
    override fun add(element: T) {
        add(root, element)
    }

    private fun add(node: BinaryTreeNode<T>?, element: T) {
        when {
            node == null -> {
                /* Only reached if tree is empty */
                root = BinaryTreeNode(element)
                count++
            }
            /* Duplicate added - increments the node's internal count when a duplicate element
               is inserted into the tree.
               Reference: https://www.geeksforgeeks.org/how-to-handle-duplicates-in-binary-search-tree */
            element == node.data -> {
                node.count++
                count++
            }

            element < node.data && node.hasLeft -> add(node.left, element)
            element < node.data && !node.hasLeft -> {
                node.left = BinaryTreeNode(element)
                count++
            }

            element > node.data && node.hasRight -> add(node.right, element)
            element > node.data && !node.hasRight -> {
                node.right = BinaryTreeNode(element)
                count++
            }
        }
    }

//     TODO - return Boolean (like List)?
    fun addAll(elements: Collection<T>) {
        elements.forEach { e ->
            add(e)
        }
    }

    /**
     * Remove the specified element from the tree if it exists.
     * @return The removed element, or null if the element was not in the tree.
     */
    override fun remove(element: T): T? {
        // count--
        TODO()
    }

    /**
     * Find an element in the tree if it exists.
     * @return The element, or null if the element was not in the tree.
     */
    override fun search(element: T): T? = find(root, element)

    private fun find(node: BinaryTreeNode<T>?, element: T): T? {
        node ?: return null

        return when {
            element < node.data -> find(node.left, element)
            element > node.data -> find(node.right, element)
            else -> node.data
        }

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

    fun toList(): List<T> = TODO()
    fun toMutableList(): MutableList<T> = TODO()

    // TODO - DFS
    // TODO - BFS
    // TODO - depthAwareBFS
    // TODO - copyOf
    // TODO - forEach
    // TODO - firstOrNull
    // TODO - height
    // TODO - min
    // TODO - max
    // TODO - depth(key)
    // TODO - level(key)
    // TODO - isFull
    // TODO - isPerfect
    // TODO - isBalanced
    // TODO - isComplete
    // TODO - fromSorted(Array/List)
    // TODO - toList()
    // TODO - toMutableList()
    // TODO - toSet()
}


internal class BinaryTreeNode<T : Comparable<T>>(
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
    val hasSingleChild: Boolean get() = hasLeft xor hasRight
    val hasTwoChildren: Boolean get() = hasLeft && hasRight
}

/**
 * Interface for a searchable tree data structure.
 */
interface SearchTree<T : Comparable<T>> {
    /** Return the size of the collection. */
    val size: Int

    /** Add the [element] to the data structure. */
    fun add(element: T)

    /**
     * Remove the specified element from the tree if it exists.
     * @return The removed element, or null if the element was not in the tree.
     */
    fun remove(element: T): T?

    /**
     * Find an element in the tree if it exists.
     * @return The element, or null if the element was not in the tree.
     */
    fun search(element: T): T?

    /** Returns `true` if the collection is empty (contains no elements), `false` otherwise. */
    fun isEmpty(): Boolean

    /** Returns `true` if the collection is not empty. */
    fun isNotEmpty(): Boolean

    /** Removes all elements from this tree */
    fun clear()
}