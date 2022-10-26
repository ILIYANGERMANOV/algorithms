import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SuperPalindromeTest : FreeSpec({
    "superpalindromes" - {
        withData(
            nameFn = { (left, right, count) ->
                "in [$left, $right] are $count"
            },
            row("4", "14", 2),
            row("1", "25", 3),
            row("1", "100000", 10),
            row("4", "1000", 4),
            row("1", "2", 1),
            row("375259531", "1265368034085", 18),
            row("1", "1000000000000000000", 70),
        ) { (left, right, count) ->
            val sut = SuperPalindrome()

            val res = sut.superpalindromesInRange(left = left, right = right)

            res shouldBe count
        }
    }
})