package com.maximbircu.devtools.common.presentation.tools.time

private const val MILLISECOND = 1
private const val SECOND = MILLISECOND * 1000
private const val MINUTE = SECOND * 60
private const val HOUR = MINUTE * 60
private const val DAY = HOUR * 24

class Time(
    val days: Long,
    val hours: Long,
    val minutes: Long,
    val seconds: Long,
    val milliseconds: Long
) {
    /**
     * Creates an instance of [Time].
     *
     * @param duration time duration in milliseconds
     */
    constructor(duration: Long) : this(
        days = duration / DAY,
        hours = duration % DAY / HOUR,
        minutes = duration % DAY % HOUR / MINUTE,
        seconds = duration % DAY % HOUR % MINUTE / SECOND,
        milliseconds = duration % DAY % HOUR % MINUTE % SECOND
    )

    /**
     * Creates an instance of [Time].
     *
     * @param duration takes duration as string with the following format [%dd %dh %dm %ds %dms]
     * for example: "2d 3h 20min 10s 100ms"
     */
    constructor(duration: String) : this(duration.toArrayWithTimeValues())

    @Suppress("MagicNumber")
    private constructor(timeParts: List<Long>) : this(
        days = timeParts[0],
        hours = timeParts[1],
        minutes = timeParts[2],
        seconds = timeParts[3],
        milliseconds = timeParts[4]
    )

    /** @return time duration in milliseconds. */
    fun inMilliseconds(): Long {
        val daysInMs = days * DAY
        val hoursInMs = hours * HOUR
        val minutesInMs = minutes * MINUTE
        val secondsInMs = seconds * SECOND
        return daysInMs + hoursInMs + minutesInMs + secondsInMs + milliseconds
    }

    /**
     * Converts time to text with the following format [%dd %dh %dm %ds %dms]
     * for example: "2d 3h 20min 10s 100ms".
     */
    override fun toString() = "${days}d ${hours}h ${minutes}m ${seconds}s ${milliseconds}ms"
}

private fun String.toArrayWithTimeValues() = split("[^0-9.]".toRegex()).asSequence()
    .filter(String::isNotBlank)
    .map(String::toLong)
    .toList()
