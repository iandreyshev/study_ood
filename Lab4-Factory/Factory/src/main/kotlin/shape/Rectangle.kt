package shape

import canvas.Color
import canvas.ICanvas
import containers.Vec2i

class Rectangle(
        private var leftTop: Vec2i,
        private var rightBottom: Vec2i,
        private var color: Color = Color.BLACK
) : IShape {
    init {
        val minX = Math.min(leftTop.x, rightBottom.x)
        val maxY = Math.max(leftTop.y, rightBottom.y)

        val maxX = Math.max(leftTop.x, rightBottom.x)
        val minY = Math.min(leftTop.y, rightBottom.y)

        leftTop = Vec2i(minX, maxY)
        rightBottom = Vec2i(maxX, minY)
    }

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        canvas.drawLine(leftTop, Vec2i(rightBottom.x, leftTop.y))
        canvas.drawLine(Vec2i(rightBottom.x, leftTop.y), rightBottom)
        canvas.drawLine(rightBottom, Vec2i(leftTop.x, rightBottom.y))
        canvas.drawLine(Vec2i(leftTop.x, rightBottom.y), leftTop)
    }

    override val description: String
        get() = "Rectangle: left top $leftTop, right bottom: $rightBottom"
}
