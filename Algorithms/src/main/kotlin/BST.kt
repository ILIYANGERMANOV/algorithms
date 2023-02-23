import kotlin.math.abs


sealed interface BST<out A : Comparable<*>> {
    companion object {
        fun <A : Comparable<A>> empty(): BST<A> = None

        fun <A : Comparable<A>> fromList(collection: Collection<A>): BST<A> =
            collection.fold(initial = empty()) { acc, a ->
                insert(a, acc)
            }
    }

    object None : BST<Nothing>
    data class Node<A : Comparable<*>>(
        val height: Int,
        val left: BST<A>,
        val value: A,
        val right: BST<A>,
    ) : BST<A>
}

fun <A : Comparable<A>> flatten(bst: BST<A>): List<A> = when (bst) {
    BST.None -> emptyList()
    is BST.Node -> flatten(bst.left) + listOf(bst.value) + flatten(bst.right)
}

fun <A : Comparable<A>> insert(x: A, bst: BST<A>): BST<A> {
    return when (bst) {
        BST.None -> node(left = BST.None, value = x, right = BST.None)
        is BST.Node -> {
            balanceStep(
                when {
                    x >= bst.value -> {
                        // insert right
                        when (bst.right) {
                            BST.None -> bst.copy(
                                right = node(value = x)
                            )
                            is BST.Node -> bst.copy(
                                right = insert(x, bst.right)
                            )
                        }
                    }
                    // x < bst.value
                    else -> {
                        // insert left
                        when (bst.left) {
                            BST.None -> bst.copy(
                                left = node(value = x)
                            )
                            is BST.Node -> bst.copy(
                                left = insert(x, bst.left)
                            )
                        }
                    }
                }
            )
        }
    }
}

fun <A : Comparable<A>> node(
    left: BST<A> = BST.None,
    value: A,
    right: BST<A> = BST.None
): BST<A> = BST.Node(
    height = 1 + maxOf(height(left), height(right)),
    left = left,
    value = value,
    right = right,
)

fun <A : Comparable<A>> balanceStep(bst: BST<A>): BST<A> {
    return when (bst) {
        BST.None -> bst
        is BST.Node -> {
            val leftHeight = height(bst.left)
            val rightHeight = height(bst.right)

            when {
                rightHeight > leftHeight + 1 -> {
                    /*
                          a
                         / \
                        b   c
                             \
                              d
                               \
                                e
                     */
                    rotateLeft(bst)
                }
                leftHeight > rightHeight + 1 -> {
                    /*
                            a
                           / \
                          b   e
                         /
                        c
                       /
                      d
                     */
                    rotateRight(bst)
                }
                else -> bst
            }
        }
    }
}

fun <A : Comparable<A>> balanced(bst: BST<A>): Boolean = when (bst) {
    is BST.Node -> abs(height(bst.left) - height(bst.right)) <= 1
    BST.None -> true
}

fun <A : Comparable<A>> height(bst: BST<A>): Int = when (bst) {
    BST.None -> 0
    is BST.Node -> bst.height
}

/**
 *
 * Before:
 * ```
 *          9*
 *        /     \         1)
 *       6      13
 *      / \    /  \       2)
 *     4  7   11  14
 *    /    \       \      3)
 *   2     8        15
 *  /                     4)
 * 1
 * ```
 *
 * After:
 * ```
 *         6
 *       /   \              1)
 *      4      9*
 *     /     /   \          2)
 *    2     7    13
 *   /       \   / \        3)
 *  1        8  11 14
 *                  \       4)
 *                  15
 * ```
 */
fun <A : Comparable<A>> rotateRight(bst: BST<A>): BST<A> {
    return when (bst) {
        is BST.Node -> {
            if (bst.left is BST.None) return bst
            val left = bst.left as BST.Node

            left.copy(
                height = left.height + 1, // goes up
                right = bst.copy(
                    height = bst.height - 1, // goes down
                    left = left.right
                )
            )
        }
        BST.None -> bst
    }
}

/**
 * Before:
 * ```
 *      5*
 *     / \             1)
 *    3   7
 *       / \           2)
 *      6   8
 * ```
 *
 * After:
 * ```
 *       7
 *      / \        1)
 *     5*  8
 *    / \          2)
 *   3   6
 * ```
 */
fun <A : Comparable<A>> rotateLeft(bst: BST<A>): BST<A> {
    return when (bst) {
        is BST.Node -> {
            if (bst.right is BST.None) return bst
            val right = bst.right as BST.Node

            right.copy(
                height = right.height + 1, // goes up
                left = bst.copy(
                    height = bst.height - 1, // goes down
                    right = right.left
                )
            )
        }
        BST.None -> bst
    }
}