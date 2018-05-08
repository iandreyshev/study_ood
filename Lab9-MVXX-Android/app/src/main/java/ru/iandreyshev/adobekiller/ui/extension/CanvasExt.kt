package ru.iandreyshev.adobekiller.ui.extension

import android.graphics.Canvas
import android.graphics.Paint
import ru.iandreyshev.adobekiller.R
import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.converter.ColorConverter
import ru.iandreyshev.adobekiller.ui.view.CanvasView

fun Canvas.fill(color: Color) {
    val paint = Paint()
    paint.color = ColorConverter.convert(color)
    paint.style = Paint.Style.FILL
    drawPaint(paint)
}

fun CanvasView.fill(color: Color?) {
    onDrawAction { view, canvas ->
        when (color) {
            null -> view.setBackgroundResource(R.drawable.undefined_color)
            Color.NONE -> view.setBackgroundResource(R.drawable.none_color)
            else -> canvas.fill(color)
        }
    }
}
