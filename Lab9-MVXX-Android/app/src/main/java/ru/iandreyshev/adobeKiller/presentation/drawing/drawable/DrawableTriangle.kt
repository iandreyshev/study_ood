package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class DrawableTriangle(frame: Frame, style: IStyle) : BaseDrawable(style = style) {

    // onDraw
    private val mPenPosition = Vec2f()
    // onDraw

    override val frame = Frame(frame)

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    private fun onDraw(canvas: ICanvas) {
        mPenPosition.x = frame.position.x + frame.width / 2
        mPenPosition.y = frame.position.y
        canvas.moveTo(mPenPosition)

        mPenPosition.x = frame.position.x + frame.width
        mPenPosition.y = frame.position.y + frame.height
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.position.x
        canvas.lineTo(mPenPosition)

        mPenPosition.x = frame.position.x + frame.width / 2
        mPenPosition.y = frame.position.y
        canvas.lineTo(mPenPosition)
    }

}
