package ru.iandreyshev.adobeKiller.presentation.drawing.frame

import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f

interface IFrame {

    var position: Vec2f

    val width: Float

    val height: Float

    fun resize(newWidth: Float, newHeight: Float)

}
