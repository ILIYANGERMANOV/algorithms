import kotlin.math.pow

/**
 * # 567. Permutation in String
 * Problem:
 * https://leetcode.com/problems/permutation-in-string/
 */
class PermutationInString {
    fun checkInclusion(target: String, source: String): Boolean {
        println("Begin CASE ----------------------")
        return permuteFind(target) { permutation ->
            println(permutation)
            source.contains(permutation)
        }
    }

    /**
     * "abc": size = 3
     * - abc (initial)
     * - bac (i=0) => swap(0,1) // j = i % 3 = 1; swap(j, j + 1)
     * - bca (i=1) => swap(1,2) // j = ...
     * - cba (i=2) => swap(0,1) // j = ...
     * - cab (i=3) => swap(1,2) // j = i % 3 = 0
     * - acb (i=4) => swap(0,1)
     * - acb (i=5) => swap(0,1)
    --- END
     *
     * "ab":
     * - ab (0) => no swap
     * - ba (1) => swap(0,1)
     * - ab (2) => swap(0,1)
     */
    private inline fun permuteFind(
        str: String,
        find: (String) -> Boolean
    ): Boolean {
        val len = str.length
        val permsCount = factorial(len)
        var perm = str
        if (find(perm)) return true
        // 1*2*3 => 0
        // 1*2*3*4 => 12
        // 1*2*3*4*5 => 30
        // 8! => 4
        for (p in 0 until permsCount) {
            val i = p % (len - 1)
            perm = if ((p + 1) % rotationInterval(permsCount, len) == 0) {
                // rotate
                println("ROTATE!!!")
                perm.swap(i, i + 1).reversed()
            } else {
                perm.swap(i, i + 1)
            }
            if (find(perm)) return true
        }
        return false
    }

    private fun rotationInterval(perms: Int, len: Int): Int =
        perms / 2.0.pow((len / 2) + 1).toInt()

    private val factMemo: MutableMap<Int, Int> = mutableMapOf(
        1 to 1,
        2 to 2,
        3 to 6,
        4 to 24,
        5 to 120,
    )

    private fun factorial(n: Int): Int {
        return factMemo[n] ?: if (n == 1) 1 else (n * factorial(n - 1)).apply {
            factMemo[this]
        }
    }

    private fun String.swap(i: Int, j: Int): String {
        val builder = StringBuilder(this)
        builder.replace(i, i + 1, this[j].toString())
        builder.replace(j, j + 1, this[i].toString())
        return builder.toString()
    }

    /*
        n = 4 perm = 24
        1234 outside loop
        2134 p = 0
        2314 p = 1
        2341 p = 2
        3241 p = 3
        3421 p = 4
        3412 p = 5
        4312 p = 6
        4132 p = 7
        4123 p = 8
        1423 p = 9
        1243 p = 10
        1234 -- fail 4321 # p = 11; (12) ((i + 1) % n) == n - 1

     */
}