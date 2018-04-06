package app

import graphicsLib.Canvas
import graphicsLib.ICanvas
import modernGraphicsLib.ModernGraphicsRenderer
import shapeDrawingLib.CanvasPainter
import shapeDrawingLib.Point
import shapeDrawingLib.Rectangle
import shapeDrawingLib.Triangle

object Application {
    fun paintPicturOnCanvas() = Canvas().paintPicture()

    fun paintPictureOnModernGraphicsRenderer() {
        with(ObjectCanvasAdapter(ModernGraphicsRenderer({}))) {
            beginDraw()
            paintPicture()
            endDraw()
        }
    }

    fun paintPictureOnModernGraphicsRenderer2() {
        with(ClassCanvasAdapter({})) {
            beginDraw()
            paintPicture()
            endDraw()
        }
    }

    private fun ICanvas.paintPicture() {
        val triangle = Triangle(Point(0, 0), Point(0, 0), Point(0, 0))
        val rectangle = Rectangle(Point(0, 0), 0, 0)
        val painter = CanvasPainter(this)

        painter.draw(triangle)
        painter.draw(rectangle)
    }
}
