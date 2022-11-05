import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class RotateImageTest : FreeSpec({
    "rotate" - {
        withData(
            row(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16),
                ),
                arrayOf(
                    intArrayOf(13, 9, 5, 1),
                    intArrayOf(14, 10, 6, 2),
                    intArrayOf(15, 11, 7, 3),
                    intArrayOf(16, 12, 8, 4)
                )
            ),
            row(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9)
                ),
                arrayOf(
                    intArrayOf(7, 4, 1),
                    intArrayOf(8, 5, 2),
                    intArrayOf(9, 6, 3)
                )
            ),
        ) { (matrix, expected) ->
            val sut = RotateImage()

            sut.rotate(matrix)

            matrix shouldBe expected
        }
    }
})