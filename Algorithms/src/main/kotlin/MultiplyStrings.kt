class MultiplyStrings {
    fun multiply(num1: String, num2: String): String {
        val l1 = num1.length
        val l2 = num2.length
        val answer = StringBuilder(l1 + l2)
        for (i in 0 until l1 + l2) {
            answer.append('0')
        }

        val num1reversed = num1.reversed()
        val num2reversed = num2.reversed()


        /*
        12 * 10 = 120

        21 x
        01
        --
        0
        00
        20
        100
        =>
        0210


        15 * 26 = 390

        51 x
        62
        --
        03 (currentPos = 0; place1 = 0 #6, place2 = 0 #5)
        06 (currentPos = 1; place1 = 0 #6, place2 = 1 #1)
        001 (currentPos = 1; place1 = 1 #2, place2 = 0 #5)
        002 (currentPos = 2; place1 = 2 #2, place2 = 1 #1)
        =>
        0930

        999 * 999 = 998001

        999 x
        999
        ---
        18 (place1 = 0, place1 = 0)
        018 (place1 = 0, place2 = 1)
        0018 (place1 = 0, place2 = 2)
        018 (place1 = 1, place2 = 0)
        0018 (place1 = 1, place2 = 1)
        00018 (place1 = 1, place2 = 2)
        0018 (place1 = 2, place2 = 0)
        00018 (place1 = 2, place2 = 1)
        000018 (place1 = 2, place2 = 2)


         */

        for (place1 in 0 until l1) {
            val digit1 = num1reversed[place1].digitToInt()
            for (place2 in 0 until l2) {
                val digit2 = num2reversed[place2].digitToInt()
                val currentPos = place1 + place2

                val multiply = digit1 * digit2
                val digitRes = multiply % 10
                val carry = multiply / 10

                answer.sum(currentPos, digitRes)
                answer.sum(currentPos + 1, carry)
            }
        }

        answer.trimTrailingZeros()
        answer.reverse()
        return answer.toString()
    }

    private fun StringBuilder.sum(pos: Int, digit: Int) {
        val newNumber = this[pos].digitToInt() + digit
        val newDigit = newNumber % 10
        val newCarry = newNumber / 10
        if (newCarry > 0) {
            sum(pos + 1, newCarry)
        }
        this[pos] = newDigit.digitToChar()
    }

    private fun StringBuilder.trimTrailingZeros() {
        if (this.last() == '0') {
            this.deleteCharAt(this.lastIndex)
            if (this.length > 1) {
                trimTrailingZeros()
            }
        }
    }

    /**
     * LeetCode compatibility
     */
    private fun Char.digitToInt(): Int = this.toInt() - '0'.toInt()

    private fun Int.digitToChar(): Char = '0' + this
}