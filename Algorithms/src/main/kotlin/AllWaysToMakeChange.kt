fun numberOfWaysToMakeChange(n: Int, denoms: List<Int>): Int {
    // Write your code here.
    println("----------- CASE: n = $n, denoms = $denoms ----------------")
    val allWays = ways(
        n = n,
        ds = denoms,
    )
    println("All ways: $allWays")
    val uniqueWays = allWays.map { it.sorted() }.toSet()
    println("Unique ways: $uniqueWays")
    println("------------- END CASE ---------------")
    return uniqueWays.size
}

/*
    n = 3, denoms = [1,2]
    ways(3) = ways(1) + ways(2)
 */
fun ways(
    n: Int,
    ds: List<Int>,
    ways: List<Int> = emptyList()
): List<List<Int>> {
    if (n < 0) return emptyList()
    if (n == 0) return listOf(ways)

    val validDs = ds.filter { it <= n }
    return validDs.foldRight(initial = emptyList()) { d, allWays ->
        ways(n = n - d, ways = ways + d, ds = validDs) + allWays
    }
}

