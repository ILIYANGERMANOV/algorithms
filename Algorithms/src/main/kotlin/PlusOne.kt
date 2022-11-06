class PlusOne {
    fun plusOne(digits: IntArray): IntArray {
        val n = digits.size
        val lastIndex = n - 1
        if (digits[lastIndex] < 9) {
            digits[lastIndex] = digits[lastIndex] + 1
            return digits
        }

        println("input = ${digits.toList()}")

        var carry = 0
        for (i in (n - 1) downTo 0) {
            val sum = if (i == n - 1) digits[i] + 1 + carry else digits[i] + carry
            println("sum[$i] = $sum, carry = $carry")
            digits[i] = sum % 10
            carry = sum / 10
        }

        println("result = ${digits.toList()}")

        if (carry > 0) {
            println("Overflow!!!")
            // overflows
            val overflowRes = IntArray(n + 1)
            overflowRes[0] = 1
            for (i in 1..n) {
                overflowRes[i] = digits[i - 1]
            }
            println("overflow = ${overflowRes.toList()}")
            return overflowRes
        }

        return digits
    }
}