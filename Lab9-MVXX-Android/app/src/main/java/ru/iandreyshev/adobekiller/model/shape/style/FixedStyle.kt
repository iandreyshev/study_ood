package ru.iandreyshev.adobekiller.model.shape.style

import ru.iandreyshev.adobekiller.model.canvas.Color

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
