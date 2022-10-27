/**
 * # 1674. Minimum Moves to Make Array Complementary
 * Problem:
 * https://leetcode.com/problems/minimum-moves-to-make-array-complementary/
 */
class MinMovesToMakeArrComplementary {
    fun minMoves(arr: IntArray, limit: Int): Int {
        println("BEGIN CASE - ${arr.toList()} with limit $limit ---------------")
        val sums = mutableListOf<Int>()
        val n = arr.size
        for (i in 0..n / 2) {
            sums.add(arr[i] + arr[n - i - 1])
        }
        println(sums)
        TODO()
    }
}