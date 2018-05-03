package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

abstract class TermShape(
        override val style: IStyle
) : IShape {

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

    override val composite: ICompositeShape? = null

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
