package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

interface IStyle {

    var fillColor: Color
    var strokeColor: Color
    var strokeSize: Float

}
