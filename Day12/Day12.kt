package Day12

import java.io.File
import kotlin.collections.forEach

val presents = mutableMapOf<Int, Present>()
val regions = mutableListOf<Region>()

fun main(args: Array<String>) {
    File("input.txt")
        .readText(Charsets.UTF_8)
        .split("\n\n")
        .forEach { parseInput(it) }

    val result = regions.map { canPresentsFit(it) }.count { it }
    println(result)
}

fun canPresentsFit(region: Region): Boolean {
    val regionArea = region.getArea()
    val minAreaNeeded = 9 * region.presentQuantity.sum()
    return regionArea >= minAreaNeeded
}

fun parseInput(part: String) {
    if (part.contains("x")) {
        parseRegions(part)
    } else {
        parseShape(part)
    }
}

fun parseRegions(part: String) {
    part.split("\n").forEach { parseRegion(it) }
}

fun parseRegion(part: String) {
    val parts = part.split(": ")
    val dimension = parts[0].split("x")
    val width = dimension[0].toInt()
    val height = dimension[1].toInt()
    val presentQuantity = parts[1].split(" ").map { it.toInt() }
    regions.add(Region(width, height, presentQuantity))
}

fun parseShape(part: String) {
    val parts = part.split(":\n")
    val index = parts[0].toInt()
    val shape = parts[1].split("\n").map { it -> it.toCharArray().map { it.toString() } }
    presents[index] = Present(index, shape)
}


