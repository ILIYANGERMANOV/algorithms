import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class NextClosestTimeTest : FreeSpec({
    "next closest time" - {
        withData(
            nameFn = { (time, expected) ->
                "of \"$time\" is \"$expected\""
            },
            row("01:37", "03:00"),
            row("19:34", "19:39"),
            row("23:59", "22:22"),
            row("09:31", "09:33"),
            row("01:01", "01:10"),
            row("22:22", "22:22"),
            row("12:58", "15:11"),
        ) { (time, expected) ->
            val sut = NextClosestTime()

            val res = sut.nextClosestTime(time)

            res shouldBe expected
        }
    }
})