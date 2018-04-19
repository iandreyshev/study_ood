package ru.iandreyshev.compositeshapespaint.model.shape

import canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas

abstract class TermShape(
        private var strokeSize: Float,
        private var fillColor: Color,
        private var strokeColor: Color
) : IShape {
    override val composite: ICompositeShape? = null

    override fun getFillColor(): Color = fillColor
    override fun setFillColor(color: Color) {
        fillColor = color
    }

    override fun getStrokeColor(): Color = strokeColor
    override fun setStrokeColor(color: Color) {
        strokeColor = color
    }

    override fun getStrokeSize(): Float = strokeSize
    override fun setStrokeSize(size: Float) {
        strokeSize = size
    }

    final override fun draw(canvas: ICanvas) {
        onDrawShape(canvas)
        canvas.color = fillColor
        canvas.fill()

        onDrawStroke(canvas)
        canvas.color = strokeColor
        canvas.strokeSize = strokeSize
        canvas.stroke()
    }

    protected abstract fun onDrawShape(canvas: ICanvas)

    protected abstract fun onDrawStroke(canvas: ICanvas)
}
