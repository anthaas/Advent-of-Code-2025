package Day08

import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt

fun euclidianDistance(s1: String, s2: String): Int {
    val o1 = s1.split(",").map(String::toInt)
    val o2 = s2.split(",").map(String::toInt)
    return sqrt(
        (o2[0].toDouble() - o1[0]).pow(2.0)
                + (o2[1].toDouble() - o1[1]).pow(2.0)
                + (o2[2].toDouble() - o1[2]).pow(2.0)
    ).toInt()

}

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()

    val distances = mutableListOf<Pair<Int, Pair<String, String>>>()

    for (i in 0 until input.size) {
        for (j in i + 1 until input.size) {
            val o1 = input[i]
            val o2 = input[j]
            val distance = euclidianDistance(o1, o2)
            distances.add(Pair(distance, Pair(o1, o2)))
        }
    }
    distances.sortBy { it.first }
    val circuits = mutableListOf<MutableSet<String>>()
    for (junction in input) {
        circuits.add(mutableSetOf(junction))
    }
    //part1
    var i = 0
    while (i < 1000) {
        makeConnection(distances[i].second, circuits)
        i++
    }
    circuits.sortByDescending { it.size }
    val result = circuits[0].size * circuits[1].size * circuits[2].size
    println(result)

    //part2
    while (circuits.size > 1) {
        makeConnection(distances[i].second, circuits)
        i++
    }
    val j1 = distances[i-1].second.first
    val j2 = distances[i-1].second.second
    val x1 = j1.split(",")[0].toLong()
    val x2 = j2.split(",")[0].toLong()
    println(x1 * x2)
}

fun makeConnection(junctions: Pair<String, String>, circuits: MutableList<MutableSet<String>>) {
    val j1 = junctions.first
    val j2 = junctions.second
    val i1 = findCircuit(j1, circuits)
    val i2 = findCircuit(j2, circuits)
    if (i1 != i2) {
        circuits[i1].addAll(circuits[i2])
        circuits.removeAt(i2)
    }
}

fun findCircuit(junction: String, circuits: List<Set<String>>): Int {
    for ((i, c) in circuits.withIndex()) {
        if (c.contains(junction)) {
            return i
        }
    }
    return -1
}

