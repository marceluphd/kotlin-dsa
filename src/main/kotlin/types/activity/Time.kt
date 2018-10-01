package types.activity

/**
 * A simple time class with hour and minutes.
 */
data class Time(val hour: Int, val minutes: Int): Comparable<Time> {

    init {
        require(hour in 0 until 24) { "Invalid hour" }
        require(minutes in 0 until 60) { "Invalid minutes" }
    }

    /**
     * Constructor accepting a time string in format "HH:MM" for convenience
     */
    constructor(timeString: String) : this(
        hour = timeString.split(':')[0].toInt(),
        minutes = timeString.split(':')[1].toInt()
    )

    override fun toString(): String {
        val paddedToNDigits: (Int, Int) -> String = { value: Int, n: Int -> "$value".padStart(n, '0') }
        return "${paddedToNDigits(hour, 2)}:${paddedToNDigits(minutes, 2)}"
    }

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     */
    override fun compareTo(other: Time): Int {
        val hourDiff = hour - other.hour
        return if (hourDiff != 0) {
            hourDiff
        } else {
            minutes - other.minutes
        }
    }
}