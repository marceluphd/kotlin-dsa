package algorithms.backtracking.powerset

import org.junit.Assert.assertEquals
import org.junit.Test

class PowerSetTest {

    @Test
    fun `powerSet - Iterative Appending Algorithm`() {
        assertEquals(setOf(emptySet<Int>()), IterativeAppending.powerSet(emptySet<Int>()))
        assertEquals(setOf(setOf(), setOf(1)), IterativeAppending.powerSet(setOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), IterativeAppending.powerSet(setOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            IterativeAppending.powerSet(setOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            IterativeAppending.powerSet(listOf(1, 2, 3, 4))
        )
    }

    @Test
    fun `powerSet - Recursive Appending Algorithm 1`() {
        assertEquals(setOf(emptySet<Int>()), RecursiveAppending1.powerSet(emptyList()))
        assertEquals(setOf(setOf(), setOf(1)), RecursiveAppending1.powerSet(listOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), RecursiveAppending1.powerSet(listOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            RecursiveAppending1.powerSet(listOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            RecursiveAppending1.powerSet(listOf(1, 2, 3, 4))
        )
    }

    @Test
    fun `powerSet - Recursive Appending Algorithm 2`() {
        assertEquals(setOf(emptySet<Int>()), RecursiveAppending2.powerSet(emptySet<Int>()))
        assertEquals(setOf(setOf(), setOf(1)), RecursiveAppending2.powerSet(setOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), RecursiveAppending2.powerSet(setOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            RecursiveAppending2.powerSet(setOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            RecursiveAppending2.powerSet(setOf(1, 2, 3, 4))
        )
    }

    @Test
    fun `powerSet - Backtracking 1`() {
        assertEquals(setOf(emptySet<Int>()), BacktrackingAlgorithm1.powerSet(emptySet()))
        assertEquals(setOf(setOf(), setOf(1)), BacktrackingAlgorithm1.powerSet(setOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), BacktrackingAlgorithm1.powerSet(setOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            BacktrackingAlgorithm1.powerSet(setOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            BacktrackingAlgorithm1.powerSet(setOf(1, 2, 3, 4))
        )
    }

    @Test
    fun `powerSet - Backtracking 2`() {
        assertEquals(setOf(emptySet<Int>()), BacktrackingAlgorithm2.powerSet(emptySet()))
        assertEquals(setOf(setOf(), setOf(1)), BacktrackingAlgorithm2.powerSet(setOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), BacktrackingAlgorithm2.powerSet(setOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            BacktrackingAlgorithm2.powerSet(setOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            BacktrackingAlgorithm2.powerSet(setOf(1, 2, 3, 4))
        )
    }

    @Test
    fun `powerSet - Backtracking DFS`() {
        assertEquals(setOf(emptySet<Int>()), BacktrackingDFS.powerSet(emptySet()))
        assertEquals(setOf(setOf(), setOf(1)), BacktrackingDFS.powerSet(setOf(1)))
        assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), BacktrackingDFS.powerSet(setOf(1, 2)))
        assertEquals(
            setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
            BacktrackingDFS.powerSet(setOf(1, 2, 3))
        )
        assertEquals(
            setOf(
                setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                setOf(1, 2, 3, 4)
            ),
            BacktrackingDFS.powerSet(setOf(1, 2, 3, 4))
        )
    }
}
