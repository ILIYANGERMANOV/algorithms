import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MultiplyStringsTest : FreeSpec({
    "multiplying" - {
        withData(
            nameFn = { (num1, num2, result) ->
                "$num1 * $num2 = $result"
            },
            row("123", "456", "56088"),
            row("2", "3", "6"),
            row("12", "10", "120"),
            row("15", "26", "390"),
            row("9133", "0", "0")
        ) { (num1, num2, result) ->
            val sut = MultiplyStrings()

            val res = sut.multiply(num1, num2)

            res shouldBe result
        }
    }
})