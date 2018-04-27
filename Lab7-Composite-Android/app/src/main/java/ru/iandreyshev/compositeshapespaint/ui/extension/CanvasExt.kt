package ru.iandreyshev.compositeshapespaint.ui.extension

import android.graphics.Canvas
import android.graphics.Paint
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.converter.ColorConverter

fun Canvas.fill(color: Color) {
    val paint = Paint()
    paint.color = ColorConverter.convert(color)
    paint.style = Paint.Style.FILL
    drawPaint(paint)
}
