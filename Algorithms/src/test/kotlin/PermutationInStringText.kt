import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PermutationInStringText : FreeSpec({
    "finds permutation" - {
        withData(
            nameFn = { (target, source, found) ->
                "of \"$target\" in \"$source\" is $found"
            },
            row("12345678", "87654321", true),
            row("1234567", "7654321", true),
            row("123456", "654321", true),
            row("12345", "54321", true),
            row("abc", "acab", true),
            row("1234", "4321", true),
            row("abcde", "ebcda", true),
            row("abcd", "deba", false),
            row("rvwrk", "lznomzggwrvrkxecjaq", true),
            row("a", "back", true),
            row("ab", "eidbaooo", true),
            row("abc", "eidboaoo", false),
        ) { (target, source, found) ->
            val sut = PermutationInString()

            val res = sut.checkInclusion(target, source)

            res shouldBe found
        }
    }
})