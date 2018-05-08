package ru.iandreyshev.adobekiller.model.shape.style

import ru.iandreyshev.adobekiller.model.canvas.Color

interface IStyle {
    fun setFillColor(color: Color)

    fun getFillColor(): Color?

    fun setStrokeColor(color: Color)

    fun getStrokeColor(): Color?

    fun setStrokeSize(size: Float)

    fun getStrokeSize(): Float?
}
