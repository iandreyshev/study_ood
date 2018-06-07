package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.canvas.style.IConstStyle
import ru.iandreyshev.canvas.style.Style

infix fun Style.copyFrom(other: IConstStyle) {
    fillColor = other.fillColor
    strokeColor = other.strokeColor
    strokeSize = other.strokeSize
}
