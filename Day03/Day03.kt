package Day03

import java.io.File

fun getMaxJolts(sequence: String, length: Int, current: String): Long {
    if (length > sequence.length) {
        return -1L
    }
    if (length == 0) {
        return current.toLong()
    }
    if (length == 1) {
        val maxDigit = sequence.toCharArray().maxOfOrNull { it.digitToInt() }.toString()
        return (current + maxDigit).toLong()
    }

    for (c in "987654321".toCharArray()) {
        if (c in sequence) {
            val index = sequence.indexOf(c)
            val result = getMaxJolts(sequence.substring(index+1), length - 1, current + c)
            if (result != -1L) return result
        }
    }

    return -1L
}


fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()

    val part1 = input.sumOf { getMaxJolts(it, 2, "") }
    val part2 = input.sumOf { getMaxJolts(it, 12, "") }
    println(part1)
    println(part2)
}