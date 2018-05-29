package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class DrawableEllipse(frame: Frame, style: IStyle) : BaseDrawable(style = style) {

    override val frame = Frame(frame)

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
