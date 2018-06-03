package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.bottom
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.left
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.right
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.top
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class RectView(
        override val frame: Frame,
        style: Style
) : DrawableView(style) {

    // onDraw
    private val mPosition = Vec2f()
    // onDraw

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        mPosition.x = frame.x
        mPosition.y = frame.y

        with(canvas) {
            moveTo(mPosition)
            lineTo(mPosition.x + width, mPosition.y)
            lineTo(mPosition.x + width, mPosition.y + height)
            lineTo(mPosition.x, mPosition.y + height)
            lineTo(mPosition)
        }
    }

    override fun hitTest(x: Float, y: Float): Boolean {
        return x >= frame.left && x <= frame.right
                && y >= frame.top && y <= frame.bottom
    }

}
