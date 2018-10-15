package datastructures.tree


/**
 * Interface for a searchable tree data structure.
 */
interface SearchTree<T : Comparable<T>> {
    /** Return the size of the collection. */
    val size: Int

    /** Add the [element] to the data structure. */
    fun add(element: T)

    /** Adds all of the elements in the specified collection to the tree. */
    fun addAll(elements: Collection<T>)

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