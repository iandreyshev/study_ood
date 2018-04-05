package app

import graphicsLib.Canvas
import modernGraphicsLib.ModernGraphicsRenderer
import shapeDrawingLib.CanvasPainter
import shapeDrawingLib.Point
import shapeDrawingLib.Rectangle
import shapeDrawingLib.Triangle

object Application {
    fun paintPicture(painter: CanvasPainter) {
        val triangle = Triangle(Point(0, 0), Point(0, 0), Point(0, 0))
        val rectangle = Rectangle(Point(0, 0), 0, 0)

        painter.draw(triangle)
        painter.draw(rectangle)
    }

    fun paintPicturOnCanvas() {
        val canvas = Canvas()
        val painter = CanvasPainter(canvas)
        paintPicture(painter)
    }

    fun paintPictureOnModernGraphicsRenderer() {
        val canvas = ObjectiveCanvasAdapter(ModernGraphicsRenderer())
        val painter = CanvasPainter(canvas)
        paintPicture(painter)
    }

    fun paintPictureOnModernGraphicsRenderer2() {
        val canvas = ClassesCanvasAdapter()
        val painter = CanvasPainter(canvas)
        paintPicture(painter)
    }
}
