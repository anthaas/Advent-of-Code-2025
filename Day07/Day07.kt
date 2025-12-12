package Day07

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()
        .map { it.toCharArray() }

    val beams = MutableList(input[0].size) { 0L }
    var result = 0L

    for (row in input) {
        for (i in 0 until row.size) {
            if (row[i] == 'S') {
                beams[i] = 1
            }
            if (row[i] == '^' && beams[i] > 0) {
                result++
                beams[i - 1] += beams[i]
                beams[i + 1] += beams[i]
                beams[i] = 0
            }
        }
    }


    println(result)
    println(beams.sum())
}

