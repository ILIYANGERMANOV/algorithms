import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class AddStringsTest : FreeSpec({
    "adds" - {
        withData(
            nameFn = { (num1, num2, answer) ->
                "$num1 + $num2 = $answer"
            },
            row("9", "99", "108"),
            row("11", "123", "134"),
            row("9", "9", "18"),
            row("5", "4", "9"),
            row("0", "0", "0"),
            row("100", "0", "100"),
            row("123", "456", "579"),
            row("999", "999", "1998"),
        ) { (num1, num2, answer) ->
            val sut = AddStrings()

            val res = sut.addStrings(num1, num2)

            res shouldBe answer
        }
    }
})