package shape

import canvas.Color
import canvas.ICanvas
import containers.AbstractFrame
import containers.Frame
import containers.Vec2i

class Ellipse(
        center: Vec2i,
        horizontalRadius: Int,
        verticalRadius: Int,
        private val color: Color = Color.BLACK
) : TermShape() {
    init {
        if (horizontalRadius < 0 || verticalRadius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: AbstractFrame by lazy {
        val x = center.x - horizontalRadius / 2
        val y = center.y - verticalRadius / 2
        return@lazy Frame(Vec2i(x, y), horizontalRadius, verticalRadius)
    }

    override fun draw(canvas: ICanvas) {
        val centerX = frame.position.x + frame.width / 2
        val centerY = frame.position.y + frame.height / 2
        canvas.penColor = color
        canvas.drawEllipse(Vec2i(centerX, centerY), frame.width, frame.height)
    }
}
