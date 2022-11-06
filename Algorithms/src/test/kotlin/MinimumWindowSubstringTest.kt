import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MinimumWindowSubstringTest : FreeSpec({
    "min substring" - {
        withData(
            nameFn = { (s, t, expected) ->
                "of \"$s\" with \"$t\" is \"$expected\""
            },
            row("ADOBECODEBANC", "ABC", "BANC"),
            row("a", "a", "a"),
            row("a", "aa", "")
        ) { (s, t, expected) ->
            val sut = MinimumWindowSubstring()

            val res = sut.minWindow(s, t)

            res shouldBe expected
        }
    }
})