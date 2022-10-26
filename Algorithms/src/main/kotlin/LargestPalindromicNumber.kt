/**
 * # Largest Palindromic Number
 * Problem:
 * https://leetcode.com/problems/largest-palindromic-number/
 */
class LargestPalindromicNumber {
    fun largestPalindromic(num: String): String {
        val digits = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (c in num) {
            val digit = c.digitToInt()
            digits[digit] += 1
        }

        val available = digits.mapIndexed { digit, count ->
            digit to count
        }.filter { (_, count) ->
            count > 0
        }.sortedWith { (digit1, count1), (digit2, count2) ->
            /**
             * Returns zero if the arguments are equal,
             * a negative number if the first argument is less than the second,
             * or a positive number if the first argument is greater than the second.
             */
            val oneRank = (if (count1 > 1) 100 else 0) + digit1
            val twoRank = (if (count2 > 1) 100 else 0) + digit2
            twoRank - oneRank// sort descending
        }

        val mirror = mirror(available)
        val palindrome = palindrome(mirror)

        // Filter leading 0's
        return if (palindrome.first() == '0') {
            palindrome.replace("0", "").takeIf { it.isNotEmpty() } ?: "0"
        } else palindrome
    }

    private fun palindrome(mirror: Pair<String, String?>): String {
        val (str, left) = mirror
        return if (left != null)
            str + left + str.reversed()
        else str + str.reversed()
    }


    private fun mirror(available: List<Pair<Int, Int>>): Pair<String, String?> {
        if (available.isEmpty()) return "" to null
        val (digit, count) = available.first()
        return if (count.isEven()) {
            val first = digit.toString().repeat(count / 2)
            val (secondStr, secondLeft) = mirror(available.drop(1))
            first + secondStr to secondLeft
        } else {
            if (count > 1) {
                // not even; count >= 3
                val leftOver = when (digit) {
                    0 -> available.getOrNull(1)?.first?.toString() ?: "0"
                    else -> digit.toString()
                }
                digit.toString().repeat((count - 1) / 2) to leftOver
            } else {
                // count = 1
                "" to digit.toString()
            }
        }
    }

    private fun Int.isEven(): Boolean = this % 2 == 0
}