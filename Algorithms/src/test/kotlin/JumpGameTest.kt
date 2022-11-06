import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class JumpGameTest : FreeSpec({
    "can jump" - {
        withData(
            nameFn = { (nums, expected) ->
                "${nums.toList()} jumped is $expected"
            },
            row(intArrayOf(2, 0, 1, 0, 1), false),
            row(intArrayOf(1, 1, 1, 0), true),
            row(intArrayOf(2, 3, 1, 1, 4), true),
            row(intArrayOf(3, 2, 1, 0, 4), false),
            row(intArrayOf(0), true),
        ) { (nums, expected) ->
            val sut = JumpGame()

            val res = sut.canJump(nums)

            res shouldBe expected
        }
    }
})