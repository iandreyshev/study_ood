package ru.iandreyshev.adobekiller.model.shape

import ru.iandreyshev.adobekiller.model.canvas.ICanvas
import ru.iandreyshev.adobekiller.model.shape.frame.IFrame
import ru.iandreyshev.adobekiller.model.shape.frame.Frame
import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.shape.style.IStyle

class Ellipse(
        center: Vec2f,
        horizontalRadius: Float,
        verticalRadius: Float,
        style: IStyle,
        override val name: String = Ellipse::class.java.simpleName
) : TermShape(style) {

    init {
        if (horizontalRadius < 0 || verticalRadius < 0) throw IllegalArgumentException("Radius can not be negative")
    }

    override val frame: IFrame by lazy {
        val x = center.x - horizontalRadius
        val y = center.y - verticalRadius
        return@lazy Frame(Vec2f(x, y), horizontalRadius * 2, verticalRadius * 2)
    }

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        val radiusX = width / 2
        val radiusY = height / 2
        val centerX = position.x + radiusX
        val centerY = position.y + radiusY
        canvas.drawEllipse(centerX, centerY, radiusX, radiusY)
    }
}
