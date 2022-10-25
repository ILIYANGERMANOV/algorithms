import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MaxSubArrayTest : FreeSpec({
    "largest sum of" - {
        withData(
            nameFn = { (input, sum) ->
                "${input.toList()} should be $sum"
            },
            row(intArrayOf(2, 1, -5, 4), 4),
            row(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6),
            row(intArrayOf(1,2,3,4), 10),
            row(intArrayOf(1), 1),
            row(intArrayOf(5, 4, -1, 7, 8), 23),
            row(intArrayOf(-2,-1), -1),
            row(intArrayOf(1,2,-1,-2,2,1,-2,1,4,-5,4), 6)
        ) { (input, output) ->
            val sut = MaxSubArray()

            val res = sut.maxSubArray(input)

            res shouldBe output
        }
    }


})