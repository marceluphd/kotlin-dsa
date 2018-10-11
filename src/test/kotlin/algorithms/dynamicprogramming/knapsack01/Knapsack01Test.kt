package algorithms.dynamicprogramming.knapsack01

import org.junit.Assert.*
import org.junit.Test

class Knapsack01Test {

    // Items from Grokking Algorithms
    private val items1 = listOf(
        Item(name = "Guitar", weight = 1, value = 1500),
        Item(name = "Stereo", weight = 4, value = 3000),
        Item(name = "Laptop", weight = 3, value = 2000)
    )

    private val items2 = listOf(
        Item(name = "Guitar", weight = 1, value = 1500),
        Item(name = "Stereo", weight = 4, value = 3000),
        Item(name = "Laptop", weight = 3, value = 2000),
        Item(name = "iPhone", weight = 1, value = 2000)
    )

    private val items3 = listOf(
        Item(name = "Guitar", weight = 1, value = 1500),
        Item(name = "Stereo", weight = 4, value = 3000),
        Item(name = "Laptop", weight = 3, value = 2000),
        Item(name = "iPhone", weight = 1, value = 2000),
        Item(name = "iPod", weight = 1, value = 1000)
    )

    // From Rosetta Code: https://rosettacode.org/wiki/Knapsack_problem/0-1#Kotlin
    private val items4 = listOf(
        Item("map", 9, 150),
        Item("compass", 13, 35),
        Item("water", 153, 200),
        Item("sandwich", 50, 160),
        Item("glucose", 15, 60),
        Item("tin", 68, 45),
        Item("banana", 27, 60),
        Item("apple", 39, 40),
        Item("cheese", 23, 30),
        Item("beer", 52, 10),
        Item("suntan cream", 11, 70),
        Item("camera", 32, 30),
        Item("T-shirt", 24, 15),
        Item("trousers", 48, 10),
        Item("umbrella", 73, 40),
        Item("waterproof trousers", 42, 70),
        Item("waterproof overclothes", 43, 75),
        Item("note-case", 22, 80),
        Item("sunglasses", 7, 20),
        Item("towel", 18, 12),
        Item("socks", 4, 50),
        Item("book", 30, 10)
    )


    @Test
    fun `knapsack01 - items1`() {
        assertEquals(
            Knapsack(
                setOf(
                    Item(name = "Laptop", weight = 3, value = 2000),
                    Item(name = "Guitar", weight = 1, value = 1500)
                )),
            knapsack(items1, 4)
        )
    }

    @Test
    fun `knapsack01 - items2`() {
        assertEquals(
            Knapsack(
                setOf(
                    Item(name = "iPhone", weight = 1, value = 2000),
                    Item(name = "Laptop", weight = 3, value = 2000)
                )),
            knapsack(items2, 4)
        )
    }

    @Test
    fun `knapsack01 - items3`() {
        assertEquals(
            Knapsack(
                setOf(
                    Item(name = "iPhone", weight = 1, value = 2000),
                    Item(name = "Guitar", weight = 1, value = 1500),
                    Item(name = "iPod", weight = 1, value = 1000)
                )),
            knapsack(items3, 4)
        )
    }

    @Test
    fun `knapsack04 - items4`() {
        val solution = knapsack(items4, 400)
        assertEquals(
                setOf(
                    Item("water", 153, 200),
                    Item("sandwich", 50, 160),
                    Item("map", 9, 150),
                    Item("note-case", 22, 80),
                    Item("waterproof overclothes", 43, 75),
                    Item("suntan cream", 11, 70),
                    Item("waterproof trousers", 42, 70),
                    Item("glucose", 15, 60),
                    Item("banana", 27, 60),
                    Item("socks", 4, 50),
                    Item("compass", 13, 35),
                    Item("sunglasses", 7, 20)
                ),
                solution.items
        )
        assertEquals(396, solution.totalWeight)
        assertEquals(1030, solution.totalValue)
    }

}