package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.frame.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class Ellipse(
        center: Vec2f,
        horizontalRadius: Float,
        verticalRadius: Float,
        strokeSize: Float = 5f,
        fillColor: Color = Color.BLACK,
        strokeColor: Color = Color.WHITE,
        override val name: String = Ellipse::class.java.simpleName
) : TermShape(strokeSize, fillColor, strokeColor) {
    init {
        if (horizontalRadius < 0 || verticalRadius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: IFrame by lazy {
        val x = center.x - horizontalRadius
        val y = center.y - verticalRadius
        return@lazy Frame(Vec2f(x, y), horizontalRadius * 2, verticalRadius * 2)
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        val radiusX = frame.width / 2
        val radiusY = frame.height / 2
        val centerX = frame.position.x + radiusX
        val centerY = frame.position.y + radiusY
        canvas.drawEllipse(centerX, centerY, radiusX, radiusY)
    }
}
