/**
 * # 567. Permutation in String
 * Problem:
 * https://leetcode.com/problems/permutation-in-string/
 *
 * WARNING: This solution is not optimal!
 */
class PermutationInString {
    fun checkInclusion(target: String, source: String): Boolean {
        if (target.length > source.length) return false
        return permuteFind(target) {
            source.contains(it)
        }
    }

    private fun permuteFind(
        arr: String,
        n: Int = arr.length,
        k: Int = 0,
        find: (String) -> Boolean
    ): Boolean {
        if (k == n - 1) {
            return find(arr)
        }

        for (i in k until n) {
            val newArr = arr.swap(k, i)
            if (i != k && arr == newArr) continue
            if (permuteFind(arr = newArr, n = n, k = k + 1, find = find)) {
                return true
            }
        }
        return false
    }

    private fun String.swap(i: Int, j: Int): String {
        if (i == j) return this
        val arr = this.toCharArray()
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
        return arr.concatToString()
    }
}