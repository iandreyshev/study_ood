package shape

import canvas.Color
import canvas.ICanvas
import containers.Vec2i

class Triangle(
        private val vertex1: Vec2i,
        private val vertex2: Vec2i,
        private val vertex3: Vec2i,
        private val color: Color = Color.BLACK
) : IShape {
    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        canvas.drawLine(vertex1, vertex2)
        canvas.drawLine(vertex2, vertex3)
        canvas.drawLine(vertex3, vertex1)
    }

    override val description: String
        get() = "Triangle: first vertex $vertex1, second vertex $vertex2, third vertex $vertex3"
}
