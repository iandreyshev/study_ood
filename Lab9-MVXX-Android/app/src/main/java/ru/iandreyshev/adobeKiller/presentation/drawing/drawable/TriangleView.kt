package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class TriangleView(
        override val frame: Frame,
        style: Style
) : DrawableView(style) {

    // onDraw
    private val mPenPosition = Vec2f()
    // onDraw

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        mPenPosition.x = frame.x + frame.width / 2
        mPenPosition.y = frame.y
        canvas.moveTo(mPenPosition)

        mPenPosition.x = frame.x + frame.width
        mPenPosition.y = frame.y + frame.height
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.x
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.x + frame.width / 2
        mPenPosition.y = frame.y
        canvas.lineTo(mPenPosition)
    }

    override fun hitTest(x: Float, y: Float): Boolean = false

}
