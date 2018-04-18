package factory

import canvas.Color
import containers.Vec2i
import ru.iandreyshev.compositeshapespaint.model.shape.Rectangle
import shape.*

class ShapeFactory : IShapeFactory {
    companion object {
        private val DEFAULT_COLOR = Color.BLACK
    }

    private var mDescription: MutableList<String> = ArrayList()

    override fun create(description: List<String>): IShape? {
        return try {
            mDescription.addAll(description)
            val shape = getShape()
            mDescription.clear()
            shape
        } catch (ex: Exception) {
            null
        }
    }

    private fun getShape(): IShape? {
        return when (argAt(0)) {
            "rectangle" -> {
                val leftTop = Vec2i(intArgAt(1), intArgAt(2))
                val rightBottom = Vec2i(intArgAt(3), intArgAt(4))
                Rectangle(leftTop, rightBottom, getColor(5))
            }
            "polygon" -> {
                val center = Vec2i(intArgAt(1), intArgAt(2))
                val vertexCount = intArgAt(3)
                val radius = intArgAt(4)
                RegularPolygon(center, vertexCount, radius, getColor(5))
            }
            "ellipse" -> {
                val center = Vec2i(intArgAt(1), intArgAt(2))
                val horizontalRadius = intArgAt(3)
                val verticalRadius = intArgAt(4)
                Ellipse(center, horizontalRadius, verticalRadius, getColor(5))
            }
            else -> null
        }
    }

    private fun getColor(index: Int): Color {
        val color = mDescription.getOrNull(index) ?: return DEFAULT_COLOR

        return try {
            Color.valueOf(color.toUpperCase())
        } catch (ex: Exception) {
            DEFAULT_COLOR
        }
    }

    private fun argAt(index: Int): String {
        return mDescription[index]
    }

    private fun intArgAt(index: Int): Int {
        return argAt(index).toInt()
    }
}
