package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame

infix fun Frame.copyFrom(other: IConstFrame) {
    resize(other.width, other.height)
    position.x = other.x
    position.y = other.y
}

val IConstFrame.right: Float
    get() = x + width

val IConstFrame.top: Float
    get() = y

val IConstFrame.left: Float
    get() = x

val IConstFrame.bottom: Float
    get() = y + height

fun IConstFrame.hitTest(x: Float, y: Float): Boolean {
    val xInFrame = x >= x && x <= x + width
    val yInFrame = y >= y && y <= y + height
    return xInFrame && yInFrame
}
