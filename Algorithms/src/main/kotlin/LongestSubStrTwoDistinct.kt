class LongestSubStrTwoDistinct {
    fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
        val n = s.length
        // 2 or less
        if (n == 1) return 1
        if (n < 3) return 2

        var maxLen = 2

        val charCountMap = mutableMapOf<Char, Int>()

        var l = 0
        var r = 0

        // expand
        while (r < n) {
            var c = s[r]
            charCountMap.increment(c)

            if (charCountMap.size <= 2) {
                maxLen = maxOf(maxLen, r - l + 1)
                println("max = $maxLen (l = $l, r = $r)")
            }
            while (charCountMap.size > 2) {
                // record max
                c = s[l]
                charCountMap.decrementOrRemove(c)
                l++
            }
            r++
        }

        return maxLen
    }

    private fun MutableMap<Char, Int>.increment(c: Char) {
        val count = this.getOrDefault(c, 0)
        this[c] = count + 1
    }

    private fun MutableMap<Char, Int>.decrementOrRemove(c: Char) {
        val count = this.getOrDefault(c, 0) - 1
        if (count <= 0) {
            this.remove(c)
        } else {
            this[c] = count
        }
    }
}