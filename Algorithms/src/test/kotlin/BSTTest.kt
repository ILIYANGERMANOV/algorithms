import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.checkAll

class BSTTest : FreeSpec({
    val arbBST = arbitrary {
        BST.fromList(Arb.list(Arb.int()).bind())
    }

    "constructs simple tree" {
        val bst = BST.fromList(listOf(1, 2, 3, 4, 4).shuffled())
        flatten(bst) shouldBe listOf(1, 2, 3, 4, 4)
    }

    "property" - {
        "remains balanced" {
            checkAll(arbBST) { bst ->
                balanced(bst) shouldBe true
            }
        }

        "flatten is non-decreasing" {
            checkAll(arbBST) { bst ->
                val asList = flatten(bst)
                asList shouldBe asList.sorted()
            }
        }
    }
})