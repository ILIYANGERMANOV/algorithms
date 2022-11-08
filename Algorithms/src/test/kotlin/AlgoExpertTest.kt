import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AlgoExpertTest : FreeSpec({
    "test" {
        val res = counts(
            teamA = arrayOf(1, 2, 3),
            teamB = arrayOf(2, 4),
        )

        res shouldBe arrayOf(2, 3)
    }
})