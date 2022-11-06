class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        val n = nums.size
        var distanceToJump: Int? = null

        if (nums.size <= 1) return true

        var start = n - 1
        if (nums.last() == 0) {
            start--
        }

        for (i in start downTo 0) {
            val current = nums[i]
            if (distanceToJump != null && current < distanceToJump) {
                distanceToJump++
            } else {
                distanceToJump = null
            }

            // [x,0]
            if (current == 0 && distanceToJump == null) {
                distanceToJump = 2
            }
        }

        return distanceToJump == null
    }
}