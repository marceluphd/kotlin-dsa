package extensions.strings

/**
 * String extensions.
 */

/** True if every character in the String is an uppercase letter. */
val String.isAllCaps: Boolean get() = all { it.isUpperCase() }

/** True if every character in the String is a lowercase letter. */
val String.isAllLowercase: Boolean get() = all { it.isLowerCase() }

/**
 * Return a String with the elements in the specified range reversed.
 * @throws StringIndexOutOfBoundsException if [indexRange] not in `0..lastIndex`
 */
fun String.reverseCharsInRange(indexRange: IntRange): String =
    replaceRange(indexRange, substring(indexRange).reversed())

/**
 * Return a string with the Chars at the given indices swapped.
 */
fun String.swappingCharsAt(i: Int, j: Int): String =
    if (i == j) this
    else substring(0, i) + this[j] + substring(i + 1, j) + this[i] + substring(j + 1)

/**
 * Returns a map of each character to its frequency.
 * If `caseSensitive`, all keys will be lowercase.
 */
fun String.characterFrequencies(caseSensitive: Boolean = true): Map<Char, Int> =
    groupingBy { if (caseSensitive) it.toLowerCase() else it }.eachCount()

/**
 * Return a map where the entries are (Char -> List of indices containing Char)
 */
fun String.characterIndices(): Map<Char, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })


/**
 * Return a list of the distinct chars in the string (retaining order).
 */
fun String.distinctChars(): List<Char> = toCharArray().distinct()

/**
 * Returns a string with the chars sorted. Sorts by the Char's Int value.
 * If a string contains whitespace or punctuation characters, the sorted order
 * will be by increasing [ASCII value](https://www.rapidtables.com/code/text/ascii-table.html).
 */
fun String.toSortedString(): String = toCharArray().sorted().joinToString("")

