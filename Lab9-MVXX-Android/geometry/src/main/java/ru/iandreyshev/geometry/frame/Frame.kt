package ru.iandreyshev.geometry.frame

import ru.iandreyshev.geometry.vector.Vec2f

open class Frame(
        position: Vec2f = Vec2f(),
        width: Float = MIN_WIDTH,
        height: Float = MIN_HEIGHT
) : IConstFrame {

    companion object {
        private const val MIN_WIDTH = 100f
        private const val MIN_HEIGHT = 100f
    }

    constructor(frame: IConstFrame) : this (Vec2f(frame.x, frame.y), frame.width, frame.height)

    private var mWidth: Float = MIN_WIDTH
    private var mHeight: Float = MIN_HEIGHT

    init {
        mWidth = width.coerceIn(MIN_WIDTH, Float.MAX_VALUE)
        mHeight = height.coerceIn(MIN_HEIGHT, Float.MAX_VALUE)
    }

    open var position = position
        set(value) {
            field.x = value.x
            field.y = value.y
        }

    override val width: Float
        get() = mWidth

    override val height: Float
        get() = mHeight

    override val x: Float
        get() = position.x

    override val y: Float
        get() = position.y

    open fun resize(newWidth: Float, newHeight: Float) {
        mWidth = newWidth.coerceIn(MIN_WIDTH, Float.MAX_VALUE)
        mHeight = newHeight.coerceIn(MIN_HEIGHT, Float.MAX_VALUE)
    }

    fun clone() = Frame(
            position = Vec2f(position),
            width = width,
            height = height
    )

}
