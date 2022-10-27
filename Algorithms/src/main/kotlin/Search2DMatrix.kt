/**
 * # Search a 2D Matrix
 * Problem:
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
class Search2DMatrix {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rowsCount = matrix.size
        if (rowsCount == 1) return binarySearchRow(matrix[0], target)
        return binarySearchMatrix(
            matrix = matrix,
            target = target,
            lowIndex = 0,
            highIndex = rowsCount - 1
        )
    }

    private fun binarySearchMatrix(
        matrix: Array<IntArray>,
        target: Int,
        lowIndex: Int,
        highIndex: Int
    ): Boolean {
        if (lowIndex > highIndex) return false

        val mid = (lowIndex + highIndex) / 2
        val row = matrix[mid]
        val rowFirst = row[0]
        val rowLast = row.last()
        return when {
            target in rowFirst..rowLast -> binarySearchRow(row, target)
            target < rowLast ->
                // search lower row
                binarySearchMatrix(
                    matrix = matrix,
                    target = target,
                    lowIndex = 0,
                    highIndex = mid - 1
                )
            else ->
                // search higher row
                binarySearchMatrix(
                    matrix = matrix,
                    target = target,
                    lowIndex = mid + 1,
                    highIndex = highIndex
                )
        }
    }

    private fun binarySearchRow(
        row: IntArray,
        target: Int,
        low: Int = 0,
        high: Int = row.size - 1
    ): Boolean {
        if (low > high) return false

        val mid = (low + high) / 2
        val midValue = row[mid]
        if (target == midValue) return true

        return if (target > midValue) {
            // search right
            binarySearchRow(
                row = row,
                target = target,
                low = mid + 1,
                high = high
            )
        } else {
            // search left
            binarySearchRow(
                row = row,
                target = target,
                low = low,
                high = mid - 1
            )
        }
    }
}