package shapeDrawingLib

import graphicsLib.ICanvas

class CanvasPainter(private val canvas: ICanvas) {
    fun draw(drawable: ICanvasDrawable) = drawable.draw(canvas)
}
