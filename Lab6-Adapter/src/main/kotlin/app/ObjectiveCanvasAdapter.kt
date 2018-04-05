package app

import graphicsLib.ICanvas
import modernGraphicsLib.ModernGraphicsRenderer
import modernGraphicsLib.ModernPoint
import modernGraphicsLib.RGBAColor

class ObjectiveCanvasAdapter(private val renderer: ModernGraphicsRenderer) : ICanvas {
    private var mPosition = ModernPoint(0, 0)
    private var mColor = RGBAColor(0f, 0f, 0f, 0f)

    override fun setColor(colorInt: Int) {
        // TODO: Convert int color to RGBAColor
    }

    override fun moveTo(x: Int, y: Int) {
        mPosition.x = x
        mPosition.y = y
    }

    override fun lineTo(x: Int, y: Int) {
        val endPosition = ModernPoint(x, y)

        renderer.beginDraw()
        renderer.drawLine(mPosition, endPosition, mColor)
        renderer.endDraw()

        mPosition = endPosition
    }
}
