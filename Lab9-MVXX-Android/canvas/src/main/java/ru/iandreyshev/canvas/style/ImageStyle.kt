package ru.iandreyshev.canvas.style

class ImageStyle : Style(
        fillColor = Color.NONE,
        strokeColor = Color.NONE,
        strokeSize = 0f
) {

    override var fillColor: Color
        get() = Color.NONE
        set(value) {}
    override var strokeColor: Color
        get() = Color.NONE
        set(value) {}
    override var strokeSize: Float
        get() = 0f
        set(value) {}

    override fun clone(): Style =
            ImageStyle()

}
