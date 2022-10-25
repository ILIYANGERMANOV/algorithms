/**
 * Problem:
 * https://leetcode.com/problems/maximum-subarray/
 */
class MaxSubArray {
    fun maxSubArray(nums: IntArray): Int {
        val size = nums.size
        if (size == 1) return nums[0]

        var sum = nums.first()
        val possibleMaxes = ArrayList<Int>(size)

        for (i in 1 until size) {
            val n = nums[i]
            when {
                n > 0 -> if (sum > 0) {
                    // n(+) + sum(+) => continue the sum: n + sum
                    sum += n
                } else {
                    // n(+) + sum(-) => reset to n(+)
                    sum = n
                }
                else -> {
                    if (sum > 0) {
                        // n(-) + sum(+) => we can't know if we should reset the sum,
                        // because we can't predict the next numbers coming
                        // => 1) remember local max; 2) continue the sum;
                        possibleMaxes.add(sum)
                        sum += n
                    } else {
                        // sum(-)
                        if (n > sum) {
                            // n(-) is better than sum(-) => reset the sum to n(-)
                            sum = n
                        } else {
                            // n(-) is worse than sum(-) but we can't predict the future
                            // => 1) remember local max; 2) continue the sum;
                            possibleMaxes.add(sum)
                            sum += n
                        }
                    }
                }
            }
        }

        return if (possibleMaxes.isEmpty())
            sum else maxOf(max(possibleMaxes), sum)
    }

    /**
     * LeetCode doesn't know the "List.maxOf{}" built-in function
     * so we have to create our own one.
     */
    private fun max(arr: ArrayList<Int>): Int {
        var max = arr.first()
        val size = arr.size
        for (i in 1 until size) {
            max = maxOf(arr[i], max)
        }
        return max
    }
}