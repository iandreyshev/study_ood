package shape

import canvas.Color
import canvas.ICanvas
import containers.Vec2i

class Ellipse(
        private val center: Vec2i,
        private val horizontalRadius: Int,
        private val verticalRadius: Int,
        private val color: Color = Color.BLACK
) : IShape {
    init {
        if (horizontalRadius < 0 || verticalRadius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        canvas.drawEllipse(center, horizontalRadius, verticalRadius)
    }

    override val description: String
        get() = "Ellipse: center $center, horizontal radius $horizontalRadius, vertical radius $verticalRadius"
}
