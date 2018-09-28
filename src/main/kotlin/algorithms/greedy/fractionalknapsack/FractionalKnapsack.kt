package algorithms.greedy.fractionalknapsack

import kotlin.math.min

/**
 * Reference: Algorithm Design & Applications
 * Ch. 10
 *
 * @param items The set of items that can be selected.
 * @param maxWeight The maximum weight that the knapsack can hold.
 */
fun fractionalKnapsack(items: Set<Item>, maxWeight: Double): List<Pair<Double, Item>> {
    // The amount to take of each item
    val amountsToTake: MutableList<Pair<Double, Item>> = arrayListOf()
    var currentWeight = 0.0
    val sortedItems: List<Item> = items.sortedByDescending(Item::valueByWeight)

    if (sortedItems.isEmpty()) return amountsToTake

    var i = 0
    while (i < sortedItems.size && currentWeight < maxWeight) {
        /* Remove item with highest value-by-weight */
        val maxValueItem = sortedItems[i]

        /* Greedy choice: Take as much of the most valuable (by weight) item -
           Take all of it if it will fit, otherwise take as much as you can without exceeding maxWeight. */
        val weightToTake = min(maxValueItem.weight, maxWeight - currentWeight)
        currentWeight += weightToTake

        /* Add to solution set */
        amountsToTake.add(weightToTake to maxValueItem)

        i++
    }

    return amountsToTake
}


/**
 * A simple item with name (optional), a weight, and a value
 * @param name The name of the item
 * @param weight The weight of the item
 * @param value The value (benefit) of the item
 */
data class Item(val name: String = "Unnamed Item", val weight: Double, val value: Double) {
    init {
        require(weight > 0.0 && value > 0.0) { "Weight and value must be greater than 0" }
    }

    /**
     * Returns the value (benefit) by weight - i.e., the value density
     */
    val valueByWeight: Double get() = value / weight

    override fun toString(): String = "($name: weight=$weight, value=$value, valueByWeight=$valueByWeight)"
}