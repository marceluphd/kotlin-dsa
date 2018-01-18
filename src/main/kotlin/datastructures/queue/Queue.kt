package datastructures.queue

/**
 * Queue interface
 */
interface Queue<T> {
    fun enqueue(value: T)
    fun dequeue(): T?
    fun peek(): T?
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean
    fun clear()
    val size: Int
}