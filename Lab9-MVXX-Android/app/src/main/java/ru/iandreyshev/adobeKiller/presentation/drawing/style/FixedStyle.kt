package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

class FixedStyle(
        private val fillColor: Color? = null,
        private val strokeColor: Color? = null,
        private val strokeSize: Float? = null
) : IStyle {

    override fun setFillColor(color: Color) {
        // skip
    }

    override fun setStrokeColor(color: Color) {
        // skip
    }

    override fun setStrokeSize(size: Float) {
        // skip
    }

    override fun getFillColor(): Color? = fillColor

    override fun getStrokeColor(): Color? = strokeColor

    override fun getStrokeSize(): Float? = strokeSize
}
