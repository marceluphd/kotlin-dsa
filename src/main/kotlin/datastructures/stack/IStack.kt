package datastructures.stack

/**
 * Interface for a Stack ADT.
 */
interface IStack<T> : Iterable<T> {
    /** Return the size of the stack. */
    val size: Int

    /** Add an element to the top of the stack. */
    fun push(element: T)
    
    /** Remove an element from the top of the stack. */
    fun pop(): T
    
    /** Return the object at the top of this stack without removing it from the stack. */ 
    fun peek(): T
    
    /** Test if the stack is empty */
    fun isEmpty(): Boolean

    /** Returns `true` if the stack is not empty. */
    fun isNotEmpty(): Boolean
}
