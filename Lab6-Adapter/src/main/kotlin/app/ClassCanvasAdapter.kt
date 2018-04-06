package app

import graphicsLib.ICanvas
import modernGraphicsLib.ModernGraphicsRenderer
import modernGraphicsLib.ModernPoint
import modernGraphicsLib.RGBAColor

class ClassCanvasAdapter(
        output: (String) -> Unit
) : ModernGraphicsRenderer(output), ICanvas {
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
        val endPosition = ModernPoint(x, y)
        drawLine(mPosition, endPosition, mColor)
        mPosition = endPosition
    }
}
