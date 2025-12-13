package Day10

import java.io.File

fun initMachine(input: String): Machine {
    var finalConfiguration = charArrayOf()
    val switches = mutableListOf<List<Int>>()
    val joltageRequirements = mutableListOf<Int>()
    val parts = input.split(" ")
    for (part in parts) {
        if (part.startsWith("[")) {
            finalConfiguration = part.substring(1, part.length - 1).toCharArray()
        }
        if (part.startsWith("(")) {
            switches.add(part.substring(1, part.length - 1).split(",").map { it.toInt() })
        }
        if (part.startsWith("{")) {
            joltageRequirements.addAll(part.substring(1, part.length - 1).split(",").map { it.toInt() })
        }
    }

    return Machine(finalConfiguration, switches, joltageRequirements)
}

fun main(args: Array<String>) {
    val input = File("input.txt")
        .readLines()
        .map { initMachine(it) }


    println(input.sumOf { it.getMinSwitchesToMatchConfig() })
    println(input.sumOf { it.getMinSwitchesToMatchJoltage() })

}



