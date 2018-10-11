package datastructures.exceptions

/**
 * Exception class for failed finds/removes in data structures.
 */
class ItemNotFoundException(message: String = ""): RuntimeException(message)