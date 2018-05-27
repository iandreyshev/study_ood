package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

class ImageStyle : IStyle {

    override var fillColor: Color
        get() = Color.NONE
        set(value) {}
    override var strokeColor: Color
        get() = Color.NONE
        set(value) {}
    override var strokeSize: Float
        get() = 0f
        set(value) {}

    override fun clone(): IStyle =
            ImageStyle()

}
