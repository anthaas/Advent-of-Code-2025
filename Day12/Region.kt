package Day12

class Region(val width: Int, val height: Int, val presentQuantity: List<Int>) {

    fun getArea(): Int {
        return width * height
    }

}