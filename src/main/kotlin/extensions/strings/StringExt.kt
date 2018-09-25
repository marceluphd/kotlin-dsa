package extensions.strings

/**
 * String extensions.
 */

/** True if every character in the String is an uppercase letter. */
val String.isAllCaps: Boolean get() = all { it.isUpperCase() }

/** True if every character in the String is a lowercase letter. */
val String.isAllLowercase: Boolean get() = all { it.isLowerCase() }

val String.head: Char? get() = firstOrNull()
val String.tail: String get() = drop(1)
val String.headAndTail: Pair<Char?, String> get() = (head to tail)

/**
 * Returns a String containing all characters except the first element.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun String.dropFirst(): String = drop(1)

/**
 * Returns a String containing all characters except the last element.
 *
 * **Time**: `O(n)`
 *
 * **Space**: `O(n)`
 */
fun String.dropLast(): String = dropLast(1)

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
 * Return a [List] of the distinct chars in the string (retaining order).
 */
fun String.distinctChars(): List<Char> = toCharArray().distinct()

/**
 * Returns a [String] with the chars sorted. Sorts by the Char's Int value (ASCII value).
 * If a string contains whitespace or punctuation characters, the sorted order
 * will be by increasing [ASCII value](https://www.rapidtables.com/code/text/ascii-table.html).
 */
fun String.toSortedString(): String = toCharArray().sorted().joinToString("")

/**
 * Returns true if the [String] is an anagram of [other] (i.e., a permutation of the letters in this
 * String could be rearranged to form the [other] string).
 */
fun String.isAnagramOf(other: String): Boolean = toCharArray().sorted() == other.toCharArray().sorted()

/**
 * Returns all (contiguous) substrings of length `0..n`.
 * May contain duplicates.
 *
 * **Time**: `O(n^2)`
 *
 * **Space**: `O(n)`
 */
fun String.substrings(): List<String> {
    val substrings = arrayListOf("")
    for (i in indices) {
        for (j in (i..lastIndex)) {
            substrings += substring(i..j)
        }
    }
    return substrings
}
