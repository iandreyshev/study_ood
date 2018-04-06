package graphicsLib

class Canvas : ICanvas {
    override fun setColor(colorInt: Int) {
        println("setColor ($colorInt)")
    }

    override fun moveTo(x: Int, y: Int) {
        println("moveTo ($x, $y)")
    }

    override fun lineTo(x: Int, y: Int) {
        println("lineTo ($x, $y)")
    }
}
