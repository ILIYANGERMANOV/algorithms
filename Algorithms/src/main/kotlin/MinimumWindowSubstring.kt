class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        if (t.isEmpty() || s.isEmpty() || t.length > s.length) return ""

        val tLetters = mutableMapOf<Char, Int>()
        for (c in t) {
            tLetters.increment(c)
        }

        val filteredS = mutableListOf<Pair<Int, Char>>()
        for ((i, c) in s.withIndex()) {
            if (tLetters.contains(c)) {
                filteredS.add(i to c)
            }
        }
        println("filteredS = ${filteredS.toList()}")
        println(s)

        var minLen = -1
        var mStart = -1
        var mEnd = -1

        val required = tLetters.size
        var l = 0
        var r = 0
        var formed = 0
        val windowCount = mutableMapOf<Char, Int>()
        while (r < filteredS.size) {
            var c = filteredS[r].second
            windowCount.increment(c)

            if (windowCount.getSafe(c) == tLetters.getSafe(c)) {
                formed++
            }

            while (l <= r && formed == required) {
                c = filteredS[l].second

                val start = filteredS[l].first
                val end = filteredS[r].first
                println("Candidate: \"${s.substring(start, end + 1)}\" (${s.substring(start, end + 1).length})")
                if (minLen == -1 || end - start + 1 < minLen) {
                    minLen = end - start + 1
                    mStart = start
                    mEnd = end
                    println("Saving: \"${s.substring(start, end + 1)}\"")
                }

                windowCount.decrement(c)
                if (windowCount.getSafe(c) < tLetters.getSafe(c)) {
                    formed--
                }
                l++
            }
            r++
        }

        println()
        println("-------------------")

        return if (minLen == -1) "" else s.substring(mStart, mEnd + 1)
    }

    private fun MutableMap<Char, Int>.increment(c: Char) {
        val count = this.getOrDefault(c, 0)
        this[c] = count + 1
    }

    private fun MutableMap<Char, Int>.decrement(c: Char) {
        val count = this.getOrDefault(c, 0)
        this[c] = count - 1
    }

    private fun MutableMap<Char, Int>.getSafe(c: Char): Int =
        getOrDefault(c, 0)
}