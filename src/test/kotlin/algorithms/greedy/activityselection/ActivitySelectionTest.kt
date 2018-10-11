package algorithms.greedy.activityselection

import org.junit.Assert.*
import org.junit.Test
import types.activity.Activity
import types.activity.TimeInterval

class ActivitySelectionTest {

    private val art = Activity("Art", TimeInterval("9:00", "10:00"))
    private val eng = Activity("Engineering", TimeInterval("9:30", "10:30"))
    private val math = Activity("Math", TimeInterval("10:00", "11:00"))
    private val cs = Activity("Computer Science", TimeInterval("10:30", "11:30"))
    private val music = Activity("Music", TimeInterval("11:00", "12:00"))
    private val classes = listOf(art, eng, math, cs, music)

    @Test
    fun testScheduleActivities() {
        assertEquals(
            setOf(art, math, music),
            scheduleActivities(classes)
        )
    }
}