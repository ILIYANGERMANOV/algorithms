fun main() {
    val sut = NextClosestTime()

    for (h in 0..23) {
        val hour = if (h < 10) "0$h" else h.toString()
        for (m in 0..59) {
            val minute = if (m < 10) "0$m" else m.toString()
            val time = "$hour:$minute"
            println("\"$time\" -> \"${sut.nextClosestTime(time)}\"")
        }
    }
}

class NextClosestTime {
    fun nextClosestTime(time: String): String {
        val digits = mutableSetOf<Int>()
        digits.add(time[0].digitToInt())
        digits.add(time[1].digitToInt())
        digits.add(time[3].digitToInt())
        digits.add(time[4].digitToInt())
        val sortedDigits = digits.sorted()

        val hour = String(charArrayOf(time[0], time[1]))
        val minute = String(charArrayOf(time[3], time[4]))
        val minuteInt = minute.toInt()
        val hasZero: Boolean = sortedDigits.first() == 0

        return tryNextMinute(
            sortedDigits = sortedDigits,
            hour = hour,
            minute = minute,
            minuteInt = minuteInt,
            hasZero = hasZero
        ) ?: tryNextHorWithMinimizedMin(
            sortedDigits = sortedDigits,
            hour = hour,
            hasZero = hasZero
        ) ?: minimizeTime(sortedDigits)
    }

    private fun tryNextMinute(
        sortedDigits: List<Int>,
        hour: String,
        minute: String,
        minuteInt: Int,
        hasZero: Boolean
    ): String? {
        if (minute == "59") return null

        for (i in sortedDigits.indices) {
            val digit1 = sortedDigits[i]
            if (hasZero) {
                // try single digit
                if (digit1 > minuteInt) return "$hour:0$digit1"
            }

            for (j in sortedDigits.indices) {
                val digit2 = sortedDigits[j]
                val newMinute = validMinute(digit1, digit2)
                if (newMinute != null && newMinute > minuteInt) return "$hour:${format(newMinute)}"
            }
        }

        return null
    }

    private fun tryNextHorWithMinimizedMin(
        sortedDigits: List<Int>,
        hour: String,
        hasZero: Boolean
    ): String? {
        fun minimizedMin(): String = sortedDigits.first().run {
            if (hasZero) "0$this" else "$this$this"
        }

        if (hour == "23") return null
        val hourInt = hour.toInt()

        for (i in sortedDigits.indices) {
            val digit1 = sortedDigits[i]
            if (hasZero) {
                // try single digit
                if (digit1 > hourInt) return "0$digit1:${minimizedMin()}"
            }

            for (j in sortedDigits.indices) {
                val digit2 = sortedDigits[j]
                val newHour = validHour(digit1, digit2)
                if (newHour != null && newHour > hourInt) return "${format(newHour)}:${minimizedMin()}"
            }
        }

        return null
    }

    private fun minimizeTime(
        sortedDigits: List<Int>
    ): String {
        val first = sortedDigits.first()
        return "$first$first:$first$first"
    }

    private fun format(newNumber: Int): String = if (newNumber < 10)
        "0$newNumber" else newNumber.toString()

    private fun validHour(digit1: Int, digit2: Int): Int? {
        val newHour = digit1 * 10 + digit2
        return newHour.takeIf { it in 0..23 }
    }

    private fun validMinute(digit1: Int, digit2: Int): Int? {
        val newMinute = digit1 * 10 + digit2
        return newMinute.takeIf { it in 0..59 }
    }

    /**
     * LeetCode compatibility
     */
    private fun Char.digitToInt(): Int = this.toInt() - '0'.toInt()
}