package misc.activity

data class Activity(val title: String = "Untitled", val time: Interval) {
    override fun toString(): String {
        return "Activity(title='$title', time=$time)"
    }
}

/** See [Interval.overlapsWith] */
fun Activity.overlapsWith(other: Activity): Boolean = time.overlapsWith(other.time)

/** See [Interval.isCompatibleWith] */
fun Activity.isCompatibleWith(other: Activity): Boolean = time.isCompatibleWith(other.time)