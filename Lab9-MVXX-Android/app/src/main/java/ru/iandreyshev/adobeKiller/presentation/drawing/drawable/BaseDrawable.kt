package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class BaseDrawable(
        override val style: IStyle
) : IDrawable {

    companion object {
        private val DEFAULT_FILL_COLOR = Color.WHITE
        private val DEFAULT_STROKE_COLOR = Color.BLACK
        private const val DEFAULT_STROKE_SIZE = 5f
    }

    protected val position: Vec2f
        get() = frame.position
    protected val width: Float
        get() = frame.width
    protected val height: Float
        get() = frame.height

    final override fun draw(canvas: ICanvas) {
        onDrawShape(canvas)
        canvas.color = style.getFillColor() ?: DEFAULT_FILL_COLOR
        canvas.fill()

        onDrawStroke(canvas)
        canvas.color = style.getStrokeColor() ?: DEFAULT_STROKE_COLOR
        canvas.strokeSize = style.getStrokeSize() ?: DEFAULT_STROKE_SIZE
        canvas.stroke()
    }

    protected abstract fun onDrawShape(canvas: ICanvas)

    protected abstract fun onDrawStroke(canvas: ICanvas)

}
