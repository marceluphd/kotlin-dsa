package types.activity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ActivityTest {
    @Test
    fun testToString() {
        assertEquals(
            "Activity(title='Computer Programming', time=(10:00-11:00))",
            Activity(title = "Computer Programming", time = TimeInterval("10:00", "11:00")).toString()
        )
    }

    @Test
    fun overlapsWith() {
        assertTrue(Activity("foo", TimeInterval("10:00", "11:00"))
            .overlapsWith(Activity("bar", TimeInterval("10:30", "11:30"))))
        assertFalse(Activity("foo", TimeInterval("10:00", "11:00"))
            .overlapsWith(Activity("bar", TimeInterval("09:00", "10:00"))))
        assertFalse(Activity("foo", TimeInterval("10:00", "11:00"))
            .overlapsWith(Activity("bar", TimeInterval("09:00", "09:59"))))
    }

    @Test
    fun isCompatibleWith() {
        assertFalse(Activity("foo", TimeInterval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", TimeInterval("10:30", "11:30"))))
        assertTrue(Activity("foo", TimeInterval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", TimeInterval("09:00", "10:00"))))
        assertTrue(Activity("foo", TimeInterval("10:00", "11:00"))
            .isCompatibleWith(Activity("bar", TimeInterval("09:00", "09:59"))))
    }
}