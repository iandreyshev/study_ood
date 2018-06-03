package ru.iandreyshev.adobeKiller.presentation.drawing.extension

import ru.iandreyshev.adobeKiller.presentation.drawing.style.IConstStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

infix fun Style.copyFrom(other: IConstStyle) {
    fillColor = other.fillColor
    strokeColor = other.strokeColor
    strokeSize = other.strokeSize
}
