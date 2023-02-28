import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class WorkspaceTest : FreeSpec({
    "number of ways to make change" - {
        withData(
            nameFn = { (n, denoms, ways) ->
                "n = $n, denoms = $denoms => $ways ways"
            },
            row(1, listOf(1), 1),
            row(2, listOf(1), 1),
            row(2, listOf(1, 2), 2),
            row(3, listOf(1), 1),
            row(3, listOf(1, 2), 2),
            row(4, listOf(1, 2), 3),
            row(7, listOf(1, 5, 10), 2),
            row(6, listOf(1, 5), 2),
            row(4, listOf(1, 5, 10, 25), 1),
        ) { (n, denoms, ways) ->
            numberOfWaysToMakeChange(n = n, denoms = denoms) shouldBe ways

        }
    }
})