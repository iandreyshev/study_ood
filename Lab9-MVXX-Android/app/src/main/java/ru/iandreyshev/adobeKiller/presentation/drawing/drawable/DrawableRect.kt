package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class DrawableRect(frame: IFrame, style: IStyle) : BaseDrawable(style = style) {

    override val frame = Frame(frame)

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        with(canvas) {
            moveTo(position)
            lineTo(position.x + width, position.y)
            lineTo(position.x + width, position.y + height)
            lineTo(position.x, position.y + height)
            lineTo(position)
        }
    }

}
