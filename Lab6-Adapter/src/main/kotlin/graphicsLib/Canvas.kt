package graphicsLib

class Canvas : ICanvas {
    override fun moveTo(x: Int, y: Int) {
        println("moveTo ($x, $y)")
    }

    override fun lineTo(x: Int, y: Int) {
        println("lineTo ($x, $y)")
    }
}
