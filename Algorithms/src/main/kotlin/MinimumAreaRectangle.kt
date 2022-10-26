import kotlin.math.pow
import kotlin.math.sqrt

typealias Point = IntArray

/**
 * Problem:
 * https://leetcode.com/problems/minimum-area-rectangle/
 */
class MinimumAreaRectangle {

    fun minAreaRect(points: Array<Point>): Int {
        var minArea: Int? = null
        chooseFourUnique(points) { a, b, c, d ->
            if (parallel(a, b, c, d) && rectangle(a, b, c, d)) {
                println("rect: ${a.toList()}, ${b.toList()}, ${c.toList()}, ${d.toList()}")
                val area = area(a, b, c, d)
                minArea = minArea?.let { minOf(it, area) } ?: area
            }
        }
        return minArea ?: 0
    }

    private inline fun chooseFourUnique(
        points: Array<Point>,
        block: (Point, Point, Point, Point) -> Unit
    ) {
        for (a in points) {
            for (b in points) {
                if (a sameAs b) continue
                for (c in points) {
                    if (a sameAs c || b sameAs c) continue
                    for (d in points) {
                        if (a sameAs d || b sameAs d || c sameAs d) continue
                        block(a, b, c, d)
                    }
                }
            }
        }
    }

    private infix fun Point.sameAs(other: Point): Boolean =
        x() == other.x() && y() == other.y()

    private fun area(
        a: Point, b: Point,
        c: Point, d: Point
    ): Int {
        val minX = minOf(a.x(), b.x(), c.x(), d.x())
        val maxX = maxOf(a.x(), b.x(), c.x(), d.x())
        val minY = minOf(a.y(), b.y(), c.y(), d.y())
        val maxY = maxOf(a.y(), b.y(), c.y(), d.y())
        return (maxX - minX) * (maxY - minY)
    }

    private fun parallel(
        a: Point, b: Point,
        c: Point, d: Point,
    ): Boolean {
        val points = arrayOf(a, b, c, d)
        if (points.map { it.x() }.toSet().size > 2) return false
        if (points.map { it.y() }.toSet().size > 2) return false
        return true
    }

    /**
     * Conditions for rectangle:
     * - distance(a,b) == distance(c,d)
     * - distance(a,c) == distance(b,d)
     * - distance(a,d) == distance(b,c)
     *
     * @return whether four points form an rectangle, note: it might be rotated
     */
    private fun rectangle(
        a: Point, b: Point,
        c: Point, d: Point
    ): Boolean = distance(a, b) == distance(c, d) &&
            distance(a, c) == distance(b, d) &&
            distance(a, d) == distance(b, c)

    /**
     * Formula:
     * d=√((x2 – x1)² + (y2 – y1)²)
     */
    private fun distance(p1: Point, p2: Point): Double = sqrt(
        (p1.x().toDouble() - p2.x()).pow(2.0) +
                (p1.y().toDouble() - p2.y()).pow(2.0)
    )

    private fun IntArray.x() = this[0]

    private fun IntArray.y() = this[1]
}