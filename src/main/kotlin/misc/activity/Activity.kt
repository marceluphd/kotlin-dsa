package misc.activity

data class Activity(val title: String = "Untitled", val time: Interval) {
    override fun toString(): String = "Activity(title='$title', time=$time)"

    /** See [Interval.overlapsWith] */
    fun overlapsWith(other: Activity): Boolean = time.overlapsWith(other.time)

    /** See [Interval.isCompatibleWith] */
    fun isCompatibleWith(other: Activity): Boolean = time.isCompatibleWith(other.time)
}
