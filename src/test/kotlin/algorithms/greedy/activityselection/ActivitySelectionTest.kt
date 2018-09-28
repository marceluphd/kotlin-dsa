package algorithms.greedy.activityselection

import misc.activity.Activity
import misc.activity.Interval
import org.junit.Assert.*
import org.junit.Test

class ActivitySelectionTest {

    private val art = Activity("Art", Interval("9:00", "10:00"))
    private val eng = Activity("Engineering", Interval("9:30", "10:30"))
    private val math = Activity("Math", Interval("10:00", "11:00"))
    private val cs = Activity("Computer Science", Interval("10:30", "11:30"))
    private val music = Activity("Music", Interval("11:00", "12:00"))
    private val classes = listOf(art, eng, math, cs, music)

    @Test
    fun testScheduleActivities() {
        assertEquals(
            setOf(art, math, music),
            scheduleActivities(classes)
        )
    }
}