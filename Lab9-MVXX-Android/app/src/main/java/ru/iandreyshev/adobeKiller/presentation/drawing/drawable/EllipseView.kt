package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.canvas.style.Style

class EllipseView(
        override val frame: Frame,
        style: Style
) : DrawableView(style) {

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    override fun hitTest(x: Float, y: Float): Boolean = false

    private fun onDraw(canvas: ICanvas) {
        val radiusX = width / 2
        val radiusY = height / 2
        val centerX = frame.x + radiusX
        val centerY = frame.y + radiusY
        canvas.drawEllipse(centerX, centerY, radiusX, radiusY)
    }

}
