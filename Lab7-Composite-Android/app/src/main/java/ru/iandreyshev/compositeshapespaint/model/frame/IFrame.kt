package ru.iandreyshev.compositeshapespaint.model.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

interface IFrame {
    var position: Vec2f

    val width: Float

    val height: Float

    fun resize(newWidth: Float, newHeight: Float)

    fun hitTest(x: Float, y: Float): Boolean {
        val xInFrame = x >= position.x && x <= position.x + width
        val yInFrame = y >= position.y && y <= position.y + height
        return xInFrame && yInFrame
    }
}
