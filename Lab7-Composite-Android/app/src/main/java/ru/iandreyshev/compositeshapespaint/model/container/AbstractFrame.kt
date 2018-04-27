package ru.iandreyshev.compositeshapespaint.model.container

abstract class AbstractFrame {
    companion object {
        private const val MIN_WIDTH = 10f
        private const val MIN_HEIGHT = 10f
    }

    abstract var position: Vec2f

    abstract val width: Float

    abstract val height: Float

    fun resize(newWidth: Float, newHeight: Float) = onResize(
            newWidth.coerceIn(MIN_WIDTH, Float.MAX_VALUE),
            newHeight.coerceIn(MIN_HEIGHT, Float.MAX_VALUE)
    )

    protected abstract fun onResize(newWidth: Float, newHeight: Float)
}
