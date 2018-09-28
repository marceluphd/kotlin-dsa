package extensions.debug

/**
 * Used to indent current recursive frame to show recursion tree.
 */
fun indent(depth: Int): String = "    ".repeat(depth)

/**
 * Print message with 4 spaces for each depth
 */
fun printlnWithIndent(depth: Int, message: Any?) {
    println("${indent(depth)}$message")
}