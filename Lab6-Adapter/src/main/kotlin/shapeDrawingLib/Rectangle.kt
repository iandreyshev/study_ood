package shapeDrawingLib

import graphicsLib.ICanvas

class Rectangle(
        private val leftTop: Point,
        private val width: Int,
        private val height: Int,
        private val color: Int = 0x000000
) : ICanvasDrawable {
    override fun draw(canvas: ICanvas) {
        println("Draw rectangle ($leftTop, $width, $height)")
    }
}
