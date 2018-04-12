package shape

import canvas.Color
import canvas.ICanvas
import containers.IFrame
import containers.Vec2i

class Triangle(
        private val vertex1: Vec2i,
        private val vertex2: Vec2i,
        private val vertex3: Vec2i,
        private val color: Color = Color.BLACK
) : TermShape() {
    override val frame: IFrame
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        canvas.drawLine(vertex1, vertex2)
        canvas.drawLine(vertex2, vertex3)
        canvas.drawLine(vertex3, vertex1)
    }
}
