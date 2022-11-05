class LongestSubstringWoReps {
    fun lengthOfLongestSubstring(s: String): Int {
        val strLen = s.length
        if (strLen == 1) return 1
        return solve(str = s, strLen = strLen)
    }

    private fun solve(
        str: String,
        strLen: Int = str.length,
        startIndex: Int = 0,
        longest: Int = 0
    ): Int {
        if (startIndex >= strLen - 1) return longest
        val letters = mutableListOf<Char>()

        for (i in startIndex until strLen) {
            val c = str[i]
            if (letters.contains(c)) {
                // reset
                return solve(
                    str = str,
                    strLen = strLen,
                    startIndex = startIndex + 1,
                    longest = maxOf(letters.size, longest)
                )
            } else {
                letters.add(c)
            }
        }

        return maxOf(longest, letters.size)
    }
}