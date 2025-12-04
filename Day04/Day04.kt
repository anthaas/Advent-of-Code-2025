package Day04

import java.io.File

var rolls = listOf<CharArray>()

fun countRemovableRolls(modifyMap : Boolean):Int {
    var result = 0
    for (r in 0 until rolls.size) {
        for (c in 0 until rolls[0].size) {
            if (rolls[r][c] == '@') {
                var neigborsCount = 0
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (r + i < 0 || r + i > rolls.size - 1 || c + j < 0 || c + j > rolls.size - 1 || (i == 0 && j == 0)) continue
                        if (rolls[r + i][c + j] == '@') neigborsCount++
                    }
                }
                if (neigborsCount < 4) {
                    result++
                    if (modifyMap) rolls[r][c] = 'x'
                }
            }
        }
    }
    return result
}

fun iterate(): Int {
    var result = 0
    while (true) {
        val removed = countRemovableRolls(true)
        result += removed
        if (removed == 0) return result
    }
}

fun main(args: Array<String>) {
    rolls = File("input.txt")
        .readLines()
        .map { it.toCharArray() }


    println(countRemovableRolls(false))
    println(iterate())
}

