package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

interface IDrawable {

    val frame: IFrame
    val style: IStyle

    fun draw(canvas: ICanvas)

}
