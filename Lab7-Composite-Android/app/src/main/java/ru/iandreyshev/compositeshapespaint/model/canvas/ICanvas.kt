package canvas

import containers.Vec2i

interface ICanvas {
    var stroke: Float

    var penColor: Color

    fun drawLine(from: Vec2i, to: Vec2i)

    fun drawEllipse(center: Vec2i, horizontalRadius: Int, verticalRadius: Int)
}
