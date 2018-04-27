package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.containers.AbstractFrame

interface IShape {
    val composite: ICompositeShape?

    val frame: AbstractFrame

    val name: String

    fun setFillColor(color: Color)

    fun getFillColor(): Color?

    fun setStrokeColor(color: Color)

    fun getStrokeColor(): Color?

    fun setStrokeSize(size: Float)

    fun getStrokeSize(): Float?

    fun draw(canvas: ICanvas)
}
