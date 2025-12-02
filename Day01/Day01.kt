package Day01

import java.io.File
import kotlin.math.absoluteValue

fun part1(input: List<Int>, position: Int): Int {
    var zeros = 0
    var dial = position

    input.forEach { rotation ->
        dial = (dial + rotation) % 100
        if (dial == 0) zeros++
    }

    return zeros
}

fun part2(input: List<Int>, position: Int): Int {
    var zeros = 0
    var dial = position

    input.forEach { rotation ->
        val next = dial + rotation
        zeros += next.div(100).absoluteValue
        if (next == 0 || (next < 0 && dial > 0) || (next > 0 && dial < 0)) zeros++
        dial = (100 + next) % 100
    }

    return zeros
}

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()
        .map { Pair(it[0], it.substring(1).toInt()) }
        .map { if (it.first == 'R') it.second else -it.second }

    println(part1(input, 50))
    println(part2(input, 50))
}