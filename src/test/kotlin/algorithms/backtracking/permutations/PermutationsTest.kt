package algorithms.backtracking.permutations

import org.junit.Assert.assertEquals
import org.junit.Test

class PermutationsTest {

    @Test
    fun permutations() {
        assertEquals(
            setOf(emptyList<Int>()),
            emptyList<Int>().permutations()
        )

        assertEquals(
            setOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1)
            ),
            listOf(1, 2, 3).permutations()
        )

        assertEquals(
            setOf(
                listOf(1, 1, 2),
                listOf(1, 2, 1),
                listOf(2, 1, 1)
            ),
            listOf(1, 1, 2).permutations()
        )

        assertEquals(
            setOf(
                listOf(0, 1, 2, 3, 4), listOf(0, 1, 2, 4, 3), listOf(0, 1, 3, 2, 4), listOf(0, 1, 3, 4, 2),
                listOf(0, 1, 4, 2, 3), listOf(0, 1, 4, 3, 2), listOf(0, 2, 1, 3, 4), listOf(0, 2, 1, 4, 3),
                listOf(0, 2, 3, 1, 4), listOf(0, 2, 3, 4, 1), listOf(0, 2, 4, 1, 3), listOf(0, 2, 4, 3, 1),
                listOf(0, 3, 1, 2, 4), listOf(0, 3, 1, 4, 2), listOf(0, 3, 2, 1, 4), listOf(0, 3, 2, 4, 1),
                listOf(0, 3, 4, 1, 2), listOf(0, 3, 4, 2, 1), listOf(0, 4, 1, 2, 3), listOf(0, 4, 1, 3, 2),
                listOf(0, 4, 2, 1, 3), listOf(0, 4, 2, 3, 1), listOf(0, 4, 3, 1, 2), listOf(0, 4, 3, 2, 1),
                listOf(1, 0, 2, 3, 4), listOf(1, 0, 2, 4, 3), listOf(1, 0, 3, 2, 4), listOf(1, 0, 3, 4, 2),
                listOf(1, 0, 4, 2, 3), listOf(1, 0, 4, 3, 2), listOf(1, 2, 0, 3, 4), listOf(1, 2, 0, 4, 3),
                listOf(1, 2, 3, 0, 4), listOf(1, 2, 3, 4, 0), listOf(1, 2, 4, 0, 3), listOf(1, 2, 4, 3, 0),
                listOf(1, 3, 0, 2, 4), listOf(1, 3, 0, 4, 2), listOf(1, 3, 2, 0, 4), listOf(1, 3, 2, 4, 0),
                listOf(1, 3, 4, 0, 2), listOf(1, 3, 4, 2, 0), listOf(1, 4, 0, 2, 3), listOf(1, 4, 0, 3, 2),
                listOf(1, 4, 2, 0, 3), listOf(1, 4, 2, 3, 0), listOf(1, 4, 3, 0, 2), listOf(1, 4, 3, 2, 0),
                listOf(2, 0, 1, 3, 4), listOf(2, 0, 1, 4, 3), listOf(2, 0, 3, 1, 4), listOf(2, 0, 3, 4, 1),
                listOf(2, 0, 4, 1, 3), listOf(2, 0, 4, 3, 1), listOf(2, 1, 0, 3, 4), listOf(2, 1, 0, 4, 3),
                listOf(2, 1, 3, 0, 4), listOf(2, 1, 3, 4, 0), listOf(2, 1, 4, 0, 3), listOf(2, 1, 4, 3, 0),
                listOf(2, 3, 0, 1, 4), listOf(2, 3, 0, 4, 1), listOf(2, 3, 1, 0, 4), listOf(2, 3, 1, 4, 0),
                listOf(2, 3, 4, 0, 1), listOf(2, 3, 4, 1, 0), listOf(2, 4, 0, 1, 3), listOf(2, 4, 0, 3, 1),
                listOf(2, 4, 1, 0, 3), listOf(2, 4, 1, 3, 0), listOf(2, 4, 3, 0, 1), listOf(2, 4, 3, 1, 0),
                listOf(3, 0, 1, 2, 4), listOf(3, 0, 1, 4, 2), listOf(3, 0, 2, 1, 4), listOf(3, 0, 2, 4, 1),
                listOf(3, 0, 4, 1, 2), listOf(3, 0, 4, 2, 1), listOf(3, 1, 0, 2, 4), listOf(3, 1, 0, 4, 2),
                listOf(3, 1, 2, 0, 4), listOf(3, 1, 2, 4, 0), listOf(3, 1, 4, 0, 2), listOf(3, 1, 4, 2, 0),
                listOf(3, 2, 0, 1, 4), listOf(3, 2, 0, 4, 1), listOf(3, 2, 1, 0, 4), listOf(3, 2, 1, 4, 0),
                listOf(3, 2, 4, 0, 1), listOf(3, 2, 4, 1, 0), listOf(3, 4, 0, 1, 2), listOf(3, 4, 0, 2, 1),
                listOf(3, 4, 1, 0, 2), listOf(3, 4, 1, 2, 0), listOf(3, 4, 2, 0, 1), listOf(3, 4, 2, 1, 0),
                listOf(4, 0, 1, 2, 3), listOf(4, 0, 1, 3, 2), listOf(4, 0, 2, 1, 3), listOf(4, 0, 2, 3, 1),
                listOf(4, 0, 3, 1, 2), listOf(4, 0, 3, 2, 1), listOf(4, 1, 0, 2, 3), listOf(4, 1, 0, 3, 2),
                listOf(4, 1, 2, 0, 3), listOf(4, 1, 2, 3, 0), listOf(4, 1, 3, 0, 2), listOf(4, 1, 3, 2, 0),
                listOf(4, 2, 0, 1, 3), listOf(4, 2, 0, 3, 1), listOf(4, 2, 1, 0, 3), listOf(4, 2, 1, 3, 0),
                listOf(4, 2, 3, 0, 1), listOf(4, 2, 3, 1, 0), listOf(4, 3, 0, 1, 2), listOf(4, 3, 0, 2, 1),
                listOf(4, 3, 1, 0, 2), listOf(4, 3, 1, 2, 0), listOf(4, 3, 2, 0, 1), listOf(4, 3, 2, 1, 0)
            ),
            listOf(0, 1, 2, 3, 4).permutations()
        )

        listOf(0, 1, 2, 3, 4, 5, 6, 7).permutations()
    }
}
