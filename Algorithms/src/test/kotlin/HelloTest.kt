import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class HelloTest : FreeSpec({
    "says hello" {
        val sut = Hello()

        val res = sut.solve()

        res shouldBe "Hello"
    }
})