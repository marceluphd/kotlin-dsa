package types.activity

data class TimeInterval(val startTime: Time, val endTime: Time) {
    override fun toString(): String = "($startTime-$endTime)"

    /**
     * Secondary constructor which allows creating an TimeInterval with time strings:
     * `TimeInterval("10:00", "14:45)`
     */
    constructor(start: String, end: String) : this(Time(start), Time(end))

    /**
     * Check whether the intervals overlap.
     * Note that (10:00-11:00) & (11:00-12:00) are considered non-overlapping.
     * @return true if the Intervals overlap.
     */
    fun overlapsWith(other: TimeInterval): Boolean =
        (this.endTime > other.startTime) && (this.startTime < other.endTime)

    /**
     * @return true if the Intervals do not overlap
     */
    fun isCompatibleWith(other: TimeInterval): Boolean = !overlapsWith(other)

    /**
     * Merge 2 Intervals.
     */
    fun mergeWith(other: TimeInterval): TimeInterval = TimeInterval(
        minOf(startTime, other.startTime),
        maxOf(endTime, other.endTime)
    )
}
