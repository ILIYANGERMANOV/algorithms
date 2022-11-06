import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PlusOneTest : FreeSpec({
    "plus one" - {
        withData(
            nameFn = { (digits, expected) ->
                "of ${digits.toList()} is ${expected.toList()}"
            },
            row(intArrayOf(9, 9), intArrayOf(1, 0, 0)),
            row(intArrayOf(1, 9, 9), intArrayOf(2, 0, 0)),
            row(intArrayOf(1, 2, 3), intArrayOf(1, 2, 4)),
            row(intArrayOf(4, 3, 2, 1), intArrayOf(4, 3, 2, 2)),
            row(intArrayOf(9), intArrayOf(1, 0)),
        ) { (digits, expected) ->
            val sut = PlusOne()

            val res = sut.plusOne(digits)

            res shouldBe expected
        }
    }
})