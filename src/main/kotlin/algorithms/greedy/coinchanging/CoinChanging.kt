package algorithms.greedy.coinchanging

// TODO - TEST
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