import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ContainerMostWaterTest : FreeSpec({
    "most water in" - {
        withData(
            nameFn = { (height, expected) ->
                "${height.toList()} is $expected"
            },
            row(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7), 49),
            row(intArrayOf(1, 1), 1),
        ) { (height, expected) ->
            val sut = ContainerMostWater()

            val res = sut.maxArea(height)

            res shouldBe expected
        }
    }
})