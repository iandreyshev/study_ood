package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.canvas.style.Style

interface IDrawable {

    val frame: Frame
    val style: Style

    fun draw(canvas: ICanvas)

    fun hitTest(x: Float, y: Float): Boolean
    fun onSelect()
    fun onDelete()

}
