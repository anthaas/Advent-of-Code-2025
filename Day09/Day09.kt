package Day09

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()
        .map { it.split(",").let { Pair(it[0].toLong(), it[1].toLong()) } }

    println(getMaxArea(makePairs(input)))

    val segments = getSegments(input)
    val points = makePairs(input).filter { (a, b) -> !intersects(a, b, segments) }
    println(getMaxArea(points))

}

fun getMaxArea(points: List<Pair<Pair<Long, Long>, Pair<Long, Long>>>): Long {
    var maxArea = 0L
    for (pair in points) {
        val (a, b) = pair
        val area = (abs(a.first - b.first) + 1) * (abs(a.second - b.second) + 1)
        if (area > maxArea) {
            maxArea = area
        }
    }
    return maxArea
}

fun makePairs(points: List<Pair<Long, Long>>): List<Pair<Pair<Long, Long>, Pair<Long, Long>>> {
    val result = mutableListOf<Pair<Pair<Long, Long>, Pair<Long, Long>>>()
    for (a in points) {
        for (b in points) {
            result.add(Pair(a, b))
        }
    }
    return result
}

fun getSegments(points: List<Pair<Long, Long>>): List<Pair<Pair<Long, Long>, Pair<Long, Long>>> {
    val result = mutableListOf<Pair<Pair<Long, Long>, Pair<Long, Long>>>()
    for (a in points) {
        for (b in points) {
            result.add(a to b)
        }
    }
    return result
}


fun intersects(
    a: Pair<Long, Long>,
    b: Pair<Long, Long>,
    segments: List<Pair<Pair<Long, Long>, Pair<Long, Long>>>
): Boolean {
    val minX = min(a.first, b.first)
    val maxX = max(a.first, b.first)
    val minY = min(a.second, b.second)
    val maxY = max(a.second, b.second)

    for (seg in segments) {
        val (s, e) = seg
        if (s.first == e.first && s.first in (minX + 1)..<maxX && maxOf(
                minOf(s.second, e.second),
                minY
            ) < minOf(
                maxOf(s.second, e.second),
                maxY
            ) || s.second == e.second && s.second in (minY + 1)..<maxY && maxOf(minOf(s.first, e.first), minX) < minOf(
                maxOf(s.first, e.first),
                maxX
            )
        ) {
            return true
        }
    }

    return false
}
