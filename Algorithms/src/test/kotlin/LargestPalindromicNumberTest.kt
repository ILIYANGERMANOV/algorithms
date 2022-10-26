import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LargestPalindromicNumberTest : FreeSpec({
    "largest palindromic number" - {
        withData(
            nameFn = { (input, output) ->
                "of \"$input\" is $output"
            },
            row("444947137", "7449447"),
            row("00009", "9"),
            row("00001105", "1005001"),
            row("00000", "0"),
            row("97231404236749078329522372611037933", "99776433322210801222333467799"),
        ) { (input, output) ->
            val sut = LargestPalindromicNumber()

            val res = sut.largestPalindromic(input)

            res shouldBe output
        }
    }
})