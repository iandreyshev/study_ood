package ru.iandreyshev.compositeshapespaint.model.shape

import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.containers.AbstractFrame
import ru.iandreyshev.compositeshapespaint.model.containers.Frame
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f

class RegularPolygon(
        private val center: Vec2f,
        private val vertexCount: Int,
        private val radius: Float,
        strokeSize: Float = 5f,
        fillColor: Color = Color.BLACK,
        strokeColor: Color = Color.WHITE,
        override val name: String = RegularPolygon::class.java.simpleName
) : TermShape(strokeSize, fillColor, strokeColor) {
    companion object {
        const val MIN_VERTEX = 3
    }

    init {
        if (vertexCount < MIN_VERTEX) throw IllegalArgumentException("Min vertex count is $MIN_VERTEX")
        if (radius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: AbstractFrame by lazy {
        return@lazy Frame()
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        var angle = .0
        val addAngle = 2 * Math.PI / vertexCount
        val xPoints = FloatArray(vertexCount)
        val yPoints = FloatArray(vertexCount)

        repeat(vertexCount) {
            xPoints[it] = Math.round(radius * Math.cos(angle)) + center.x
            yPoints[it] = Math.round(radius * Math.sin(angle)) + center.y
            angle += addAngle
        }

        fun draw(fromIndex: Int, toIndex: Int) {
            val from = Vec2f(xPoints[fromIndex], yPoints[fromIndex])
            val to = Vec2f(xPoints[toIndex], yPoints[toIndex])
            //canvas.drawLine(from, to)
        }

        repeat(vertexCount) {
            draw(it, if (it + 1 == vertexCount) 0 else it + 1)
        }
    }
}
