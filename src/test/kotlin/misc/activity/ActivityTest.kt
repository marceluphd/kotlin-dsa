package misc.activity

import org.junit.Assert.*
import org.junit.Test

class ActivityTest {
    @Test
    fun testToString() {
        assertEquals(
            "Activity(title='Computer Programming', time=(10:00-11:00))",
            Activity(title = "Computer Programming", time = Interval("10:00", "11:00")).toString()
        )
    }

    @Test
    fun overlapsWith() {
        assertTrue(Activity("foo", Interval("10:00", "11:00"))
            .overlapsWith(Activity("bar", Interval("10:30", "11:30"))))
        assertFalse(Activity("foo", Interval("10:00", "11:00"))
            .overlapsWith(Activity("bar", Interval("09:00", "10:00"))))
        assertFalse(Activity("foo", Interval("10:00", "11:00"))
            .overlapsWith(Activity("bar", Interval("09:00", "09:59"))))
    }

    @Test
    fun isCompatibleWith() {
        assertFalse(Activity("foo", Interval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", Interval("10:30", "11:30"))))
        assertTrue(Activity("foo", Interval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", Interval("09:00", "10:00"))))
        assertTrue(Activity("foo", Interval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", Interval("09:00", "09:59"))))
    }
}