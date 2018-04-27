package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.AbstractFrame
import ru.iandreyshev.compositeshapespaint.model.container.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class Rectangle(
        leftTop: Vec2f,
        rightBottom: Vec2f,
        strokeSize: Float = 5f,
        fillColor: Color = Color.BLACK,
        strokeColor: Color = Color.WHITE,
        override val name: String = Rectangle::class.java.simpleName
) : TermShape(strokeSize, fillColor, strokeColor) {
    override val frame: AbstractFrame by lazy {
        val minX = Math.min(leftTop.x, rightBottom.x)
        val maxY = Math.max(leftTop.y, rightBottom.y)

        val maxX = Math.max(leftTop.x, rightBottom.x)
        val minY = Math.min(leftTop.y, rightBottom.y)

        return@lazy Frame(Vec2f(minX, minY), maxX - minX, maxY - minY)
    }

    override fun onDrawShape(canvas: ICanvas) = onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) = onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        val leftTop = frame.position
        val rightTop = Vec2f(frame.position.x + frame.width, frame.position.y)
        val rightBottom = Vec2f(frame.position.x + frame.width, frame.position.y + frame.height)
        val leftBottom = Vec2f(frame.position.x, frame.position.y + frame.height)

        with(canvas) {
            moveTo(leftTop)
            lineTo(rightTop)
            lineTo(rightBottom)
            lineTo(leftBottom)
            lineTo(leftTop)
        }
    }
}
