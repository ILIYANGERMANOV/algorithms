import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LongestSubstringWoRepsTest : FreeSpec({
    "longest substring w/o reps" - {
        withData(
            nameFn = { (str, len) ->
                "in \"$str\" is $len"
            },
            row(" ", 1),
            row("abcabcbb", 3),
            row("bbbbb", 1),
            row("pwwkew", 3),
            row("", 0),
            row("ab degz", 7),
            row("dvdf", 3),
        ) { (str, len) ->
            val sut = LongestSubstringWoReps()

            val res = sut.lengthOfLongestSubstring(str)

            res shouldBe len
        }
    }
})