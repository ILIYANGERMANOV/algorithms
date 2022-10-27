class FruitIntoBaskets {
    fun totalFruit(fruits: IntArray): Int = solve(fruits)

    private tailrec fun solve(
        types: IntArray,
        n: Int = types.size,
        lastMax: Int = 0,
        startIndex: Int = 0,
        lastResetIndex: Int? = null
    ): Int {
        var currentMax = 0
        var lastPickType: Int? = null
        var lastPickIndex: Int? = null

        val slots = arrayOf<Int?>(null, null)
        for (i in startIndex until n) {
            val type = types[i]
            if (pick(slots = slots, type = type)) {
                // picking successful, continue picking
                currentMax++
                if (lastPickType != type) {
                    lastPickIndex = i
                }
                lastPickType = type
            } else {
                // baskets full! Save local max & reset.
                if (lastPickIndex != lastResetIndex) {
                    // reset and go back to the last type
                    slots[0] = null
                    slots[1] = null
                    return solve(
                        types = types,
                        lastMax = maxOf(currentMax, lastMax),
                        startIndex = lastPickIndex!!,
                        lastResetIndex = lastPickIndex
                    )
                } else {
                    // just reset and pick the new type
                    slots[0] = type
                    slots[1] = null
                    currentMax = 1
                }
            }
        }

        return maxOf(currentMax, lastMax)
    }

    private fun pick(slots: Array<Int?>, type: Int): Boolean {
        fun trySlot(slotIndex: Int): Boolean {
            val slot = slots[slotIndex]
            if (slot == null) {
                // Slot is free, take it
                slots[slotIndex] = type
                return true
            }

            // slot if not free
            return slot == type
        }

        return trySlot(0) || trySlot(1)
    }
}