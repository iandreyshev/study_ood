package ru.iandreyshev.adobekiller.factory

import ru.iandreyshev.adobekiller.model.shape.*
import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.shape.style.Style

object IndexedShapesFactory : IShapeFactory {

    private var mShapeIndex = 0L

    override fun create(shapeName: String): IShape? {
        return when (shapeName) {
            "rect" -> Rectangle(
                    leftTop = Vec2f(100, 100),
                    rightBottom = Vec2f(200, 300),
                    style = Style(),
                    name = "Shape #$mShapeIndex (Rect)"
            )
            "circle" -> Ellipse(
                    center = Vec2f(200, 200),
                    verticalRadius = 200f,
                    horizontalRadius = 200f,
                    style = Style(),
                    name = "Shape #$mShapeIndex (Circle)"
            )
            "triangle" -> Triangle(
                    vertex1 = Vec2f(200, 0),
                    vertex2 = Vec2f(0, 300),
                    vertex3 = Vec2f(400, 300),
                    style = Style(),
                    name = "Shape #$mShapeIndex (Triangle)"
            )
            "polygon" -> RegularPolygon(
                    center = Vec2f(300, 300),
                    radius = 200f,
                    vertexCount = 6,
                    style = Style(),
                    name = "Shape #$mShapeIndex (R. Polygon)"
            )
            else -> null

        }?.apply {
            ++mShapeIndex
        }
    }
}
