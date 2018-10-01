package algorithms.dynamicprogramming.rodcutting

import org.junit.Assert.*
import org.junit.Test

class CutRodTest {

    /**
     * Price table from the CLRS Algorithms book.
     */
    private val pricesCLRS = intArrayOf(0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30)

    @Test
    fun `cutRod - Naive`() {
        assertEquals(0, NaiveSolution.cutRod(pricesCLRS, 0))
        assertEquals(1, NaiveSolution.cutRod(pricesCLRS, 1))
        assertEquals(5, NaiveSolution.cutRod(pricesCLRS, 2))
        assertEquals(8, NaiveSolution.cutRod(pricesCLRS, 3))
        assertEquals(10, NaiveSolution.cutRod(pricesCLRS, 4))
        assertEquals(13, NaiveSolution.cutRod(pricesCLRS, 5))
        assertEquals(17, NaiveSolution.cutRod(pricesCLRS, 6))
        assertEquals(18, NaiveSolution.cutRod(pricesCLRS, 7))
        assertEquals(22, NaiveSolution.cutRod(pricesCLRS, 8))
        assertEquals(25, NaiveSolution.cutRod(pricesCLRS, 9))
        assertEquals(30, NaiveSolution.cutRod(pricesCLRS, 10))
    }

    @Test
    fun `cutRod - Memoized`() {
        assertEquals(0, MemoizedSolution.cutRod(pricesCLRS, 0))
        assertEquals(1, MemoizedSolution.cutRod(pricesCLRS, 1))
        assertEquals(5, MemoizedSolution.cutRod(pricesCLRS, 2))
        assertEquals(8, MemoizedSolution.cutRod(pricesCLRS, 3))
        assertEquals(10, MemoizedSolution.cutRod(pricesCLRS, 4))
        assertEquals(13, MemoizedSolution.cutRod(pricesCLRS, 5))
        assertEquals(17, MemoizedSolution.cutRod(pricesCLRS, 6))
        assertEquals(18, MemoizedSolution.cutRod(pricesCLRS, 7))
        assertEquals(22, MemoizedSolution.cutRod(pricesCLRS, 8))
        assertEquals(25, MemoizedSolution.cutRod(pricesCLRS, 9))
        assertEquals(30, MemoizedSolution.cutRod(pricesCLRS, 10))
    }

    @Test
    fun `cutRod - Tabulation`() {
        assertEquals(0, TabulationSolution.cutRod(pricesCLRS, 0))
        assertEquals(1, TabulationSolution.cutRod(pricesCLRS, 1))
        assertEquals(5, TabulationSolution.cutRod(pricesCLRS, 2))
        assertEquals(8, TabulationSolution.cutRod(pricesCLRS, 3))
        assertEquals(10, TabulationSolution.cutRod(pricesCLRS, 4))
        assertEquals(13, TabulationSolution.cutRod(pricesCLRS, 5))
        assertEquals(17, TabulationSolution.cutRod(pricesCLRS, 6))
        assertEquals(18, TabulationSolution.cutRod(pricesCLRS, 7))
        assertEquals(22, TabulationSolution.cutRod(pricesCLRS, 8))
        assertEquals(25, TabulationSolution.cutRod(pricesCLRS, 9))
        assertEquals(30, TabulationSolution.cutRod(pricesCLRS, 10))
    }
}