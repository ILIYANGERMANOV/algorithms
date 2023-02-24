fun numberOfWaysToMakeChange(target: Int, denoms: List<Int>): Int {
    // Write your code here.
    if (target == 0) return 1
    return solve(target, denoms.sortedDescending(), n = 0)
}

// n = 10, [6, 2, 1]
private fun solve(
    target: Int,
    coins: List<Int>,
    n: Int
): Int {
    if (coins.isEmpty()) return n

    val first = coins.first()

    val newN = if (first <= target) {
        val remainder = target % first
        solve(
            target = remainder,
            coins = coins.drop(1),
            n = if (remainder == 0) n + 1 else n
        )
    } else n
    return solve(
        target = target,
        coins = coins.drop(1),
        n = newN
    )
}