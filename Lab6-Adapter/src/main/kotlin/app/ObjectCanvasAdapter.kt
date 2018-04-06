package app

import graphicsLib.ICanvas
import modernGraphicsLib.ModernGraphicsRenderer
import modernGraphicsLib.ModernPoint
import modernGraphicsLib.RGBAColor

class ObjectCanvasAdapter(private val renderer: ModernGraphicsRenderer) : ICanvas {
    private var mPosition = ModernPoint(0, 0)
    private var mColor = RGBAColor(0f, 0f, 0f, 0f)

    override fun setColor(colorInt: Int) {
        mColor = colorInt.toRGBAColor()
    }

    override fun moveTo(x: Int, y: Int) {
        mPosition.x = x
        mPosition.y = y
    }

    override fun lineTo(x: Int, y: Int) {
        renderer.drawLine(mPosition, ModernPoint(x, y), mColor)
        mPosition.x = x
        mPosition.y = y
    }

    fun beginDraw() = renderer.beginDraw()

    fun endDraw() = renderer.endDraw()
}
