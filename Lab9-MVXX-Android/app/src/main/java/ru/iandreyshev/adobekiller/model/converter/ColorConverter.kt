package ru.iandreyshev.adobekiller.model.converter

import ru.iandreyshev.adobekiller.model.canvas.Color

object ColorConverter {
    fun convert(color: Color): Int {
        return when (color) {
            Color.NONE -> android.graphics.Color.TRANSPARENT
            Color.WHITE -> android.graphics.Color.WHITE
            Color.RED -> android.graphics.Color.RED
            Color.GREEN -> android.graphics.Color.GREEN
            Color.BLUE -> android.graphics.Color.BLUE
            Color.YELLOW -> android.graphics.Color.YELLOW
            Color.BLACK -> android.graphics.Color.BLACK
        }
    }
}
