import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MinimumAreaRectangleTest : FreeSpec({
    "minimum area rect of points" - {
        withData(
            nameFn = { (points, area) ->
                "${points.map { it.toList() }} is $area"
            },
            row(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(3, 3),
                    intArrayOf(4, 4),
                    intArrayOf(2, 1),
                    intArrayOf(1, 4),
                    intArrayOf(2, 2),
                    intArrayOf(1, 0),
                    intArrayOf(0, 2),
                ),
                0,
            ),
            row(
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(3, 2),
                    intArrayOf(4, 4),
                    intArrayOf(0, 2),
                    intArrayOf(4, 3),
                    intArrayOf(2, 4),
                    intArrayOf(4, 2),
                    intArrayOf(1, 1)
                ),
                0
            ),
            row(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(1, 3),
                    intArrayOf(3, 1),
                    intArrayOf(3, 3),
                    intArrayOf(2, 2)
                ),
                4
            ),
            row(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(1, 3),
                    intArrayOf(3, 1),
                    intArrayOf(3, 3),
                    intArrayOf(4, 1),
                    intArrayOf(4, 3)
                ),
                2
            ),
            row(
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 3),
                    intArrayOf(3, 3),
                    intArrayOf(4, 4),
                    intArrayOf(1, 4),
                    intArrayOf(2, 3),
                    intArrayOf(1, 0),
                    intArrayOf(3, 4),
                ),
                2,
            ),
            row(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(3, 1),
                    intArrayOf(4, 4),
                    intArrayOf(1, 1),
                    intArrayOf(4, 3),
                    intArrayOf(0, 3),
                    intArrayOf(0, 2),
                    intArrayOf(4, 0),
                ),
                0
            ),
        ) { (points, area) ->
            val sut = MinimumAreaRectangle()

            val res = sut.minAreaRect(points)

            res shouldBe area
        }
    }

})