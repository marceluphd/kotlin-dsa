package datastructures.stack.arraystack

import datastructures.stack.IStack
import java.util.*

/**
 * Stack implemented with an ArrayList.
 */
class ArrayStack<T> : IStack<T> {

    private val elements = arrayListOf<T>()
    
    /** Return the size of the stack. */
    override val size: Int get() = elements.size

    /** Add an element to the top of the stack. */
    override fun push(element: T) {
        elements += element
    }

    /** 
     * Remove an element from the top of the stack. 
     * @throws EmptyStackException if the stack is empty. 
     */
    override fun pop(): T =
        if (elements.isNotEmpty())
            elements.removeAt(elements.lastIndex)
        else throw EmptyStackException()

    /** 
     * Return the object at the top of this stack without removing it from the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    override fun peek(): T =
        if (elements.isNotEmpty())
            elements[elements.lastIndex]
        else throw EmptyStackException()

    /** Test if the stack is empty */
    override fun isEmpty(): Boolean = elements.isEmpty()

    /** Returns `true` if the stack is not empty. */
    override fun isNotEmpty(): Boolean = elements.isNotEmpty()

    override fun toString(): String = "ArrayStack(elements=$elements)"

    /** Returns an iterator over the elements of this object. */
    override fun iterator(): Iterator<T> = elements.iterator()
}
