import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MissingRangesTest : FreeSpec({
    "missing ranges" - {
        withData(
            nameFn = { (nums, lower, upper, expected) ->
                "with [$lower,$upper] in \"${nums.toList()}\" are ${expected.map { "\"$it\"" }}"
            },
            row(intArrayOf(), 1, 1, listOf("1")),
            row(intArrayOf(0, 1, 3, 50, 75), 0, 99, listOf("2", "4->49", "51->74", "76->99")),
            row(intArrayOf(-1), -1, -1, emptyList()),
            row(intArrayOf(-5, -4, -3, 2, 3, 5, 6, 8, 9, 10), -10, 10, listOf("-10->-6", "-2->1", "4", "7")),
        ) { (nums, lower, upper, expected) ->
            val sut = MissingRanges()

            val res = sut.findMissingRanges(nums, lower, upper)

            res shouldBe expected
        }
    }
})