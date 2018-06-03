package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

open class Style(
        override var fillColor: Color = Color.WHITE,
        override var strokeColor: Color = Color.BLACK,
        override var strokeSize: Float = 5f
): IConstStyle {

    constructor(style: IConstStyle) : this(
            fillColor = style.fillColor,
            strokeColor = style.strokeColor,
            strokeSize = style.strokeSize
    )

    override fun clone(): Style =
            Style(this)

}
