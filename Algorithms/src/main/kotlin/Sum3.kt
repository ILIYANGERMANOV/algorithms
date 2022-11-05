class Sum3 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val res = mutableSetOf<List<Int>>()
        val n = nums.size
        for (i in 0 until n) {
            val fixed = nums[i]
            if (fixed > 0) break
            if (i == 0 || nums[i - 1] != fixed) {
                twoSum(
                    nums = nums,
                    target = -fixed,
                    i = i,
                    n = n,
                    res = res
                )
            }
        }
        return res.toList()
    }

    private fun twoSum(
        nums: IntArray,
        target: Int,
        i: Int,
        n: Int,
        res: MutableSet<List<Int>>
    ) {
        val seen = mutableSetOf<Int>()
        for (j in i + 1 until n) {
            val complement = target - nums[j]
            if (seen.contains(complement)) {
                res.add(
                    listOf(nums[i], complement, nums[j]).sorted()
                )
            }
            seen.add(nums[j])
        }
    }
}