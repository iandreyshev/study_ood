package ru.iandreyshev.adobekiller.model.shape.style

import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.shape.style.IStyle

class Style(
        private var fillColor: Color = Color.WHITE,
        private var strokeColor: Color = Color.BLACK,
        private var strokeSize: Float = 5f
) : IStyle {

    override fun setFillColor(color: Color) {
        fillColor = color
    }

    override fun setStrokeColor(color: Color) {
        strokeColor = color
    }

    override fun setStrokeSize(size: Float) {
        strokeSize = size
    }

    override fun getFillColor(): Color? =
            fillColor

    override fun getStrokeColor(): Color? =
            strokeColor

    override fun getStrokeSize(): Float? =
            strokeSize
}
