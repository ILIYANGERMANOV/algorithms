class RotateImage {
    fun rotate(matrix: Array<IntArray>) {
        val n = matrix.size

        flipDiagonal(matrix = matrix, n = n)

        println("2. Flip values diagonally")
        // 2. Flip values diagonally
        /*
        1 2 3
        4 5 6
        7 8 9

        1   2  3  4
        5   6  7  8
        9  10 11 12
        13 14 15 16
        */
        // 3x3 swaps: (center = 1)
        // (0,1) #2; (0, center) -> (1,0) #4
        // (1,2) #6; (center, n-1) -> (2,1) #8
        // 4x4 swaps: (center = 2)
        // (0,1) #2; (0, 1) -> (1,0) #5
        // (0,2) #3; (0, center) -> (2,0) #9
        // (1,3) #8; (1, n-1) -> (3,1) #14
        // (2,3) #12 (center, n-1) -> (3,2) #15
        val center = n / 2
        for (row in 0 until n) {
            for (column in 0 until n) {
                // 3x3
                // (0,0) (1,1) (2,2) n = 3
                // (0,2) (1,1) (0,2) n = 3
                // 4x4
                // (0,0) (1,1) (2,2) (3,3) n = 4
                // (0,3) (1,2) (2,1) (3,0) n = 4
                if (
                    row >= column
                ) continue // should not be diag
                matrix.swap(row to column, column to row)
            }

        }
        printMatrix(matrix)

        flipDiagonal(matrix, n)

        // 3. Reverse each row
        matrix.forEach { it.reverse() }
    }

    private fun flipDiagonal(
        matrix: Array<IntArray>,
        n: Int
    ) {
        println("1.Flip the right diag")
        // 1. Flip the right diag "/"
        /*
                            1 2 3
                            4 5 6
                            7 8 9

                            1   2  3  4
                            5   6  7  8
                            9  10 11 12
                            13 14 15 16
                            */
        // 3x3 swaps:
        // (0,2) -> (2,0)
        // 4x4 swaps:
        // (0,3) -> (3,0)
        // (1,2) -> (2,1)

        // right diag "/" => swap
        var row = 0
        var column = n - 1
        while (row <= column) {
            matrix.swap(row to column, column to row)
            row++
            column--
        }
        printMatrix(matrix)
    }

    private fun Array<IntArray>.swap(i: Pair<Int, Int>, j: Pair<Int, Int>) {
        println("Swapping $i #${this[i.first][i.second]} with $j #${this[j.first][j.second]}")
        val temp = this[i.first][i.second]
        this[i.first][i.second] = this[j.first][j.second]
        this[j.first][j.second] = temp
    }

    private fun printMatrix(matrix: Array<IntArray>) {
        matrix.forEach { row ->
            row.forEach { value ->
                if (value < 10) {
                    print(" $value ")
                } else print("$value ")
            }
            println()
        }
        println("-----")
    }
}