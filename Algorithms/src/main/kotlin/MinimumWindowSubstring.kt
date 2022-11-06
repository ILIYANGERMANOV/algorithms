class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        val tLen = t.length
        val sLen = s.length
        if (tLen > sLen) return ""

        // load "t" letters
        val lettersMap = mutableMapOf<Char, Int>()
        reloadLetters(t = t, lettersMap = lettersMap)

        return solve(
            s = s,
            t = t,
            sLen = sLen,
            start = 0,
            min = "",
            lettersMap = lettersMap,
        )
    }

    private fun solve(
        s: String,
        t: String,
        sLen: Int,
        start: Int,
        min: String,
        lettersMap: MutableMap<Char, Int>,
    ): String {
        var current = ""

        println("Start loop: start = $start #${s[start]} in \"$s\"")
        for (i in start until sLen) {
            val c = s[i]
            lettersMap.decrement(c)
            current += c
            if (lettersMap.matches()) {
                val newMin = if (
                    current.length < min.length || min.isEmpty()
                ) current else min

                println("match!!! current = \"$current\", min = \"$min\"")
                println("lettersMap = ${lettersMap.map { (key, value) -> "$key=$value" }}")
                // reset and continue new
                reloadLetters(t = t, lettersMap = lettersMap)
                return solve(
                    s = s,
                    t = t,
                    sLen = sLen,
                    start = i,
                    min = newMin,
                    lettersMap = lettersMap
                )
            }
        }

        return min
    }

    private fun MutableMap<Char, Int>.matches(): Boolean {
        this.values.forEach {
            if (it > 0) return false
        }
        return true
    }

    private fun MutableMap<Char, Int>.increment(c: Char) {
        val count = this[c] ?: 0
        this[c] = count + 1
    }

    private fun MutableMap<Char, Int>.decrement(c: Char) {
        val count = this[c] ?: 0
        this[c] = count - 1
    }

    private fun reloadLetters(t: String, lettersMap: MutableMap<Char, Int>) {
        lettersMap.clear()
        for (c in t) {
            lettersMap.increment(c)
        }
    }
}