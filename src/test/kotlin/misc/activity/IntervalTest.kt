package misc.activity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IntervalTest {

    @Test
    fun secondaryConstructor() {
        assertEquals(Interval(Time(10, 0), Time(11, 0)), Interval("10:00", "11:00"))
    }

    @Test
    fun testToString() {
        assertEquals("(10:00-11:00)", Interval(Time(10, 0), Time(11, 0)).toString())
        assertEquals("(10:00-11:00)", Interval(Time("10:00"), Time("11:00")).toString())
    }

    @Test
    fun overlapsWith() {
        assertTrue(Interval("10:00", "11:00").overlapsWith(Interval("10:30", "11:30")))
        assertFalse(Interval("10:00", "11:00").overlapsWith(Interval("09:00", "10:00")))
        assertFalse(Interval("10:00", "11:00").overlapsWith(Interval("09:00", "09:59")))
    }

    @Test
    fun isCompatibleWith() {
        assertFalse(Interval("10:00", "11:00").isCompatibleWith(Interval("10:30", "11:30")))
        assertTrue(Interval("10:00", "11:00").isCompatibleWith(Interval("09:00", "10:00")))
        assertTrue(Interval("10:00", "11:00").isCompatibleWith(Interval("09:00", "09:59")))
    }

    @Test
    fun mergeWith() {
        assertEquals(
            Interval("07:00", "12:00"), Interval(
                "07:00",
                "10:00"
            ).mergeWith(Interval("10:00", "12:00")))
    }
}