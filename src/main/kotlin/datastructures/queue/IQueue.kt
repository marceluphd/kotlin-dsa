package datastructures.queue

/**
 * Interface for a Queue ADT.
 */
interface IQueue<T> : Iterable<T> {
    /** Return the size of the queue. */
    val size: Int

    /** Add an element to the end of the queue. */
    fun enqueue(value: T)

    /** Remove the element to the front of the queue. */
    fun dequeue(): T?

    /** Get the element to the front of the queue, but do not remove it. */
    fun peek(): T?

    /** Test if the stack is empty */
    fun isEmpty(): Boolean

    /** Returns `true` if the stack is not empty. */
    fun isNotEmpty(): Boolean

    /** Removes all elements from this queue. */
    fun clear()
}
