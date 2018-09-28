package algorithms.greedy.coinchanging

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CoinChangingTest {

    private val usCoins = intArrayOf(1, 5, 10, 25, 50, 100)

    @Test
    fun testChangeCoins() {
        assertEquals(listOf(25, 5, 1, 1, 1, 1), changeCoins(34, usCoins))
        assertEquals(listOf(100, 100, 50, 25, 10, 1, 1, 1, 1), changeCoins(289, usCoins))
    }
}