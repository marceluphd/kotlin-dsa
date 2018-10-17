package algorithms.backtracking.combinations

/**
 * This algorithm generates the power set and then filters `size == k` subsets.
 */
object CombinationsFromPowerSet {
    /**
     * Generates all combinations of size [k].
     */
    fun <T> combinations(items: Set<T>, k: Int): Set<Set<T>> = when {
        k < 0 -> throw Error("k cannot be smaller then 0, but was $k")
        k == 0 -> setOf(setOf())
        k >= items.size -> setOf(items)
        else -> powerSet(items)
            .asSequence()
            .filter { it.size == k }
            .toSet()
    }

    /**
     * Generates the power set of a Collection.
     */
    private fun <T> powerSet(items: Collection<T>): Set<Set<T>> {
        val powerSet: MutableSet<Set<T>> = hashSetOf(emptySet()) // contains only the null set {{}}
        for (x in items)
            powerSet += powerSet.map { it + x }
        return powerSet
    }
}

/**
 * This algorithm iterates the elements of the list, generates the combinations for the remaining elements
 * in the list, and then prepends the current element to the tail combinations to generate larger k combinations.
 */
object TailPrependCombinations {

    fun <T> combinations(elements: Set<T>, k: Int): List<List<T>> = when {
        k == 0 || k > elements.size -> listOf(emptyList())

        // k == n: n choose n
        k == elements.size -> listOf(elements.toList())

        // k = 1: Return single-element lists
        k == 1 -> elements.fold(arrayListOf()) { acc, num ->
            acc.apply { acc.add(listOf(num)) }
        }

        else -> elements.foldIndexed(mutableListOf()) { i, acc, element ->
            val tailCombinations = combinations(elements.drop(i + 1).toSet(), k - 1)
                .filter { it.isNotEmpty() }
            val newCombinations = tailCombinations.map { listOf(element) + it }
            acc.apply { acc.addAll(newCombinations) }
        }
    }
}