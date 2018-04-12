package shape

import canvas.Color
import canvas.ICanvas
import containers.IFrame
import containers.Vec2i

class RegularPolygon(
        private val center: Vec2i,
        private val vertexCount: Int,
        private val radius: Int,
        private val color: Color = Color.BLACK
) : TermShape() {
    companion object {
        const val MIN_VERTEX = 3
    }

    init {
        if (vertexCount < MIN_VERTEX) throw IllegalArgumentException("Min vertex count is $MIN_VERTEX")
        if (radius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: IFrame
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun draw(canvas: ICanvas) {
        canvas.penColor = color
        var angle = .0
        val addAngle = 2 * Math.PI / vertexCount
        val xPoints = IntArray(vertexCount)
        val yPoints = IntArray(vertexCount)

        repeat(vertexCount) {
            xPoints[it] = Math.round(radius * Math.cos(angle)).toInt() + center.x
            yPoints[it] = Math.round(radius * Math.sin(angle)).toInt() + center.y
            angle += addAngle
        }

        fun draw(fromIndex: Int, toIndex: Int) {
            val from = Vec2i(xPoints[fromIndex], yPoints[fromIndex])
            val to = Vec2i(xPoints[toIndex], yPoints[toIndex])
            canvas.drawLine(from, to)
        }

        repeat(vertexCount) {
            draw(it, if (it + 1 == vertexCount) 0 else it + 1)
        }
    }
}
