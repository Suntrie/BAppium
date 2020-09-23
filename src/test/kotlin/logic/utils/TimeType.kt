package logic.utils

import kotlin.random.Random

enum class TimeType(val instance: Int) {
    HOUR(0), MINUTE(1);

    companion object {

        fun randomHour() = Random.nextInt(1, 13)
        fun randomMinutes() = Random.nextInt(1,60)
        fun randomTimeSuff() = setOf("PM", "AM").elementAt(Random.nextInt(2))

        fun getFormattedString(hour: Int, minutes: Int, suff: String)="%02d:%02d %s".format(hour, minutes, suff)

    }
}
