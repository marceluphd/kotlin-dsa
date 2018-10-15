package datastructures.queue.arrayqueue

import datastructures.queue.IQueue

/**
 * Queue implementation backed by an [ArrayList].
 */
class ArrayQueue<T> : IQueue<T> {
    private val elements: MutableList<T> = arrayListOf()

    /** Return the size of the queue. */
    override val size: Int get() = elements.size

    /** Add an element to the end of the queue. */
    override fun enqueue(value: T) = elements.add(elements.size, value)

    /** Remove the element to the front of the queue. */
    override fun dequeue(): T? = if (elements.isEmpty()) null else elements.removeAt(0)

    /** Get the element to the front of the queue, but do not remove it. */
    override fun peek(): T? = elements.firstOrNull()

    /** Test if the stack is empty */
    override fun isEmpty(): Boolean = elements.isEmpty()

    /** Returns `true` if the stack is not empty. */
    override fun isNotEmpty(): Boolean = elements.isNotEmpty()

    /** Removes all elements from this queue. */
    override fun clear() = elements.clear()

    override fun toString(): String = "ArrayQueue(elements=$elements)"

    /** Returns an iterator over the elements of this object. */
    override fun iterator(): Iterator<T> = elements.iterator()
}
