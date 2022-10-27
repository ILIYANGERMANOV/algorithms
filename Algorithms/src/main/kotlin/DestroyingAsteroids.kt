/**
 * # 2126. Destroying Asteroids
 * Problem:
 * https://leetcode.com/problems/destroying-asteroids/
 */
class DestroyingAsteroids {
    fun asteroidsDestroyed(mass: Int, asteroids: IntArray): Boolean {
        val sorted = asteroids.sorted().toIntArray() // TODO: this might slow things down
        return solve(
            mass = mass.toLong(), // planet's mass may overflow => Long fixes it
            arr = sorted,
            index = 0,
            lastIndex = sorted.size - 1
        )
    }

    private tailrec fun solve(
        mass: Long,
        arr: IntArray,
        index: Int,
        lastIndex: Int
    ): Boolean {
        if (index > lastIndex) return true // all asteroids destroyed
        val currentValue = arr[index]
        if (mass < currentValue) return false
        return solve(
            mass = mass + currentValue,
            arr = arr,
            index = index + 1,
            lastIndex = lastIndex
        )
    }
}