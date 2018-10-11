package algorithms.greedy.coinchanging

/**
 * Greedy algorithm which solves the [Coin-Changing Problem](https://en.wikipedia.org/wiki/Change-making_problem#Greedy_method)
 * for certain coin denominations (like US coins).
 * The greedy approach not work for all coin denominations (use dynamic programming).
 */
fun changeCoins(amountDue: Int, coins: IntArray): List<Int>? {
    var changeNeeded = amountDue
    require(coins.isNotEmpty()) { "Invalid input" }
    coins.sortDescending()
    val change = arrayListOf<Int>()

    while (changeNeeded > 0) {
        val largestCoin = coins.firstOrNull { it <= changeNeeded } ?: return null // no solution
        changeNeeded -= largestCoin
        change += largestCoin
    }

    return change
}
