import kotlin.math.sqrt

class SuperPalindrome {
    fun superpalindromesInRange(left: String, right: String): Int {
        val l = left.toLong()
        val r = right.toLong()

        var count = 0
        var n = sqrt(l.toDouble()).toLong()

        while (n < r) {
            if (!palindrome(n)) {
                n++
                continue
            }
            val squared = n * n
            if (squared <= r) {
                if (squared >= l && palindrome(squared)) {
                    println(squared)
                    count++
                }
            } else {
                println("break for $n")
                break
            }
            n++
        }

        return count
    }

    private fun palindrome(n: Long): Boolean =
        n == n.toString().reversed().toLong()
}