package algorithms.backtracking.powerset

object IterativeAppending {
    /**
     * Iterative appending algorithm.
     * Time: O(2^n)
     * Space: O(n)
     */
    fun <T> powerSet(elements: Collection<T>): Set<Set<T>> {
        val powerSet: MutableSet<Set<T>> = hashSetOf(emptySet()) // contains only the null set {{}}
        for (x in elements)
            powerSet += powerSet.map { it + x }
        return powerSet
    }
}

object RecursiveAppending1 {
    /**
     * Recursive version of [IterativeAppending].
     * Time: O(2^n)
     * Space: O(n)
     */
    fun powerSet(nums: List<Int>): Set<Set<Int>> = when {
        nums.isEmpty() -> setOf(emptySet())
        else -> {
            val prevPowerSets = powerSet(nums.slice(0 until nums.lastIndex))
            prevPowerSets + prevPowerSets.map { it + nums.last() }
        }
    }
}

object RecursiveAppending2 {
    /**
     * Another recursive version of [IterativeAppending].
     * Here, we map the first element onto tail power sets, instead of mapping
     * the last element onto previous power sets.
     * Time: O(2^n)
     * Space: O(n)
     */
    fun <T> powerSet(set: Set<T>): Set<Set<T>> = when {
        set.isEmpty() -> setOf(setOf())
        else -> {
            val tailPowerSet = powerSet(set.asSequence().drop(1).toSet())
            tailPowerSet + tailPowerSet.map { setOf(set.first()) + it }
        }
    }
}

object BacktrackingAlgorithm1 {
    /**
     * Backtracking
     *
     * Time: O(2^n)
     * Space: O(n)
     */
    fun powerSet(nums: Set<Int>): Set<Set<Int>> {
        val powerSet: MutableSet<Set<Int>> = hashSetOf()
        generateSubsets(nums = nums.toList(), subset = hashSetOf(), powerSet = powerSet)
        return powerSet
    }

    private fun generateSubsets(
        nums: List<Int>,
        subset: Set<Int> = hashSetOf(),
        powerSet: MutableSet<Set<Int>>
    ) {
        when {
            nums.isEmpty() -> powerSet += subset
            else -> {
                generateSubsets(nums.drop(1), subset + nums.first(), powerSet)      // include
                generateSubsets(nums.drop(1), subset, powerSet)                             // don't include
            }
        }
    }
}

object BacktrackingAlgorithm2 {
    /**
     * Backtracking
     *
     * Time: O(2^n)
     * Space: O(n)
     */
    fun powerSet(nums: Set<Int>): Set<Set<Int>> {
        val powerSet = mutableSetOf<Set<Int>>()
        backtrack(nums.toList(), 0, hashSetOf(), powerSet)
        return powerSet
    }

    private fun backtrack(
        nums: List<Int>,
        startIdx: Int,
        subset: Set<Int> = hashSetOf(),
        powerSet: MutableSet<Set<Int>>
    ) {
        powerSet.add(subset)

        for (i in (startIdx..nums.lastIndex)) {
            backtrack(nums, i + 1, subset + nums[i], powerSet)
        }
    }
}

object BacktrackingDFS {
    /**
     * Backtracking - DFS
     *
     * Time: O(2^n)
     * Space: O(n)
     */
    fun powerSet(nums: Set<Int>): Set<Set<Int>> {
        val powerSet = mutableSetOf<Set<Int>>()
        dfs(nums.toList(), 0, mutableListOf(), powerSet)
        return powerSet
    }

    private fun dfs(
        nums: List<Int>,
        index: Int,
        sublist: MutableList<Int>,
        powerSet: MutableSet<Set<Int>>
    ) {
        when (index) {
            nums.size -> {
                powerSet += sublist.toSet()
                return
            }
            else -> {
                /* Don't include the number */
                dfs(nums, index + 1, sublist, powerSet)

                /* Include the number, then backtrack */
                sublist.add(nums[index])
                dfs(nums, index + 1, sublist, powerSet)
                sublist.removeAt(sublist.lastIndex)
            }
        }
    }
}
