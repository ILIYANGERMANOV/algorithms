import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

/**
 * # Search a 2D Matrix
 * Problem:
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
class Search2DMatrixTest : FreeSpec({
    "search" - {
        withData(
            nameFn = { (matrix, target, expected) ->
                "$target in ${matrix.map { it.toList() }} is $expected"
            },
            row(
                arrayOf(
                    intArrayOf(-8, -7, -6, -6, -6),
                    intArrayOf(-5, -4, -3, -1, -1),
                    intArrayOf(0, 0, 1, 3, 3),
                    intArrayOf(5, 5, 6, 6, 6),
                    intArrayOf(7, 8, 8, 10, 12),
                    intArrayOf(13, 15, 17, 17, 18),
                    intArrayOf(20, 20, 20, 20, 20),
                    intArrayOf(22, 22, 22, 22, 23)
                ),
                4, false
            ),
            row(
                arrayOf(
                    intArrayOf(1, 3, 5, 7),
                    intArrayOf(10, 11, 16, 20),
                    intArrayOf(23, 30, 34, 50)
                ),
                20, true
            ),
            row(
                arrayOf(intArrayOf(1), intArrayOf(3), intArrayOf(5)),
                4, false,
            ),
            row(
                arrayOf(intArrayOf(1), intArrayOf(3)),
                4, false,
            ),
            row(
                arrayOf(intArrayOf(1)),
                2, false
            ),
            row(
                arrayOf(
                    intArrayOf(1, 3, 5, 7),
                    intArrayOf(10, 11, 16, 20),
                    intArrayOf(23, 30, 34, 60)
                ), 3, true
            ),
            row(
                arrayOf(
                    intArrayOf(1, 3, 5, 7),
                    intArrayOf(10, 11, 16, 20),
                    intArrayOf(23, 30, 34, 60),
                ), 13, false
            )
        ) { (matrix, target, expected) ->
            val sut = Search2DMatrix()

            val res = sut.searchMatrix(matrix, target)

            res shouldBe expected
        }
    }
})