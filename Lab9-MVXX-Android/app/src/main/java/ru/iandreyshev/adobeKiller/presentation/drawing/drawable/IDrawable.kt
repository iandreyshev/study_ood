package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle

interface IDrawable {

    val frame: IConstFrame
    val style: IConstStyle

    fun draw(canvas: ICanvas)
    fun hitTest(x: Float, y: Float): Boolean
    fun onClick()

}
