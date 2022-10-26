import kotlin.math.abs

/**
 * Problem:
 * https://leetcode.com/problems/minimum-area-rectangle/
 */
class MinimumAreaRectangle {

    data class Point(
        val x: Int,
        val y: Int
    )

    data class Line(
        val start: Point,
        val end: Point
    )

    data class Rect(
        val bottomLeft: Point,
        val topRight: Point
    )

    fun minAreaRect(pointsArr: Array<IntArray>): Int {
        val points = pointsArr.map {
            Point(x = it[0], y = it[1])
        }

        println("Case $points -------------")
        // it[0] = x; it[1] = y;
        val horizontalLines = points.groupBy { it.y }.filterValues { it.size >= 2 }
            .mapValues { (y, points) ->
                val lines = mutableListOf<Line>()
                for (p1 in points) {
                    for (p2 in points) {
                        if (p1 == p2) continue
                        val lineAdded = lines.any {
                            it.start.x == p1.x && it.end.x == p2.x ||
                                    it.start.x == p2.x && it.end.x == p1.x
                        }
                        if (lineAdded) continue
                        val newLine = Line(
                            start = Point(
                                x = p1.x,
                                y = y
                            ),
                            end = Point(
                                x = p2.x,
                                y = y
                            )
                        )
                        lines.add(newLine)
                    }
                }
                lines
            }.values.flatten()
        val verticalLines = points.groupBy { it.x }.filterValues { it.size >= 2 }
            .mapValues { (x, points) ->
                val lines = mutableListOf<Line>()
                for (p1 in points) {
                    for (p2 in points) {
                        if (p1 == p2) continue
                        val lineAdded = lines.any {
                            it.start.y == p1.y && it.end.y == p2.y ||
                                    it.start.y == p2.y && it.end.y == p1.y
                        }
                        if (lineAdded) continue
                        val newLine = Line(
                            start = Point(
                                x = x,
                                y = p1.y
                            ),
                            end = Point(
                                x = x,
                                y = p2.y
                            )
                        )
                        lines.add(newLine)
                    }
                }
                lines
            }.values.flatten()
        println("horizontal = $horizontalLines")
        println("vertical = $verticalLines")

        val rectangles = mutableSetOf<Rect>()
        for (h1 in horizontalLines) {
            for (h2 in horizontalLines) {
                if (h1 == h2) continue
                for (v1 in verticalLines) {
                    for (v2 in verticalLines) {
                        if (v1 == v2) continue
                        if (
                            connected(h1, v1) &&
                            connected(h1, v2) &&
                            connected(h2, v1) &&
                            connected(h2, v2)
                        ) {
                            val topRight = if (v1.start.x > v2.start.x) {
                                // v1 is right
                                Point(
                                    x = v1.start.x,
                                    y = maxOf(v1.start.y, v1.end.y)
                                )
                            } else {
                                // v2 is right
                                Point(
                                    x = v2.start.x,
                                    y = maxOf(v2.start.y, v2.end.y)
                                )
                            }
                            val bottomLeft = if (h1.start.y < h2.start.y) {
                                // h1 is bottom
                                Point(
                                    x = minOf(h1.start.x, h1.end.x),
                                    y = h1.start.y
                                )
                            } else {
                                // h2 is bottom
                                Point(
                                    x = minOf(h2.start.x, h2.end.x),
                                    y = h2.start.y
                                )
                            }
                            if (bottomLeft.x == topRight.x) continue
                            if (bottomLeft.y == topRight.y) continue

                            // validate that points exists
                            if (
                                !points.any { it.x == bottomLeft.x && it.y == topRight.y }
                            ) continue
                            if (
                                !points.any { it.x == topRight.x && it.y == bottomLeft.y }
                            ) continue


                            rectangles.add(
                                Rect(
                                    bottomLeft = bottomLeft,
                                    topRight = topRight
                                )
                            )
                        }
                    }
                }
            }
        }

        println("rectangles $rectangles")
        return rectangles.myMinOf { it.area() } ?: 0
    }

    private fun connected(hor: Line, ver: Line): Boolean = when {
        hor.start == ver.start ||
                hor.start == ver.end ||
                hor.end == ver.start ||
                hor.end == ver.end -> true
        else -> false
    }

    private fun Rect.area(): Int =
        abs(bottomLeft.x - topRight.x) * abs(bottomLeft.y - topRight.y)

    private fun Set<Rect>.myMinOf(selector: (Rect) -> Int): Int? {
        if (this.isEmpty()) return null
        var min = selector(first())
        val n = this.size
        for (i in 1 until n) {
            min = minOf(min, selector(this.elementAt(i)))
        }
        return min
    }
}