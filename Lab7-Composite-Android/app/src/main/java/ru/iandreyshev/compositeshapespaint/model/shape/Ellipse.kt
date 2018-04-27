package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.AbstractFrame
import ru.iandreyshev.compositeshapespaint.model.container.Frame
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

    override val frame: AbstractFrame by lazy {
        val x = center.x - horizontalRadius / 2
        val y = center.y - verticalRadius / 2
        return@lazy Frame(Vec2f(x, y), horizontalRadius, verticalRadius)
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        val centerX = frame.position.x + frame.width / 2
        val centerY = frame.position.y + frame.height / 2
        canvas.drawEllipse(Vec2f(centerX, centerY), frame.width, frame.height)
    }
}
