package shape

import canvas.ICanvas

interface IShape {
    val description: String

    fun draw(canvas: ICanvas)
}
