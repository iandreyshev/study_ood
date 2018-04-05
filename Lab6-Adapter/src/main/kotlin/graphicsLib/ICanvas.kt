package graphicsLib

interface ICanvas {
    fun setColor(colorInt: Int)

    fun moveTo(x: Int, y: Int)

    fun lineTo(x: Int, y: Int)
}
