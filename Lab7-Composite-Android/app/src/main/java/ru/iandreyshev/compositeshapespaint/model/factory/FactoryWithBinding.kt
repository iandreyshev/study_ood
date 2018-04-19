package ru.iandreyshev.compositeshapespaint.model.factory

import canvas.Color
import factory.IShapeFactory
import ru.iandreyshev.compositeshapespaint.model.containers.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.Ellipse
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.model.shape.Rectangle
import ru.iandreyshev.compositeshapespaint.model.shape.RegularPolygon

class FactoryWithBinding : IShapeFactory {
    companion object {
        private val DEFAULT_COLOR = Color.BLACK
    }

    private val mShapeCreators: HashMap<String, (List<String>) -> IShape> = hashMapOf(
            Pair("rectangle", ::createRectangle),
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
        val leftTop = Vec2f(desc[0].toInt(), desc[1].toInt())
        val rightBottom = Vec2f(desc[2].toInt(), desc[3].toInt())
        return Rectangle(leftTop, rightBottom)
    }

    private fun createPolygon(desc: List<String>): IShape {
        val center = Vec2f(desc[0].toInt(), desc[1].toInt())
        val vertexCount = desc[2].toInt()
        val radius = desc[3].toFloat()
        return RegularPolygon(center, vertexCount, radius)
    }

    private fun createEllipse(desc: List<String>): IShape {
        val center = Vec2f(desc[0].toInt(), desc[1].toInt())
        val horizontalRadius = desc[2].toFloat()
        val verticalRadius = desc[3].toFloat()
        return Ellipse(center, horizontalRadius, verticalRadius)
    }

    private fun toColor(name: String?): Color {
        return try {
            Color.valueOf(name?.toUpperCase() ?: return DEFAULT_COLOR)
        } catch (ex: Exception) {
            DEFAULT_COLOR
        }
    }
}