package ru.iandreyshev.compositeshapespaint.model.containers

class Frame @JvmOverloads constructor(
        override var position: Vec2f = Vec2f(),
        override var width: Float = 0f,
        override var height: Float = 0f
) : AbstractFrame() {
    override fun onResize(newWidth: Float, newHeight: Float) {
        width = newWidth
        height = newHeight
    }
}
