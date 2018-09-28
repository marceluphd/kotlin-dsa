package algorithms.greedy.fractionalknapsack

import extensions.math.formatToNDecimalPlaces
import org.junit.Assert.*
import org.junit.Test

class FractionalKnapsackTest {

    private val items1 = setOf(
        Item(name = "A", weight = 12.0, value =  4.0),
        Item(name = "B", weight =  1.0, value =  2.0),
        Item(name = "C", weight =  2.0, value =  2.0),
        Item(name = "D", weight =  1.0, value =  1.0),
        Item(name = "E", weight =  4.0, value = 10.0)
    )

    private val items2 = setOf(
        Item(name = "Clock",    weight = 10.0,  value = 175.0),
        Item(name = "Painting", weight =  9.0,  value =  90.0),
        Item(name = "Radio",    weight =  4.0,  value =  20.0),
        Item(name = "Vase",     weight =  2.0,  value =  50.0),
        Item(name = "Book",     weight =  1.0,  value =  10.0),
        Item(name = "Computer", weight = 20.0,  value =  200.0)
    )

    // Fig. 10.2 in Algorithm Design & Application (Kleinberg)
    private val items3 = setOf(
        Item(name = "1", weight = 4.0, value = 12.0),
        Item(name = "2", weight = 8.0, value = 32.0),
        Item(name = "3", weight = 2.0, value = 40.0),
        Item(name = "4", weight = 6.0, value = 30.0),
        Item(name = "5", weight = 1.0, value = 50.0)
    )

    @Test
    fun testFractionalKnapsack1() {
        val solution = fractionalKnapsack(items1, 15.0)
        val weightsTaken = solution.map { (amount, _) -> amount }
        printSolution(items1, 15.0, solution)
        assertEquals(listOf(4.0, 1.0, 2.0, 1.0, 7.0), weightsTaken)
    }

    @Test
    fun testFractionalKnapsack2() {
        val solution = fractionalKnapsack(items2, 20.0)
        val weightsTaken = solution.map { (amount, _) -> amount }
        printSolution(items2, 20.0, solution)
        assertEquals(listOf(2.0, 10.0, 8.0), weightsTaken)
    }

    @Test
    fun testFractionalKnapsack3() {
        val solution = fractionalKnapsack(items3, 10.0)
        val weightsTaken = solution.map { (amount, _) -> amount }
        printSolution(items3, 10.0, solution)
        assertEquals(listOf(1.0, 2.0, 6.0, 1.0), weightsTaken)
    }

    private fun printSolution(items: Set<Item>, maxWeight: Double, solution: List<Pair<Double, Item>>) {
        println("\nFractional Knapsack:")
        println("Items: $items")
        println("Max. Weight: $maxWeight")

        var cumulativeValue = 0.0
        var cumulativeWeight = 0.0
        solution.forEachIndexed { i, (amount, item) ->
            println("i = $i:")
            println("${amount.formatToNDecimalPlaces(2)} taken of ${item.name}")
            println("Value Added: ${(amount * item.valueByWeight).formatToNDecimalPlaces(2)}")
            cumulativeWeight += amount
            println("Cumulative Weight: ${cumulativeWeight.formatToNDecimalPlaces(2)}")
            cumulativeValue += (amount * item.valueByWeight)
            println("Cumulative Value: ${cumulativeValue.formatToNDecimalPlaces(2)}\n")
        }
    }
}