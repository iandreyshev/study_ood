package shape.factory

import canvas.Color
import containers.Vec2i
import shape.*

class FactoryWithBinding : IShapeFactory {
    companion object {
        private val DEFAULT_COLOR = Color.BLACK
    }

    private val mShapeCreators: HashMap<String, (List<String>) -> IShape> = hashMapOf(
            Pair("rectangle", ::createRectangle),
            Pair("triangle", ::createTriangle),
            Pair("polygon", ::createPolygon),
            Pair("ellipse", ::createEllipse)
    )

    override fun create(description: List<String>): IShape? {
        return try {
            val shapeName = description.first()
            val shapeProperties = description.subList(1, description.size)
            return mShapeCreators[shapeName]?.invoke(shapeProperties)
        } catch (ex: Exception) {
            null
        }
    }

    private fun createRectangle(desc: List<String>): IShape {
        val leftTop = Vec2i(desc[0].toInt(), desc[1].toInt())
        val rightBottom = Vec2i(desc[2].toInt(), desc[3].toInt())
        val color = toColor(desc.getOrNull(4))
        return Rectangle(leftTop, rightBottom, color)
    }

    private fun createTriangle(desc: List<String>): IShape {
        val vertex0 = Vec2i(desc[0].toInt(), desc[1].toInt())
        val vertex1 = Vec2i(desc[2].toInt(), desc[3].toInt())
        val vertex2 = Vec2i(desc[4].toInt(), desc[5].toInt())
        val color = toColor(desc.getOrNull(6))
        return Triangle(vertex0, vertex1, vertex2, color)
    }

    private fun createPolygon(desc: List<String>): IShape {
        val center = Vec2i(desc[0].toInt(), desc[1].toInt())
        val vertexCount = desc[2].toInt()
        val radius = desc[3].toInt()
        val color = toColor(desc.getOrNull(4))
        return RegularPolygon(center, vertexCount, radius, color)
    }

    private fun createEllipse(desc: List<String>): IShape {
        val center = Vec2i(desc[0].toInt(), desc[1].toInt())
        val horizontalRadius = desc[2].toInt()
        val verticalRadius = desc[3].toInt()
        val color = toColor(desc.getOrNull(4))
        return Ellipse(center, horizontalRadius, verticalRadius, color)
    }

    private fun toColor(name: String?): Color {
        return try {
            Color.valueOf(name?.toUpperCase() ?: return DEFAULT_COLOR)
        } catch (ex: Exception) {
            DEFAULT_COLOR
        }
    }
}