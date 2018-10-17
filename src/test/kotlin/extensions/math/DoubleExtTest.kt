package extensions.math

import org.junit.Assert.*
import org.junit.Test

class DoubleExtTest {

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
    fun formatToNDecimalPlaces() {
        assertEquals("1235", (1234.5678).formatToNDecimalPlaces(0))
        assertEquals("1234.6", (1234.5678).formatToNDecimalPlaces(1))
        assertEquals("1234.57", (1234.5678).formatToNDecimalPlaces(2))
        assertEquals("1234.568", (1234.5678).formatToNDecimalPlaces(3))
        assertEquals("1234.5678", (1234.5678).formatToNDecimalPlaces(4))
    }
}