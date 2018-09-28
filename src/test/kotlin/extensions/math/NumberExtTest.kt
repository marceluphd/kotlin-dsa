package extensions.math

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigInteger

class NumberExtTest {

    @Test
    fun isEven() {
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { assertTrue(it.isEven) }
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { assertFalse(it.isEven) }
    }

    @Test
    fun isOdd() {
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { assertTrue(it.isOdd) }
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { assertFalse(it.isOdd) }
    }

    @Test
    fun charForDigit() {
        assertEquals('0', 0.charForDigit())
        assertEquals('1', 1.charForDigit())
        assertEquals('9', 9.charForDigit())
    }

    @Test
    fun digits() {
        assertEquals(listOf(0), 0.digits())
        assertEquals(listOf(1), 1.digits())
        assertEquals(listOf(9, 9), 99.digits())
        assertEquals(listOf(1, 2, 3, 4), 1234.digits())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `digits throws when negative`() {
        (-7).digits()
    }

    @Test
    fun numberOfDigits() {
        assertEquals(4, 1234.numberOfDigits())
        assertEquals(2, 99.numberOfDigits())
        assertEquals(2, 10.numberOfDigits())
        assertEquals(1, 9.numberOfDigits())
        assertEquals(1, 1.numberOfDigits())
        assertEquals(1, (-1).numberOfDigits())
        assertEquals(1, (-9).numberOfDigits())
        assertEquals(2, (-10).numberOfDigits())
        assertEquals(4, (-9999).numberOfDigits())
        assertEquals(1, 0.numberOfDigits())
        assertEquals(10, Int.MAX_VALUE.numberOfDigits())
        assertEquals(10, Int.MIN_VALUE.numberOfDigits())
    }

    @Test
    fun mostSignificantDigit() {
        assertEquals(1, 123.mostSignificantDigit())
    }

    @Test
    fun leastSignificantDigit() {
        assertEquals(3, 123.leastSignificantDigit())
    }

    @Test
    fun nMostSignificantDigits() {
        assertEquals(12, 123.nMostSignificantDigits(2))
    }

    @Test
    fun nLeastSignificantDigits() {
        assertEquals(23, 123.nLeastSignificantDigits(2))
    }

    @Test
    fun roundedToNDecimalPlaces() {
        assertEquals(123.0, 123.456789.roundedToNDecimalPlaces(0), 0.00001)
        assertEquals(123.4, 123.400000.roundedToNDecimalPlaces(1), 0.00001)
        assertEquals(123.5, 123.456789.roundedToNDecimalPlaces(1), 0.00001)
        assertEquals(123.46, 123.456789.roundedToNDecimalPlaces(2), 0.00001)
        assertEquals(123.45, 123.451234.roundedToNDecimalPlaces(2), 0.00001)
        assertEquals(123.457, 123.456789.roundedToNDecimalPlaces(3), 0.00001)
        assertEquals(123.4568, 123.456789.roundedToNDecimalPlaces(4), 0.00001)
        assertEquals(123.45679, 123.456789.roundedToNDecimalPlaces(5), 0.00001)
        assertEquals(123.456789, 123.456789.roundedToNDecimalPlaces(6), 0.00001)
        assertEquals(0.12, 0.1234.roundedToNDecimalPlaces(2), 0.00001)
        assertEquals(-0.12, (-0.1234).roundedToNDecimalPlaces(2), 0.00001)
    }

    @Test
    fun medianTest() {
        assertEquals(2, median(1, 2, 3))
        assertEquals(-2, median(-1, -2, -3))
        assertEquals(3, median(1, 3, 3))
        assertEquals(3, median(3, 3, 3))
    }

    @Test
    fun testMinOf() {
        assertEquals(1, minOf(1, 2, 3, 4))
        assertEquals(1L, minOf(1L, 2L, 3L, 4L))
        assertEquals(1f, minOf(1f, 2f, 3f, 4f))
        assertEquals(1.0, minOf(1.0, 2.0, 3.0, 4.0), 0.00001)
        assertEquals(1.0001, minOf(1.0001, 1.0002, 3.0, 4.0), 0.00001)
    }

    @Test
    fun `coerceToIntRange - Long`() {
        assertEquals(0, 0L.coerceToIntRange())
        assertEquals(-999, (-999L).coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE).toLong().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE).toLong().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, 2147483648L.coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (-91283472332L).coerceToIntRange())
    }

    @Test
    fun `coerceToIntRange - BigInteger`() {
        assertEquals(0, BigInteger.ZERO.coerceToIntRange())
        assertEquals(1, BigInteger.ONE.coerceToIntRange())
        assertEquals(10, BigInteger.TEN.coerceToIntRange())
        assertEquals(-999, (-999).toBigInteger().coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE).toBigInteger().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE).toBigInteger().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, 2147483648L.toBigInteger().coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (-91283472332L).toBigInteger().coerceToIntRange())
    }

    @Test
    fun `coerceToIntRange - Double`() {
        assertEquals(0, 0.0.coerceToIntRange())
        assertEquals(-999, (-999.0).coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE).toDouble().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE).toDouble().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, 2147483648.0.toLong().coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (-91283472332.0).coerceToIntRange())
    }

    @Test
    fun formatToNDecimalPlaces() {
        assertEquals("1235", (1234.5678).formatToNDecimalPlaces(0))
        assertEquals("1234.6", (1234.5678).formatToNDecimalPlaces(1))
        assertEquals("1234.57", (1234.5678).formatToNDecimalPlaces(2))
        assertEquals("1234.568", (1234.5678).formatToNDecimalPlaces(3))
        assertEquals("1234.5678", (1234.5678).formatToNDecimalPlaces(4))
    }
}