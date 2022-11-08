fun counts(teamA: Array<Int>, teamB: Array<Int>): Array<Int> {
    teamA.sort()
    val aSize = teamA.size
    val bSize = teamB.size

    val cache = mutableMapOf<Int, Int>()

    for (i in 0 until bSize) {
        val goalsB = teamB[i]
        val answer = cache[goalsB] ?: search(
            teamA = teamA,
            teamBGoals = goalsB,
            latestIndex = null,
            left = 0,
            right = aSize - 1
        )?.plus(1) ?: 0
        cache[goalsB] = answer
        teamB[i] = answer
    }

    return teamB
}

// teamA = [1, 2, 3, 4, 5]
// teamB = [1, 6]

private fun search(
    teamA: Array<Int>,
    teamBGoals: Int,
    latestIndex: Int?,
    left: Int,
    right: Int
): Int? {
    if (left > right) return latestIndex

    val mid = (left + right) / 2
    val aGoals = teamA[mid]

    return if (teamBGoals >= aGoals) {
        // search right
        search(
            teamA = teamA,
            teamBGoals = teamBGoals,
            latestIndex = mid,
            left = mid + 1,
            right = right
        )
    } else {
        // team B scored less
        search(
            teamA = teamA,
            teamBGoals = teamBGoals,
            latestIndex = mid.takeIf { latestIndex != null },
            left = 0,
            right = mid - 1
        )
    }
}