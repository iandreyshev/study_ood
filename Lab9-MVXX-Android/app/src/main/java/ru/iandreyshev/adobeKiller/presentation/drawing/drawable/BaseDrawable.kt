package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class BaseDrawable(
        override val style: IStyle
) : IDrawable {

    protected val position: Vec2f
        get() = Vec2f(frame.x, frame.y)
    protected val width: Float
        get() = frame.width
    protected val height: Float
        get() = frame.height

    final override fun draw(canvas: ICanvas) {
        onDrawShape(canvas)
        canvas.color = style.fillColor
        canvas.fill()

        onDrawStroke(canvas)
        canvas.color = style.strokeColor
        canvas.strokeSize = style.strokeSize
        canvas.stroke()
    }

    protected abstract fun onDrawShape(canvas: ICanvas)

    protected abstract fun onDrawStroke(canvas: ICanvas)

    override fun onClick() = Unit
    override fun hitTest(x: Float, y: Float) = false

}
