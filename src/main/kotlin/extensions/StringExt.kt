package extensions

/**
 * String extensions.
 */

/** True if every character in the String is an uppercase letter. */
val String.isAllCaps: Boolean get() = all { it.isUpperCase() }

/** True if every character in the String is a lowercase letter. */
val String.isAllLowercase: Boolean get() = all { it.isLowerCase() }

/**
 * Returns a map of each character to its frequency.
 * If `caseSensitive`, all keys will be lowercase.
 */
fun String.characterFrequencies(caseSensitive: Boolean = true): Map<Char, Int> =
    groupingBy { if (caseSensitive) it.toLowerCase() else it }.eachCount()

/**
 * Returns a string with the chars sorted. Sorts by the Char's Int value.
 * If a string contains whitespace or punctuation characters, the sorted order
 * will be by increasing [ASCII value](https://www.rapidtables.com/code/text/ascii-table.html).
 */
fun String.toSortedString(): String = toCharArray().sorted().joinToString("")
