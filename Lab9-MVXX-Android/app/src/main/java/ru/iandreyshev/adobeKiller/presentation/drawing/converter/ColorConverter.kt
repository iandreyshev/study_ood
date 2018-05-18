package ru.iandreyshev.adobeKiller.presentation.drawing.converter

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

object ColorConverter {

    fun convert(from: Color): Int = when (from) {
        Color.NONE -> android.graphics.Color.TRANSPARENT
        Color.WHITE -> android.graphics.Color.WHITE
        Color.RED -> android.graphics.Color.RED
        Color.GREEN -> android.graphics.Color.GREEN
        Color.BLUE -> android.graphics.Color.BLUE
        Color.YELLOW -> android.graphics.Color.YELLOW
        Color.BLACK -> android.graphics.Color.BLACK
    }

}
