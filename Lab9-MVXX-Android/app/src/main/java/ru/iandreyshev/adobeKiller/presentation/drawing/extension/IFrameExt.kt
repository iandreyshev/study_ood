package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame

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
