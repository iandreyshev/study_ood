package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.geometry.frame.IConstFrame

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

fun IConstFrame.hitTest(testX: Float, testY: Float): Boolean {
    val xInFrame = testX >= x && testX <= this.x + width
    val yInFrame = testY >= this.y && testY <= this.y + height
    return xInFrame && yInFrame
}
