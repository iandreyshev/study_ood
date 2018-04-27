package ru.iandreyshev.compositeshapespaint.model.container

data class Vec2f(
        val x: Float = 0f,
        val y: Float = 0f
) {
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
}
