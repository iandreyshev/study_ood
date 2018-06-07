package ru.iandreyshev.canvas.style

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
