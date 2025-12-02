package Day02

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readText(Charsets.UTF_8)
        .split(",")
        .map { it.split("-").let { Pair(it[0].toLong(), it[1].toLong()) } }

    var result = 0L
    for (range in input) {
        for (i in range.first..range.second) {
            val expr = i.toString()
            if (expr.length % 2 != 0) continue
            if (expr.take(expr.length / 2) == expr.takeLast(expr.length / 2)) {
                result += i
            }
        }
    }

    println(result)

    result = 0L
    for (range in input) {
        for (i in range.first..range.second) {
            val expr = i.toString()
            for (j in 1..expr.length) {
                val chunked = expr.chunked(j)
                if (chunked.size == 1) continue
                if (chunked.all { it == chunked[0] }) {
                    result += i
                    break
                }
            }
        }
    }

    println(result)

}