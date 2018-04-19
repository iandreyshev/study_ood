package ru.iandreyshev.compositeshapespaint.model.factory

import canvas.Color
import factory.IShapeFactory
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.Ellipse
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.model.shape.Rectangle
import ru.iandreyshev.compositeshapespaint.model.shape.RegularPolygon

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
                val leftTop = Vec2f(intArgAt(1), intArgAt(2))
                val rightBottom = Vec2f(intArgAt(3), intArgAt(4))
                Rectangle(leftTop, rightBottom)
            }
            "polygon" -> {
                val center = Vec2f(intArgAt(1), intArgAt(2))
                val vertexCount = intArgAt(3)
                val radius = floatArgAt(4)
                RegularPolygon(center, vertexCount, radius)
            }
            "ellipse" -> {
                val center = Vec2f(intArgAt(1), intArgAt(2))
                val horizontalRadius = floatArgAt(3)
                val verticalRadius = floatArgAt(4)
                Ellipse(center, horizontalRadius, verticalRadius)
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

    private fun argAt(index: Int): String =
            mDescription[index]

    private fun intArgAt(index: Int): Int =
            argAt(index).toInt()

    private fun floatArgAt(index: Int): Float =
            argAt(index).toFloat()
}
