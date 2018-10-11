package algorithms.greedy.coinchanging

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

internal class CoinChangingTest {

    /** The Greedy algorithm finds the optimal solution for US coin denominations. */
    private val usCoins = intArrayOf(1, 5, 10, 25, 50, 100)

    /** A set of coins for which the greedy approach fails to get the optimal result */
    private val coins2 = intArrayOf(1, 3, 4)

    @Test
    fun changeCoins() {
        assertEquals(listOf(25, 5, 1, 1, 1, 1), changeCoins(34, usCoins))
        assertEquals(listOf(100, 100, 50, 25, 10, 1, 1, 1, 1), changeCoins(289, usCoins))
    }

    @Test
    fun `changeCoins - greedy fails to find optimal`() {
        assertEquals(listOf(4, 1, 1), changeCoins(6, coins2))
        val optimalCoins = listOf(3, 3)
        assertNotEquals(optimalCoins, changeCoins(6, coins2))
    }
}