package algorithms.dynamicprogramming.knapsack01

import kotlin.math.max


/**
 * A collection of selected items.
 */
data class Knapsack(val items: Set<Item>) {
    // Calculate the total value of the knapsack
    val totalValue by lazy {
        items.sumBy { it.value }
    }

    internal val totalWeight by lazy {
        items.sumBy { it.weight }
    }
}

/**
 * Solves the 0-1 Knapsack problem.
 * @param items The items that can be selected
 * @param maxWeight The max. weight that the knapsack can hold
 * @param printGrid boolean flag for debugging/educational purposes
 * @return a [Knapsack] containing the items that should be taken to maximize total value.
 */
fun knapsack(items: List<Item>, maxWeight: Int, printGrid: Boolean = false): Knapsack {
    // Each cell in the grid represents the optimal solution choosing from
    // items[0..i]. The cell value is a pair of the set of items selected
    // and the total value of the selected items.
    val grid: Array<Array<Knapsack>> = Array(items.size) {
        Array(maxWeight + 1) { Knapsack(setOf()) }
    }

    // fill in first row (avoid grid[i - 1][j] index out-of-bounds)
    for (j in 1..maxWeight) {
        grid[0][j] = if (items[0].weight <= j) Knapsack(setOf(items[0]))
                     else Knapsack(emptySet())
    }

    // i: selecting from items[0..i]
    for (i in 1..items.lastIndex) {

        // j: sub-knapsack weight
        // j = 0 -> knapsack
        for (j in 1..maxWeight) {

            val itemWeight = items[i].weight
            val prevItemsMaxForWeight = grid[i - 1][j].totalValue // max total value selecting from items[0..i - 1]

            val valueWithCurrentItem = 
                if (itemWeight > j) 0 // item won't fit
                else items[i].value + grid[i - 1][j - itemWeight].totalValue    // current item value + value of remaining space

            val maxValue = max(prevItemsMaxForWeight, valueWithCurrentItem)

            val selectedItems =
                if (maxValue == prevItemsMaxForWeight) grid[i - 1][j].items
                else grid[i - 1][j - itemWeight].items + items[i]

            grid[i][j] = Knapsack(selectedItems)
        }

    }

    if (printGrid)
        printGrid(grid, maxWeight)

    return grid[items.lastIndex][maxWeight]
}

private fun printGrid(grid: Array<Array<Knapsack>>, maxWeight: Int) {
    (0 until grid.size).forEach { r ->
        (0..maxWeight).forEach { c ->
            val itemsInKnapsack = grid[r][c].items.asSequence().map { it.name }.joinToString(", ")
            println("| grid[$r][$c]: ${itemsInKnapsack.padEnd(30, ' ')} (totalValue = ${grid[r][c].totalValue.toString().padStart(4, ' ')})|")
        }
        println()
    }
}

/**
 * A simple item with name (optional), a weight, and a value
 * @param name The name of the item
 * @param weight The weight of the item
 * @param value The value (benefit) of the item
 */
data class Item(val name: String = "Unnamed Item", val weight: Int, val value: Int) {
    init {
        require(weight > 0 && value > 0) { "Weight and value must be greater than 0" }
    }

    override fun toString(): String = "($name: weight=$weight, value=$value)"
}