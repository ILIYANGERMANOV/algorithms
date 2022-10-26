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
            val oneRank = (if (count1.isEven()) 100 else 0) + digit1
            val twoRank = (if (count2.isEven()) 100 else 0) + digit2
            twoRank - oneRank// sort descending
        }

        val mirror = mirror(available)
        val palindrome = if (mirror.last().digitToInt().isEven()) {
            mirror + mirror.reversed()
        } else {
            val evenMirror = mirror.dropLast(1)
            evenMirror + mirror.last() + evenMirror.reversed()
        }

        // Filter leading 0's
        return if (palindrome.first() == '0') {
            palindrome.replace("0", "")
        } else palindrome
    }

    private fun mirror(available: List<Pair<Int, Int>>): String {
        if (available.isEmpty()) return ""
        val (digit, count) = available.first()
        return if (count.isEven()) {
            // even, recurse
            digit.toString().repeat(count / 2) + mirror(available.drop(1))
        } else {
            // not even, just add once
            digit.toString()
        }
    }

    private fun Int.isEven(): Boolean = this % 2 == 0
}