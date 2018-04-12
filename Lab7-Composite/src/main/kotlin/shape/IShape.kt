package shape

import canvas.Color
import canvas.ICanvas
import containers.IFrame

interface IShape {
    val composite: ICompositeShape?

    val frame: IFrame

    var fillColor: Color?

    var strokeColor: Color?

    var strokeSize: Int?

    fun draw(canvas: ICanvas)
}
