package ru.iandreyshev.adobeKiller.presentation.drawing.canvas

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f

interface ICanvas {

    var color: Color
    var strokeSize: Float

    fun fill()
    fun stroke()

    fun moveTo(position: Vec2f)
    fun moveTo(x: Float, y: Float)
    fun lineTo(position: Vec2f)
    fun lineTo(x: Float, y: Float)

    fun drawEllipse(centerX: Float, centerY: Float, horizontalRadius: Float, verticalRadius: Float)
    fun drawImage(image: Bitmap, position: Vec2f, width: Float, height: Float)

}
