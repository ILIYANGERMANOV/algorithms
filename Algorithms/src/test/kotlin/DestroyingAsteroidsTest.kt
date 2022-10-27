import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DestroyingAsteroidsTest : FreeSpec({
    "solve" - {
        withData(
            nameFn = { (mass, asteroids, expected) ->
                "mass $mass vs ${asteroids.toList()} is $expected"
            },
            row(5, intArrayOf(4, 9, 23, 4), false),
            row(10, intArrayOf(3, 9, 19, 5, 21), true),
        ) { (mass, asteroids, expected) ->
            val sut = DestroyingAsteroids()

            val res = sut.asteroidsDestroyed(mass, asteroids)

            res shouldBe expected
        }
    }
})