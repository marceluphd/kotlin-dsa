package extensions.math

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class MathExtTest {

    @Ignore
    @Test
    fun powerSet() {
    }

    @Test
    fun combinations() {
        val ms = measureTimeMillis {
            assertEquals(
                listOf(emptyList<Int>()),
                listOf(1, 2, 3).combinations(0)
            )

            assertEquals(
                listOf(listOf(1), listOf(2), listOf(3)),
                listOf(1, 2, 3).combinations(1)
            )

            assertEquals(
                listOf(listOf(1, 2), listOf(1, 3), listOf(2, 3)),
                listOf(1, 2, 3).combinations(2)
            )

            assertEquals(
                listOf(listOf(1, 2, 3)),
                listOf(1, 2, 3).combinations()
            )

            assertEquals(
                listOf(
                    listOf(0, 1, 2, 3),
                    listOf(0, 1, 2, 4),
                    listOf(0, 1, 2, 5),
                    listOf(0, 1, 3, 4),
                    listOf(0, 1, 3, 5),
                    listOf(0, 1, 4, 5),
                    listOf(0, 2, 3, 4),
                    listOf(0, 2, 3, 5),
                    listOf(0, 2, 4, 5),
                    listOf(0, 3, 4, 5),
                    listOf(1, 2, 3, 4),
                    listOf(1, 2, 3, 5),
                    listOf(1, 2, 4, 5),
                    listOf(1, 3, 4, 5),
                    listOf(2, 3, 4, 5)
                ),
                listOf(0, 1, 2, 3, 4, 5).combinations(k = 4)
            )

        }
        println("$ms ms")
    }

    @Test
    fun permutations() {
        val ms = measureTimeMillis {
            assertEquals(
                listOf(
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
                listOf(
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
        println("$ms ms")
    }

    @Ignore
    @Test
    fun `permutations of size k`() {
        assertEquals(
            listOf(listOf<Int>()),
            listOf(1, 2).permutations(k = 0)
        )

        assertEquals(
            listOf(listOf(1), listOf(2)),
            listOf(1, 2).permutations(k = 1)
        )

        assertEquals(
            listOf(listOf(1, 2), listOf(1, 3), listOf(2, 1), listOf(2, 3), listOf(3, 1), listOf(3, 2)),
            listOf(1, 2, 3).permutations(k = 2)
        )

        assertEquals(
            listOf(listOf(1, 2), listOf(2, 1)),
            listOf(1, 2).permutations()
        )
    }

    @Test
    fun choose() {
        assertEquals(BigInteger.ONE, choose(0, 0))

        (0..10).forEach { i ->
            assertEquals(BigInteger.ONE, choose(i, 0))       // C(n, 0)
            assertEquals(BigInteger.ONE, choose(i, i))       // C(n, n)
        }

        assertEquals(BigInteger.valueOf(7L), choose(7, 1))   // C(n, 1)
        assertEquals(BigInteger.valueOf(7L), choose(7, 6))   // C(n, n-1)
        assertEquals(BigInteger.valueOf(21L), choose(7, 2))
        assertEquals(BigInteger.valueOf(21L), choose(7, 5))
        assertEquals(BigInteger.valueOf(35L), choose(7, 3))
        assertEquals(BigInteger.valueOf(35L), choose(7, 4))

        assertEquals(BigInteger.valueOf(10272278170L), choose(50, 10))
        assertEquals(BigInteger.valueOf(10272278170L), choose(50, 40))
        assertEquals(BigInteger.valueOf(121548660036300L), choose(50, 24))
        assertEquals(BigInteger.valueOf(126410606437752L), choose(50, 25))
        assertEquals(BigInteger.valueOf(121548660036300L), choose(50, 26))

        assertEquals(BigInteger.valueOf(75287520L), choose(100, 5))
        assertEquals(BigInteger("100891344545564193334812497256"), choose(100, 50))
    }
}