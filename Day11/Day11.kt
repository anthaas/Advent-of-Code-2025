package Day11

import java.io.File
import kotlin.collections.forEach

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()
        .map { it.split(": ").let { Pair(it[0], it[1].split(" ")) } }
    val graph = mutableMapOf<String, List<String>>()
    input.forEach { graph[it.first] = it.second }

    val part1 = findPath("you", "out", graph, mutableMapOf())
    println(part1)

    //dac is first
    val svrToDac = findPath("svr", "dac", graph, mutableMapOf())
    val dacToFft = findPath("dac", "fft", graph, mutableMapOf())
    val fftToOut = findPath("fft", "out", graph, mutableMapOf())
    val svrToDacToFftToOut = svrToDac * dacToFft * fftToOut

    //fft is first
    val svrToFft = findPath("svr", "fft", graph, mutableMapOf())
    val fftToDac = findPath("fft", "dac", graph, mutableMapOf())
    val dacToOut = findPath("dac", "out", graph, mutableMapOf())
    val svrToFftToDacToOut = svrToFft * fftToDac * dacToOut

    val part2 = svrToDacToFftToOut + svrToFftToDacToOut
    println(part2)

}

fun findPath(current: String, target: String, graph: Map<String, List<String>>, memo: MutableMap<String, Long>): Long {
    if (current == target) return 1
    if (memo.contains(current)) return memo[current]!!
    var ways = 0L
    for (next in graph.getOrDefault(current, listOf())) {
        ways += findPath(next, target, graph, memo)
    }
    memo[current] = ways
    return ways
}

