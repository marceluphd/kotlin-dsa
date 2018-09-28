package misc.activity

data class Interval(val startTime: Time, val endTime: Time) {
    override fun toString(): String = "($startTime-$endTime)"

    /**
     * Secondary constructor which allows creating an Interval with time strings:
     * `Interval("10:00", "14:45)`
     */
    constructor(start: String, end: String) : this(Time(start), Time(end))
}

/**
 * Check whether the intervals overlap.
 * Note that (10:00-11:00) & (11:00-12:00) are considered non-overlapping.
 * @return true if the Intervals overlap.
 */
fun Interval.overlapsWith(other: Interval): Boolean =
    (this.endTime > other.startTime) && (this.startTime < other.endTime)

/**
 * @return true if the Intervals do not overlap
 */
fun Interval.isCompatibleWith(other: Interval): Boolean = !overlapsWith(other)

/**
 * Merge 2 Intervals.
 */
fun Interval.mergeWith(other: Interval): Interval = Interval(
    minOf(startTime, other.startTime),
    maxOf(endTime, other.endTime)
)