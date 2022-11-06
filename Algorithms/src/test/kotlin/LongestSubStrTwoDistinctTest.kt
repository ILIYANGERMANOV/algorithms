import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LongestSubStrTwoDistinctTest : FreeSpec({
    "longest 2 char substring" - {
        withData(
            nameFn = { (s, count) ->
                "\"$s\" is $count"
            },
            row("aaaa", 4),
            row("ccaabbb", 5),
            row("aabce", 3),
            row("eceba", 3),
        ) { (s, count) ->
            val sut = LongestSubStrTwoDistinct()

            val res = sut.lengthOfLongestSubstringTwoDistinct(s)

            res shouldBe count
        }
    }
})