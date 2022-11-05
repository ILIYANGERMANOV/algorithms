import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Sum3Test : FreeSpec({
    "sum 3" - {
        withData(
            nameFn = { (arr, answer) ->
                "${arr.toList()} is ${answer.map { it.toList() }}"
            },
            row(
                intArrayOf(-1, 0, 1, 2, -1, -4), listOf(
                    listOf(-1, -1, 2),
                    listOf(-1, 0, 1)
                )
            ),
            row(
                intArrayOf(0, 1, 1), listOf()
            ),
            row(
                intArrayOf(0, 0, 0), listOf(listOf(0, 0, 0))
            )
        ) { (arr, answer) ->
            val sut = Sum3()

            val res = sut.threeSum(arr)

            res shouldBe answer
        }
    }
})