package datastructures.queue

/**
 * Queue implementation backed by an [ArrayList].
 */
class ListQueue<T>(private val elements: MutableList<T> = arrayListOf()) : Queue<T> {
    override val size: Int get() = elements.size

    override fun enqueue(value: T) = elements.add(elements.size, value)
    override fun dequeue(): T? = if (elements.isEmpty()) null else elements.removeAt(0)
    override fun peek(): T? = elements.firstOrNull()
    override fun isEmpty(): Boolean = elements.isEmpty()
    override fun isNotEmpty(): Boolean = !elements.isEmpty()
    override fun clear() = elements.clear()
    override fun toString(): String = "ListQueue(elements=$elements)"
    internal fun toList(): List<T> = elements.toList()
}