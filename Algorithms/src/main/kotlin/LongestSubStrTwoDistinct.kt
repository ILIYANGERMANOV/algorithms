class LongestSubStrTwoDistinct {
    fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
        val n = s.length
        // 2 or less
        if (n == 1) return 1
        if (n < 3) return 2

        var maxLen = 2

        val lastSeenIndex = mutableMapOf<Char, Int>()

        var l = 0
        var r = 0

        // expand
        while (r < n) {
            val c = s[r]
            lastSeenIndex[c] = r

            if (lastSeenIndex.size == 3) {
                // we've used 3 letters! drop the most old
                l = lastSeenIndex.clearOldestIndex() + 1
            }
            maxLen = maxOf(maxLen, r - l + 1)
            r++
        }

        return maxLen
    }

    private fun MutableMap<Char, Int>.clearOldestIndex(): Int {
        var min: Pair<Char, Int>? = null
        this.forEach { (char, index) ->
            if (min == null || index < min!!.second) {
                min = char to index
            }
        }
        this.remove(min!!.first)
        return min!!.second
    }

}