package shape

import canvas.Color
import canvas.ICanvas
import containers.AbstractFrame
import containers.Frame
import containers.Vec2i

class Rectangle(
        leftTop: Vec2i,
        rightBottom: Vec2i,
        private var color: Color = Color.BLACK
) : TermShape() {
    override val frame: AbstractFrame by lazy {
        val minX = Math.min(leftTop.x, rightBottom.x)
        val maxY = Math.max(leftTop.y, rightBottom.y)

        val maxX = Math.max(leftTop.x, rightBottom.x)
        val minY = Math.min(leftTop.y, rightBottom.y)

        return@lazy Frame(Vec2i(minX, minY), maxX - minX, maxY - minY)
    }

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color

        val leftTop = frame.position
        val rightTop = Vec2i(frame.position.x + frame.width, frame.position.y)
        val rightBottom = Vec2i(frame.position.x + frame.width, frame.position.y + frame.height)
        val leftBottom = Vec2i(frame.position.x, frame.position.y + frame.height)

        canvas.drawLine(leftTop, rightTop)
        canvas.drawLine(rightTop, rightBottom)
        canvas.drawLine(rightBottom, leftBottom)
        canvas.drawLine(leftBottom, leftTop)
    }
}
