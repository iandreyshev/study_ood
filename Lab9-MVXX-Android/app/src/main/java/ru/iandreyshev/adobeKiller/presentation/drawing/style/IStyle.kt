package ru.iandreyshev.adobeKiller.presentation.drawing.style

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color

interface IStyle : IConstStyle {

    override var fillColor: Color
    override var strokeColor: Color
    override var strokeSize: Float

}
