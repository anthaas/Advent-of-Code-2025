package Day05

import java.io.File
import kotlin.math.max

fun isFresh(input: Long, ranges: List<Pair<Long, Long>>): Boolean {
    ranges.forEach { if (input >= it.first && input <= it.second) return true }
    return false
}

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readText(Charsets.UTF_8)
        .split("\n\n")

    val ranges = input[0].split("\n")
        .map {
            it.split("-")
                .let { Pair(it[0].toLong(), it[1].toLong()) }
        }
        .sortedBy { it.first }
    val ingredients = input[1].split("\n").map { it.toLong() }
    val result1 = ingredients.count { isFresh(it, ranges) }
    println(result1)

    val merged = mutableListOf<Pair<Long, Long>>()
    var currentLow = ranges.first().first
    var currentHigh = ranges.first().second
    for (range in ranges.drop(1)) {
        val newLow = range.first
        val newHigh = range.second
        if (newLow <= currentHigh + 1) {
            currentHigh = max(currentHigh, newHigh)
        } else {
            merged.add(Pair(currentLow, currentHigh))
            currentLow = newLow
            currentHigh = newHigh
        }
    }
    merged.add(Pair(currentLow, currentHigh))

    val result2 = merged.sumOf { it.second - it.first + 1 }
    println(result2)


}

