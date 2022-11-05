class ContainerMostWater {
    fun maxArea(heightArr: IntArray): Int {
        var left = 0
        var right = heightArr.lastIndex
        var width: Int
        var maxWaterArea = 0

        while (left < right) {
            width = right - left
            maxWaterArea = maxOf(
                maxWaterArea,
                minOf(heightArr[left], heightArr[right]) * width
            )

            if (heightArr[left] <= heightArr[right]) {
                left++
            } else {
                right--
            }
        }

        return maxWaterArea
    }
}