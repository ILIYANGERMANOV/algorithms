class AddStrings {
    fun addStrings(num1: String, num2: String): String {
        val l1 = num1.length
        val l2 = num2.length

        val answer = StringBuilder(maxOf(l1, l2) + 1)

        /*
        19 + 543 = 562

        91  +
        345
        ---
        265

        345 +
        91
        ---
         */

        val num1rev = num1.reversed()
        val num2rev = num2.reversed()

        var carry = 0
        for (i in 0 until maxOf(l1, l2)) {
            val digit1 = num1rev.getOrNull(i)?.digitToInt() ?: 0
            val digit2 = num2rev.getOrNull(i)?.digitToInt() ?: 0

            val res = digit1 + digit2 + carry
            val newDigit = res % 10
            carry = res / 10

            answer.append(newDigit.digitToChar())
        }

        if (carry > 0) {
            answer.append(carry.digitToChar())
        }

        answer.reverse()
        return answer.toString()
    }


    /**
     * LeetCode compatibility
     */
    private fun Char.digitToInt(): Int = this.toInt() - '0'.toInt()

    private fun Int.digitToChar(): Char = '0' + this
}