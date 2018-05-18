package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

interface IStyle {
    fun setFillColor(color: Color)

    fun getFillColor(): Color?

    fun setStrokeColor(color: Color)

    fun getStrokeColor(): Color?

    fun setStrokeSize(size: Float)

    fun getStrokeSize(): Float?
}
