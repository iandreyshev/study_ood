package shape

import canvas.Color

abstract class TermShape : IShape {
    override val composite: ICompositeShape? = null

    private var fillColor: Color? = null
    private var strokeColor: Color? = null
    private var strokeSize: Int? = null

    override fun getFillColor(): Color? = fillColor
    override fun setFillColor(color: Color) {
        fillColor = color
    }

    override fun getStroleColor(): Color? = strokeColor
    override fun setStroleColor(color: Color) {
        strokeColor = color
    }

    override fun getStrokeSize(): Int? = strokeSize
    override fun setStrokeSize(size: Int) {
        strokeSize = size
    }
}
