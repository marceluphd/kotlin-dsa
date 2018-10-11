package types.activity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TimeIntervalTest {

    @Test
    fun secondaryConstructor() {
        assertEquals(TimeInterval(Time(10, 0), Time(11, 0)), TimeInterval("10:00", "11:00"))
    }

    @Test
    fun testToString() {
        assertEquals("(10:00-11:00)", TimeInterval(Time(10, 0), Time(11, 0)).toString())
        assertEquals("(10:00-11:00)", TimeInterval(Time("10:00"), Time("11:00")).toString())
    }

    @Test
    fun overlapsWith() {
        assertTrue(TimeInterval("10:00", "11:00").overlapsWith(TimeInterval("10:30", "11:30")))
        assertFalse(TimeInterval("10:00", "11:00").overlapsWith(TimeInterval("09:00", "10:00")))
        assertFalse(TimeInterval("10:00", "11:00").overlapsWith(TimeInterval("09:00", "09:59")))
    }

    @Test
    fun isCompatibleWith() {
        assertFalse(TimeInterval("10:00", "11:00").isCompatibleWith(TimeInterval("10:30", "11:30")))
        assertTrue(TimeInterval("10:00", "11:00").isCompatibleWith(TimeInterval("09:00", "10:00")))
        assertTrue(TimeInterval("10:00", "11:00").isCompatibleWith(TimeInterval("09:00", "09:59")))
    }

    @Test
    fun mergeWith() {
        assertEquals(
            TimeInterval("07:00", "12:00"), TimeInterval(
                "07:00",
                "10:00"
            ).mergeWith(TimeInterval("10:00", "12:00")))
    }
}