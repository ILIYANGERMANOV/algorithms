class MaxSubArray {
    fun maxSubArray(nums: IntArray): Int {
        var maxSum = nums.first()
        val l = nums.size
        for (offset in 0 until l) {
            for (sL in offset until l) {
                maxSum = maxOf(nums.slice(offset..sL).sum(), maxSum)
            }
        }
        return maxSum
    }
}