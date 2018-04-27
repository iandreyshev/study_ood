package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.AbstractFrame
import ru.iandreyshev.compositeshapespaint.model.container.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class RegularPolygon(
        center: Vec2f,
        private val vertexCount: Int,
        radius: Float,
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
        return@lazy Frame(center, radius, radius)
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        var angle = .0
        val addAngle = 2 * Math.PI / vertexCount
        val xPoints = FloatArray(vertexCount)
        val yPoints = FloatArray(vertexCount)

        repeat(vertexCount) {
            xPoints[it] = Math.round(frame.radius * Math.cos(angle)) + frame.center.x
            yPoints[it] = Math.round(frame.radius * Math.sin(angle)) + frame.center.y
            angle += addAngle
        }

        if (!xPoints.isEmpty() && !yPoints.isEmpty()) {
            canvas.moveTo(Vec2f(xPoints.first(), yPoints.first()))
        }

        repeat(vertexCount) { pointIndex ->
            val pointNum = if (pointIndex + 1 == vertexCount) 0 else pointIndex + 1
            canvas.lineTo(Vec2f(xPoints[pointNum], yPoints[pointNum]))
        }
    }

    private val AbstractFrame.radius: Float
        get() = Math.min(width, height)

    private val AbstractFrame.center: Vec2f
        get() = Vec2f(position.x + width / 2, position.y + height / 2)
}
