package extensions.math

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigInteger

class NumberExtTest {

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
    fun factorial() {
        assertEquals(BigInteger.ONE, 0.factorial())
        assertEquals(BigInteger.ONE, 1.factorial())
        assertEquals(BigInteger.valueOf(2L), 2.factorial())
        assertEquals(BigInteger.valueOf(6L), 3.factorial())
        assertEquals(BigInteger.valueOf(24L), 4.factorial())
        assertEquals(BigInteger.valueOf(120L), 5.factorial())
        assertEquals(BigInteger.valueOf(720L), 6.factorial())
        assertEquals(BigInteger.valueOf(5040L), 7.factorial())
        assertEquals(BigInteger.valueOf(3628800L), 10.factorial())
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

}