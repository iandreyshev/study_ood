package ru.iandreyshev.compositeshapespaint.model.shape.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class Frame @JvmOverloads constructor(
        override var position: Vec2f = Vec2f(),
        width: Float = 0f,
        height: Float = 0f
) : IFrame {

    companion object {
        private const val MIN_WIDTH = 10f
        private const val MIN_HEIGHT = 10f
    }

    override var width: Float = width
        private set
    override var height: Float = height
        private set

    override fun resize(newWidth: Float, newHeight: Float) {
        width = newWidth.coerceIn(MIN_WIDTH, Float.MAX_VALUE)
        height = newHeight.coerceIn(MIN_HEIGHT, Float.MAX_VALUE)
    }
}
