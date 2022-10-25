class MaxSubArray {
    fun maxSubArray(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return nums[0]

        var currentMax = nums.first()
        val possibleMax = mutableListOf<Int>()

        for (i in 1 until n) {
            val num = nums[i]
            if (num > 0) {
                // positive number
                if (currentMax > 0) {
                    // positive sum => increment with positive number
                    currentMax += num
                } else {
                    // negative sum => replace with the new positive number
                    currentMax = num
                }
            } else {
                // negative number
                if (currentMax > 0) {
                    // positive sum
                    possibleMax.add(currentMax) // remember local max
                    currentMax += num
                } else {
                    // negative sum
                    if (num > currentMax) {
                        // replace with the number
                        currentMax = num
                    } else {
                        possibleMax.add(currentMax)
                        currentMax += num
                    }
                }
            }
        }

        return maxOf(possibleMax.toIntArray() + currentMax)
    }

    private fun maxOf(arr: IntArray): Int {
        val n = arr.size
        if (n == 1) return arr.first()
        var max = arr.first()
        for (i in 1 until n) {
            max = maxOf(arr[i], max)
        }
        return max
    }
}