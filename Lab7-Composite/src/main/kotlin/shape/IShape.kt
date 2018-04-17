package shape

import canvas.Color
import canvas.ICanvas
import containers.AbstractFrame

interface IShape {
    val composite: ICompositeShape?

    val frame: AbstractFrame

    fun setFillColor(color: Color)

    fun getFillColor(): Color?

    fun setStroleColor(color: Color)

    fun getStroleColor(): Color?

    fun setStrokeSize(size: Int)

    fun getStrokeSize(): Int?

    fun draw(canvas: ICanvas)
}
