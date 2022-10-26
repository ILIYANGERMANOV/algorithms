import kotlin.math.sqrt

class SuperPalindrome {
    fun superpalindromesInRange(left: String, right: String): Int {
        val l = left.toLong()
        val r = right.toLong()

        var count = 0
        var n = l
        while (n < r) {
            if (superPalindrome(n)) {
                println(n)
                count++
            }
            n++
        }
        return count
    }

    private fun superPalindrome(n: Long): Boolean {
        if (!palindrome(n)) return false
        val square = sqrt(n.toDouble()).toLong()
        if (square * square != n) return false
        return palindrome(square)
    }

    private fun palindrome(n: Long): Boolean =
        n == n.toString().reversed().toLong()
}