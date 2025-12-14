package Day12

class Present(val index: Int, val shape: List<List<String>>) {

    fun getShapeSize(): Int {
        return shape.map { it.filter { it == "#" }.size }.sum()
    }

    fun getShapeArea(): Int {
        return shape.sumOf { it.size }
    }

}

