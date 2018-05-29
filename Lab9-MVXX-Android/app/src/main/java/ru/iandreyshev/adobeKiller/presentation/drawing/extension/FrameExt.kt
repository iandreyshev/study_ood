package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame

val Frame.right: Float
    get() = position.x + width

val Frame.top: Float
    get() = position.y

val Frame.left: Float
    get() = position.x

val Frame.bottom: Float
    get() = position.y + height

fun Frame.hitTest(x: Float, y: Float): Boolean {
    val xInFrame = x >= position.x && x <= position.x + width
    val yInFrame = y >= position.y && y <= position.y + height
    return xInFrame && yInFrame
}
