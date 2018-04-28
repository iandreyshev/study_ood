package ru.iandreyshev.compositeshapespaint.model.frame

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f

interface IFrame {
    var position: Vec2f

    val width: Float

    val height: Float

    fun resize(newWidth: Float, newHeight: Float)
}
