package datastructures.stack

interface Stack<T> {
    fun push(element: T)
    fun pop(): T
    fun peek(): T
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean
    val size: Int
}
