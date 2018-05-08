package ru.iandreyshev.adobekiller.model.shape

import ru.iandreyshev.adobekiller.model.canvas.ICanvas
import ru.iandreyshev.adobekiller.model.shape.frame.IFrame
import ru.iandreyshev.adobekiller.model.shape.style.IStyle

interface IShape {
    val composite: ICompositeShape?

    val frame: IFrame

    val style: IStyle

    val name: String

    fun draw(canvas: ICanvas)
}
