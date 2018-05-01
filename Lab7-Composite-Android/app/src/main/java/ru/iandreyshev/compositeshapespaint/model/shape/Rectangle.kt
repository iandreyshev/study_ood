package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.frame.Frame
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

class Rectangle(
        leftTop: Vec2f,
        rightBottom: Vec2f,
        style: IStyle,
        override val name: String = Rectangle::class.java.simpleName
) : TermShape(style) {

    override val frame: IFrame by lazy {
        val minX = Math.min(leftTop.x, rightBottom.x)
        val maxY = Math.max(leftTop.y, rightBottom.y)

        val maxX = Math.max(leftTop.x, rightBottom.x)
        val minY = Math.min(leftTop.y, rightBottom.y)

        return@lazy Frame(Vec2f(minX, minY), maxX - minX, maxY - minY)
    }

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        val position = frame.position
        val width = frame.width
        val height = frame.height

        with(canvas) {
            moveTo(position)
            lineTo(position.x + width, position.y)
            lineTo(position.x + width, position.y + height)
            lineTo(position.x, position.y + height)
            lineTo(position)
        }
    }
}
