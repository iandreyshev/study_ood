package ru.iandreyshev.compositeshapespaint.model.shape

import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

interface IShape {
    val composite: ICompositeShape?

    val frame: IFrame

    val style: IStyle

    val name: String

    fun draw(canvas: ICanvas)
}
