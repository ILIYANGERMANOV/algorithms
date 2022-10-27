import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FruitIntoBasketsTest : FreeSpec({
    "maximum picked fruits" - {
        withData(
            nameFn = { (fruits, picked) ->
                "from ${fruits.toList()} are $picked fruits"
            },
            row(intArrayOf(0, 1, 6, 6, 4, 4, 6), 5),
            row(intArrayOf(0, 1, 2, 2), 3),
            row(intArrayOf(1, 2, 1), 3),
            row(intArrayOf(1, 2, 3, 2, 2), 4),
        ) { (fruits, picked) ->
            val sut = FruitIntoBaskets()

            val res = sut.totalFruit(fruits)

            res shouldBe picked
        }
    }
})