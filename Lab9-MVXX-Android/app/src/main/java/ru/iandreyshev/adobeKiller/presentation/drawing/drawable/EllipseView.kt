package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas
import ru.iandreyshev.geometry.frame.Frame
import ru.iandreyshev.canvas.style.Style

class EllipseView(
        override val frame: Frame,
        override val style: Style
) : DrawableView() {

    override fun onDrawShape(canvas: ICanvas) =
            onDraw(canvas)

    override fun onDrawStroke(canvas: ICanvas) =
            onDraw(canvas)

    override fun hitTest(testX: Float, testY: Float): Boolean {
        val halfRadiusX = (width / 2).toDouble()
        val halfRadiusY = (height / 2).toDouble()
        val centerX = frame.position.x + halfRadiusX
        val centerY = frame.position.y + halfRadiusY

        val a = Math.pow(testX - centerX, 2.0) / Math.pow(halfRadiusX, 2.0)
        val b = Math.pow(testY - centerY, 2.0) / Math.pow(halfRadiusY, 2.0)

        return a + b < 1
    }

    private fun onDraw(canvas: ICanvas) {
        val radiusX = width / 2
        val radiusY = height / 2
        val centerX = frame.x + radiusX
        val centerY = frame.y + radiusY
        canvas.drawEllipse(centerX, centerY, radiusX, radiusY)
    }

}
