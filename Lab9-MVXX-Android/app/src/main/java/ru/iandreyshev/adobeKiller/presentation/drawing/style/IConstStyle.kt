package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

interface IConstStyle {

    val fillColor: Color
    val strokeColor: Color
    val strokeSize: Float

    fun clone(): Style

}
