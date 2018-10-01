package algorithms.backtracking.subsetsum

import org.junit.Assert.*
import org.junit.Test

class KSumSubsetsTest {

    private val solution = Solution()
    private val list1to4 = listOf(1, 2, 3, 4)

    @Test
    fun testGetKSumSubsets() {
        assertEquals(listOf(emptyList<Int>()), solution.getKSumSubsets(list1to4, 0))
        assertEquals(listOf(listOf(1)), solution.getKSumSubsets(list1to4, 1))
        assertEquals(listOf(listOf(2)), solution.getKSumSubsets(list1to4, 2))
        assertEquals(listOf(listOf(1, 2), listOf(3)), solution.getKSumSubsets(list1to4, 3))
        assertEquals(listOf(listOf(1, 3), listOf(4)), solution.getKSumSubsets(list1to4, 4))
        assertEquals(listOf(listOf(1, 4), listOf(2, 3)), solution.getKSumSubsets(list1to4, 5))
        assertEquals(listOf(listOf(1, 2, 3), listOf(2, 4)), solution.getKSumSubsets(list1to4, 6))
        assertEquals(listOf(listOf(1, 2, 4), listOf(3, 4)), solution.getKSumSubsets(list1to4, 7))
        assertEquals(listOf(listOf(1, 3, 4)), solution.getKSumSubsets(list1to4, 8))
        assertEquals(listOf(listOf(2, 3, 4)), solution.getKSumSubsets(list1to4, 9))
        assertEquals(listOf(listOf(1, 2, 3, 4)), solution.getKSumSubsets(list1to4, 10))
    }
}