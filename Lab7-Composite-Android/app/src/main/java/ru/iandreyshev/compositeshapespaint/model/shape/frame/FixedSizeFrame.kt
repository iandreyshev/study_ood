package ru.iandreyshev.compositeshapespaint.model.shape.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

class FixedSizeFrame(
        override var position: Vec2f,
        override val width: Float,
        override val height: Float
) : IFrame {

    override fun resize(newWidth: Float, newHeight: Float) {
        // skip
    }
}
