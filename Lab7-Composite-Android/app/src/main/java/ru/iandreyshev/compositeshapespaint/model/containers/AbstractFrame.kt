package ru.iandreyshev.compositeshapespaint.model.containers

abstract class AbstractFrame {
    abstract var position: Vec2f

    abstract val width: Float

    abstract val height: Float

    fun resize(newWidth: Float, newHeight: Float) {
        if (newWidth < 0 || newHeight < 0) {
            throw IllegalArgumentException("Invalid frame size. Any size less than zero.")
        }
        onResize(newWidth, newHeight)
    }

    protected abstract fun onResize(newWidth: Float, newHeight: Float)
}
