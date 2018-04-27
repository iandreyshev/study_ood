package ru.iandreyshev.compositeshapespaint.ui.extension

import android.graphics.Canvas
import android.graphics.Paint
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.converter.ColorConverter
import ru.iandreyshev.compositeshapespaint.ui.view.CanvasView

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
