package shape

import canvas.Color
import canvas.ICanvas
import containers.IFrame
import containers.Vec2i

class Ellipse(
        private val center: Vec2i,
        private val horizontalRadius: Int,
        private val verticalRadius: Int,
        private val color: Color = Color.BLACK
) : TermShape() {
    init {
        if (horizontalRadius < 0 || verticalRadius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: IFrame
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        canvas.drawEllipse(center, horizontalRadius, verticalRadius)
    }
}
