import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class WorkspaceTest : FreeSpec({
    "n = 6, denoms = [1,5]" {
        numberOfWaysToMakeChange(target = 6, denoms = listOf(1, 5)) shouldBe 2
    }

    "n = 10, denoms = [1,2,6]" {
        numberOfWaysToMakeChange(target = 10, denoms = listOf(1, 2, 6)) shouldBe 6
    }

    "n = 4, denoms = [1, 5, 10, 25]" {
        numberOfWaysToMakeChange(target = 4, listOf(1, 5, 10, 25)) shouldBe 1
    }
})