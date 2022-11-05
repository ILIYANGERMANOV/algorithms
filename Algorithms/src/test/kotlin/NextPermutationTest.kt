import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class NextPermutationTest : FreeSpec({
    "next permutation" - {
        withData(
            nameFn = { (nums, expected) ->
                "of ${nums.toList()} is expected to be ${expected.toList()}"
            },
            row(intArrayOf(5, 4, 7, 5, 3, 2), intArrayOf(5, 5, 2, 3, 4, 7)),
            row(intArrayOf(1, 3, 2), intArrayOf(2, 1, 3)),
            row(intArrayOf(2, 3, 1), intArrayOf(3, 1, 2)),
            row(intArrayOf(1, 2, 3), intArrayOf(1, 3, 2)),
            row(intArrayOf(3, 2, 1), intArrayOf(1, 2, 3)),
            row(intArrayOf(1, 1, 5), intArrayOf(1, 5, 1)),
            row(intArrayOf(1), intArrayOf(1))
        ) { (nums, expected) ->
            val sut = NextPermutation()

            sut.nextPermutation(nums)

            nums shouldBe expected
        }
    }
})