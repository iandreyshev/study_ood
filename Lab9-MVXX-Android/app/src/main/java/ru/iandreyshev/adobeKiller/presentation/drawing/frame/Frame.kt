package ru.iandreyshev.adobeKiller.presentation.drawing.frame

import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f

open class Frame(
        position: Vec2f = Vec2f(),
        override var width: Float = MIN_WIDTH,
        override var height: Float = MIN_HEIGHT
) : IConstFrame {

    companion object {
        private const val MIN_WIDTH = 100f
        private const val MIN_HEIGHT = 100f
    }

    constructor(frame: Frame) : this(Vec2f(frame.position), frame.width, frame.height)

    var position = position
        set(value) {
            field.x = value.x
            field.y = value.y
        }

    override val x: Float
        get() = position.x

    override val y: Float
        get() = position.y

    fun resize(newWidth: Float, newHeight: Float) {
        width = newWidth.coerceIn(MIN_WIDTH, Float.MAX_VALUE)
        height = newHeight.coerceIn(MIN_HEIGHT, Float.MAX_VALUE)
    }

    fun clone() = Frame(
            position = Vec2f(position),
            width = width,
            height = height
    )

}
