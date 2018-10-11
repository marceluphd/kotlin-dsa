package datastructures.linkedlist

/**
 * TODO: Make Generic
 * Definition for a singly-linked list (from LeetCode).
 */
class ListNode(var value: Int = 0) : Iterable<Int> {

    var next: ListNode? = null

    /**
     * Returns a new list which is a copy of the original list.
     * Changes to the copy will not affect the original list.
     *
     * **Time**: `O(n)`
     *
     * **Space**: `O(n)`
     */
    fun copyOf(): ListNode? {
        val sentinel = ListNode(Int.MIN_VALUE)
        var copy: ListNode? = sentinel
        var node: ListNode? = this
        while (node != null) {
            copy?.next = ListNode(node.value)
            node = node.next
            copy = copy?.next
        }
        return sentinel.next
    }

    /**
     * @return a [List] of the values of each node in the linked list.
     */
    fun toList(): List<Int> {
        val list = mutableListOf(this.value)
        var nextNode: ListNode? = this.next
        while (nextNode != null) {
            list.add(nextNode.value)
            nextNode = nextNode.next
        }

        return list.toList()
    }

    /**
     * @return a [Set] containing the (distinct) values in the linked list.
     */
    fun toSet(): Set<Int> {
        val set = hashSetOf(this.value)
        var nextNode: ListNode? = this.next
        while (nextNode != null) {
            if (!set.contains(nextNode.value)) {
                set.add(nextNode.value)
            }
            nextNode = nextNode.next
        }

        return set
    }

    /**
     * @return an [IntArray] containing the values in the linked list.
     */
    fun toIntArray(): IntArray = toList().toIntArray()


    override fun toString(): String = "($value)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListNode

        if (value != other.value) return false
        if (next != other.next) return false

        return true
    }

    override fun hashCode(): Int = value

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Int> = toList().iterator()

    companion object {
        /**
         * Create a linked list from the given [Collection].
         * @param elements The elements to add to the list.
         * @return The head of a linked list of the [elements], or null if [elements] is empty.
         */
        fun from(elements: Collection<Int>): ListNode? {
            if (elements.isEmpty())
                return null

            val sentinel = ListNode()
            var node: ListNode? = sentinel
            for (element in elements) {
                node?.next = ListNode(element)
                node = node?.next
            }

            return sentinel.next
        }
    }
}

/**
 * @return the number of elements in the linked list from this [ListNode].
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
val ListNode?.size: Int
    get() {
        var count = 0
        var node: ListNode? = this
        while (node != null) {
            count++
            node = node.next
        }

        return count
    }

/**
 * Return the middle node in the list, or null if the receiver is null.
 * When the size of the list is even, the "2nd middle" node is returned.
 *
 * ```
 * oddList: (1)->(2)->(3)
 * evenList: (1)->(2)->(3)->(4)
 *
 * oddList.middleNode   // (2)
 * evenList.middleNode  // (3)
 * ```
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
val ListNode?.middleNode: ListNode?
    get() {
        var tortoise: ListNode? = this
        var hare: ListNode? = this

        while (hare?.next != null) {
            tortoise = tortoise?.next
            hare = hare.next?.next
        }

        return tortoise
    }

/**
 * Return the list of all nodes except the first.
 */
val ListNode?.tail: ListNode? get() = this?.next

/**
 * Returns a new [ListNode] in reverse order.
 * Non-mutating.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun ListNode?.reversed(): ListNode? {
    this ?: return null

    val copyHead = this.copyOf()

    var prev: ListNode? = null
    var curr: ListNode? = copyHead
    var next: ListNode? = copyHead?.next

    while (curr != null) {
        curr.next = prev

        prev = curr
        curr = next
        next = next?.next
    }

    return prev
}

/**
 * The list contents as a [String]: (1)->(2)->(3)
 *
 * @return a [String] representation of the List.
 */
fun ListNode?.contentToString(): String {
    this ?: return "(null)"

    return buildString {
        var node: ListNode? = this@contentToString
        while (node != null) {
            append(node)
            node = node.next
        }
        toString()
    }.replace(")(", ")->(")
}

/**
 * Create a linked list from the given elements.
 * @param elements The elements to add to the list.
 * @return The head of a linked list of the [elements].
 * @throws IllegalArgumentException if [elements] is empty.
 */
fun linkedListOf(vararg elements: Int): ListNode {
    require(elements.isNotEmpty()) { "Cannot create ListNode without elements." }

    val sentinel = ListNode()
    var node = sentinel
    for (element in elements) {
        val newNode = ListNode(element)
        node.next = newNode
        node = newNode
    }

    return sentinel.next!!
}
