package Day10

import kotlin.math.min

class Machine(val finalConfiguration: CharArray, val switches: List<List<Int>>, val joltageRequirements: List<Int>) {

    fun getMinSwitchesToMatchConfig(): Int {
        return getMinSwitchesToMatchConfig(finalConfiguration, mutableSetOf())
    }

    fun getMinSwitchesToMatchConfig(targetCombination: CharArray, combinations: MutableSet<String>): Int {
        var min = Int.MAX_VALUE
        for (c in generateCombinations(switches.size, "")) {
            val currentCombination = c.toCharArray()
            val numOfSwitches = currentCombination.count { it == '1' }
            val configuration = ".".repeat(targetCombination.size).toCharArray()
            for ((i, e) in switches.withIndex()) {
                if ('1' == currentCombination[i]) {
                    for (pos in e) {
                        if (configuration[pos] == '.') {
                            configuration[pos] = '#'
                        } else if (configuration[pos] == '#') {
                            configuration[pos] = '.'
                        }

                    }
                }
            }
            if (configuration.joinToString("") == targetCombination.joinToString("")) {
                combinations.add(c)
                if (numOfSwitches < min) {
                    min = numOfSwitches
                }
            }
        }

        return min
    }

    fun getMinSwitchesToMatchJoltage(): Int {
        //https://www.reddit.com/r/adventofcode/comments/1pk87hl/2025_day_10_part_2_bifurcate_your_way_to_victory/
        return getMinSwitchesToMatchJoltageRecur(joltageRequirements)
    }

    fun getMinSwitchesToMatchJoltageRecur(currentJoltageRequirements: List<Int>): Int {
        var min = Int.MAX_VALUE

        if (currentJoltageRequirements.all { it == 0 }) {
            return 0
        }

        val lightsByJoltage = currentJoltageRequirements.map { if (it % 2 == 0) '.' else '#' }.toCharArray()
        var combinations = mutableSetOf<String>()
        getMinSwitchesToMatchConfig(lightsByJoltage, combinations)
        if (combinations.isEmpty()) {
            return min
        }
        combinations = combinations.sortedBy { it.count { it == '1' } }.toMutableSet()

        for (combination in combinations) {
            val remainingJoltage = currentJoltageRequirements.toMutableList()
            val currentCombination = combination.toCharArray()
            val presses = currentCombination.count { it == '1' }
            for ((i, e) in currentCombination.withIndex()) {
                if ('1' == e) {
                    for (pos in switches[i]) {
                        remainingJoltage[pos]--
                    }
                }
            }

            if (remainingJoltage.any { it < 0 }) {
                continue
            }

            val result = getMinSwitchesToMatchJoltageRecur(remainingJoltage.map { it / 2 })
            if (result == Int.MAX_VALUE) {
                continue
            }
            min = min(min, presses + 2 * result)
        }

        return min
    }

    fun generateCombinations(n: Int, curr: String): List<String> {
        val res = mutableListOf<String>()
        if (n > 0) {
            res.addAll(generateCombinations(n - 1, "0" + curr))
            res.addAll(generateCombinations(n - 1, "1" + curr))
        } else {
            res.add(curr)
        }

        return res
    }
}