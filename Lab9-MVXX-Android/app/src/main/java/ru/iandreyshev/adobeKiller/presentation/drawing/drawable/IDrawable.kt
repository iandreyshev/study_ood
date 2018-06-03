package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

interface IDrawable {

    val frame: Frame
    val style: Style

    fun draw(canvas: ICanvas)

    fun hitTest(x: Float, y: Float): Boolean
    fun onClick()
    fun onDelete()

}
