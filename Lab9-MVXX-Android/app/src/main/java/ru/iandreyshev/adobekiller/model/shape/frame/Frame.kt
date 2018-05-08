package ru.iandreyshev.adobekiller.model.shape.frame

import ru.iandreyshev.adobekiller.model.container.Vec2f

class Frame @JvmOverloads constructor(
        position: Vec2f = Vec2f(),
        width: Float = MIN_WIDTH,
        height: Float = MIN_HEIGHT
) : IFrame {

    companion object {
        private const val MIN_WIDTH = 10f
        private const val MIN_HEIGHT = 10f
    }

    override var position = position
        set(value) {
            field.x = value.x
            field.y = value.y
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
