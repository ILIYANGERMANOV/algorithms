class MissingRanges {
    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {
        if (nums.isEmpty()) {
            return listOf(missingRange(lower, upper))
        }

        val missing = mutableListOf<String>()

        // bounds = [-10, 10];
        // nums = [-5,-4,-3,2,3,5,6,8,9,10]
        // answer: "-10->-6", "-2->1", "4", "7"

        // say missing lower
        val first = nums.first()
        if (first > lower) {
            missing.addMissingRange(numLeft = lower, numRight = first - 1)
        }

        for (i in 1 until nums.size) {
            val prev = nums[i - 1]
            val current = nums[i]
            if (current > upper) {
                // stop! no missing to look fore
                return missing
            }

            if (prev + 1 != current) {
                // not consequent, potential missing
                missing.addMissingRange(prev + 1, current - 1)
            }
        }

        val last = nums.last()
        if (last < upper) {
            missing.addMissingRange(numLeft = last + 1, numRight = upper)
        }

        return missing
    }

    private fun MutableList<String>.addMissingRange(numLeft: Int, numRight: Int) {
        this.add(missingRange(numLeft, numRight))
    }

    private fun missingRange(numLeft: Int, numRight: Int): String = if (numRight - numLeft == 0)
        numLeft.toString()
    else
        "$numLeft->$numRight"

}