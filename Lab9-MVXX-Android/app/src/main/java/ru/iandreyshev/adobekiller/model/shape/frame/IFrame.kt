package ru.iandreyshev.adobekiller.model.shape.frame

import ru.iandreyshev.adobekiller.model.container.Vec2f

interface IFrame {
    var position: Vec2f

    val width: Float

    val height: Float

    fun resize(newWidth: Float, newHeight: Float)
}

val IFrame.right: Float
    get() = position.x + width

val IFrame.top: Float
    get() = position.y

val IFrame.left: Float
    get() = position.x

val IFrame.bottom: Float
    get() = position.y + height

fun IFrame.hitTest(x: Float, y: Float): Boolean {
    val xInFrame = x >= position.x && x <= position.x + width
    val yInFrame = y >= position.y && y <= position.y + height
    return xInFrame && yInFrame
}
