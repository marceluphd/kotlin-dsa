package algorithms.backtracking.combinations

import extensions.math.roundedToNDecimalPlaces
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

class CombinationsTest {

    @Test
    fun `combinations - from Power Set`() {
        val times = arrayListOf<Long>()
        repeat(15) {
            val ms = measureTimeMillis {
                assertEquals(
                    setOf(emptySet<Int>()),
                    CombinationsFromPowerSet.combinations(items = setOf(1, 2, 3), k = 0)
                )

                assertEquals(
                    setOf(setOf(1), setOf(2), setOf(3)),
                    CombinationsFromPowerSet.combinations(items = setOf(1, 2, 3), k = 1)
                )

                assertEquals(
                    setOf(setOf(1, 2), setOf(1, 3), setOf(2, 3)),
                    CombinationsFromPowerSet.combinations(items = setOf(1, 2, 3), k = 2)
                )

                assertEquals(
                    setOf(setOf(1, 2, 3)),
                    CombinationsFromPowerSet.combinations(items = setOf(1, 2, 3), k = 3)
                )

                assertEquals(
                    setOf(
                        setOf(1, 2, 3, 4),
                        setOf(1, 2, 3, 5),
                        setOf(1, 2, 3, 6),
                        setOf(1, 2, 4, 5),
                        setOf(1, 2, 4, 6),
                        setOf(1, 2, 5, 6),
                        setOf(1, 3, 4, 5),
                        setOf(1, 3, 4, 6),
                        setOf(1, 3, 5, 6),
                        setOf(1, 4, 5, 6),
                        setOf(2, 3, 4, 5),
                        setOf(2, 3, 4, 6),
                        setOf(2, 3, 5, 6),
                        setOf(2, 4, 5, 6),
                        setOf(3, 4, 5, 6)
                    ),
                    CombinationsFromPowerSet.combinations(items = setOf(1, 2, 3, 4, 5, 6), k = 4)
                )

                CombinationsFromPowerSet.combinations(items = setOf((1..100).toList().toSet()), k = 10)

            }
            times += ms
        }
        val adjustedTimes = times.drop(3)
        println("adjustedTimes for combinationsSet: $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }

    @Test
    fun `combinations - tail prepend`() {
        val times = arrayListOf<Long>()
        repeat(15) {
            val ms = measureTimeMillis {
                assertEquals(
                    listOf(emptyList<Int>()),
                    TailPrependCombinations.combinations(elements = setOf(1, 2, 3), k = 0)
                )

                assertEquals(
                    listOf(listOf(1), listOf(2), listOf(3)),
                    TailPrependCombinations.combinations(elements = setOf(1, 2, 3), k = 1)
                )

                assertEquals(
                    listOf(listOf(1, 2), listOf(1, 3), listOf(2, 3)),
                    TailPrependCombinations.combinations(elements = setOf(1, 2, 3), k = 2)
                )

                assertEquals(
                    listOf(listOf(1, 2, 3)),
                    TailPrependCombinations.combinations(elements = setOf(1, 2, 3), k = 3)
                )

                assertEquals(
                    listOf(
                        listOf(1, 2, 3, 4),
                        listOf(1, 2, 3, 5),
                        listOf(1, 2, 3, 6),
                        listOf(1, 2, 4, 5),
                        listOf(1, 2, 4, 6),
                        listOf(1, 2, 5, 6),
                        listOf(1, 3, 4, 5),
                        listOf(1, 3, 4, 6),
                        listOf(1, 3, 5, 6),
                        listOf(1, 4, 5, 6),
                        listOf(2, 3, 4, 5),
                        listOf(2, 3, 4, 6),
                        listOf(2, 3, 5, 6),
                        listOf(2, 4, 5, 6),
                        listOf(3, 4, 5, 6)
                    ),
                    TailPrependCombinations.combinations(elements = setOf(1, 2, 3, 4, 5, 6), k = 4)
                )

                TailPrependCombinations.combinations(elements = setOf((1..100).toList()), k = 10)

            }
            times += ms
        }
        val adjustedTimes = times.drop(3)
        println("adjustedTimes for combinationsSet: $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }
}