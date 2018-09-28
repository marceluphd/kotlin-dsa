package datastructures.interval

import kotlin.math.max
import kotlin.math.min

/**
 * Definition for an interval.
 * class Interval(
 *     var start: Int = 0,
 *     var end: Int = 0
 * )
 */
class Interval(var start: Int = 0, var end: Int = 0) {
    override fun toString(): String = "($start, $end)"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Interval

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start
        result = 31 * result + end
        return result
    }

}

fun Interval.overlapsWith(other: Interval): Boolean = (this.end >= other.start) || (other.end <= this.start)
fun Interval.mergeWith(other: Interval): Interval = Interval(min(start, other.start), max(end, other.end))