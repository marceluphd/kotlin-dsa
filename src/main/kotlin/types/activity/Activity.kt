package types.activity

/**
 * Represents an activity/meeting that occurs at the specified [TimeInterval].
 * Used for the Activity Selection Problem - [algorithms.greedy.activityselection.scheduleActivities].
 */
data class Activity(val title: String = "Untitled", val time: TimeInterval) {
    override fun toString(): String = "Activity(title='$title', time=$time)"

    /** See [TimeInterval.overlapsWith] */
    fun overlapsWith(other: Activity): Boolean = time.overlapsWith(other.time)

    /** See [TimeInterval.isCompatibleWith] */
    fun isCompatibleWith(other: Activity): Boolean = time.isCompatibleWith(other.time)
}
