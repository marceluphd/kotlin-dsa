package algorithms.backtracking.permutations

/**
 * Generates all permutations of the input list.
 * If the list contains duplicates, all *unique* permutations are generated.
 */
fun <T> List<T>.permutations(): Set<List<T>> = when (size) {
    0 -> setOf(emptyList())
    1 -> setOf(listOf(first()))
    2 -> setOf(this, listOf(this[1], this[0]))
    else -> {
        val permutations: MutableSet<List<T>> = hashSetOf()
        for (i in indices) {
            val otherElements = filterIndexed { index, _ -> index != i }
            val newPermutations = otherElements.permutations().map {
                it + this[i]
            }
            permutations.addAll(newPermutations)
        }
        permutations
    }
}