package shapeDrawingLib

import graphicsLib.ICanvas

class Triangle(
        private val p1: Point,
        private val p2: Point,
        private val p3: Point,
        private val color: Int = 0x000000
) : ICanvasDrawable {
    override fun draw(canvas: ICanvas) {
        println("Draw triangle ($p1, $p2, $p3)")
    }
}
