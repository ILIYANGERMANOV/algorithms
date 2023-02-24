import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.engine.test.logging.info
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll

@OptIn(ExperimentalKotest::class)
class BSTTest : FreeSpec({

    val arbBST = arbitrary {
        BST.fromList(Arb.list(Arb.int()).bind())
    }

    "constructs simple tree" {
        val input = listOf(1, 2, 3, 4, 4).shuffled()

        val bst = BST.fromList(input)

        flatten(bst) shouldBe listOf(1, 2, 3, 4, 4)
        height(bst) shouldBe heightNoCache(bst)
        height(bst) shouldBe 3
    }

    "removes node" {
        val bst = BST.fromList(listOf(1, 2, 3, 4, 4).shuffled())

        val res = remove(3, bst)

        flatten(res) shouldBe listOf(1, 2, 4, 4)
        height(bst) shouldBe heightNoCache(bst)
        height(res) shouldBe 3
    }

    "property" - {
        "remains balanced" {
            checkAll(arbBST) { bst ->
                if (bst is BST.Node) {
                    println("BST: left = ${height(bst.left)}, right = ${height(bst.right)}")
                }

                balanced(bst) shouldBe true
                height(bst) shouldBe heightNoCache(bst)
            }
        }

        "flatten is non-decreasing" {
            checkAll(arbBST) { bst ->
                val asList = flatten(bst)
                asList shouldBe asList.sorted()
                height(bst) shouldBe heightNoCache(bst)
            }
        }

        "remove & add behaves like sorted mutable list" {
            checkAll(Arb.list(Arb.enum<BSTOperation>(), 80..200)) { ops ->
                // Arrange
                val list = mutableListOf<Int>()
                var bst = BST.empty<Int>()

                ops.forEach { op ->
                    when (op) {
                        BSTOperation.Insert -> {
                            val randomInt = Arb.int().next()
                            list.add(randomInt)
                            // Act
                            bst = insert(randomInt, bst)
                        }
                        BSTOperation.RemoveExisting -> {
                            val randomExisting = list.randomOrNull()
                            if (randomExisting != null) {
                                list.remove(randomExisting)
                                // Act
                                bst = remove(randomExisting, bst)
                            }
                        }
                        BSTOperation.RemoveNonExisting -> {
                            // might exist in some cases but we don't care
                            val randomInt = Arb.int().next()
                            list.remove(randomInt)
                            // Act
                            bst = remove(randomInt, bst)
                        }
                    }
                }

                // Assert
                val expected = list.sorted()
                info {
                    "Size ${expected.size}\n$expected"
                }
                flatten(bst) shouldBe expected
                height(bst) shouldBe heightNoCache(bst)
            }
        }
    }
})


fun <A : Comparable<A>> heightNoCache(bst: BST<A>): Int = when (bst) {
    BST.None -> 0
    is BST.Node -> 1 + maxOf(heightNoCache(bst.left), heightNoCache(bst.right))
}

enum class BSTOperation {
    Insert,
    RemoveExisting,
    RemoveNonExisting
}