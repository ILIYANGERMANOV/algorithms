class ContainerMostWater {
    fun maxArea(height: IntArray): Int {
        var lIndex = 0
        val initialR = height.size - 1
        var rIndex = initialR
        var res = 0

        while (lIndex != rIndex) {
            while (lIndex != rIndex) {
                val lHeight = height[lIndex]
                val rHeight = height[rIndex]
                val width = rIndex - lIndex
                val area = width * minOf(lHeight, rHeight)
                res = maxOf(res, area)
                rIndex--
            }
            rIndex = initialR
            lIndex++
        }

        return res
    }
}