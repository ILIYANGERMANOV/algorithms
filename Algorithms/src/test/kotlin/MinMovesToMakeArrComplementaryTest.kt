import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MinMovesToMakeArrComplementaryTest : FreeSpec({
    "solve" - {
        withData(
            nameFn = { (arr, limit, moves) ->
                "${arr.toList()} with limit $limit in $moves moves"
            },
            row(intArrayOf(1, 2, 4, 3), 4, 1),
            row(intArrayOf(1, 2, 2, 1), 2, 2),
            row(intArrayOf(1, 2, 1, 2), 2, 0),
        ) { (arr, limit, moves) ->
            val sut = MinMovesToMakeArrComplementary()

            val res = sut.minMoves(arr, limit)

            res shouldBe moves
        }
    }
})