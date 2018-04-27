package ru.iandreyshev.compositeshapespaint.model.container

class Frame @JvmOverloads constructor(
        override var position: Vec2f = Vec2f(),
        width: Float,
        height: Float
) : AbstractFrame() {

    override var width: Float = width
        private set
    override var height: Float = height
        private set

    override fun onResize(newWidth: Float, newHeight: Float) {
        width = newWidth
        height = newHeight
    }
}
