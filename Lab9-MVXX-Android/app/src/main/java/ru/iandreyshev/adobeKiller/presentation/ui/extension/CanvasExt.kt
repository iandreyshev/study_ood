package ru.iandreyshev.adobeKiller.presentation.ui.extension

import android.graphics.Canvas
import android.graphics.Paint
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.converter.ColorConverter
import ru.iandreyshev.adobeKiller.presentation.ui.view.CanvasView

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
