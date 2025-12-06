package Day06

import java.io.File
import kotlin.collections.take

fun transpose(xs: List<List<String>>): List<List<String>> {
    val cols = xs[0].size
    val rows = xs.size
    return List(cols) { j ->
        List(rows) { i ->
            xs[i][j]
        }
    }
}

fun compute(sequence: List<String>, operation: String): Long {
    val numbers = sequence.map { it.toLong() }
    if (operation == "*") {
        return numbers.reduce { a, b -> a * b }
    }
    if (operation == "+") {
        return numbers.sum()
    }
    return 0L
}

fun part2(input: List<List<String>>): Long {
    val operations = mutableListOf<String>()
    val sequences = mutableListOf<List<String>>()
    var sequence = mutableListOf<String>()
    for (row in input) {
        val r = row.joinToString("").trim()
        if (r.isEmpty()) continue
        if (r.last().isDigit()) {
            sequence.add(r)
        } else {
            operations.add(r.last().toString())
            sequence.add(r.take(r.length - 1).trim())
            sequences.add(sequence)
            sequence = mutableListOf()
        }
    }

    var result = 0L
    for (i in 0..sequences.size - 1) {
        result += compute(sequences[i], operations[i])
    }

    return result
}

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()

    val processedInput = transpose(input.map { it.trim().replace("\\s+".toRegex(), " ").split(" ").map { it.trim() } })
    val result = processedInput.sumOf { compute(it.take(it.size - 1), it.last()) }
    println(result)

    val transposedInput = transpose(input.map { it.toCharArray().map { it.toString() }.reversed() })
    println(part2(transposedInput))

}

