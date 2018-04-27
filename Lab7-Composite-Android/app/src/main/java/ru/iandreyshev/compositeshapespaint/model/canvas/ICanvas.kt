package ru.iandreyshev.compositeshapespaint.model.canvas

import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f

interface ICanvas {
    var color: Color

    var strokeSize: Float

    fun fill()

    fun stroke()

    fun moveTo(dest: Vec2f)

    fun lineTo(dest: Vec2f)

    fun drawEllipse(center: Vec2f, horizontalRadius: Float, verticalRadius: Float)
}
