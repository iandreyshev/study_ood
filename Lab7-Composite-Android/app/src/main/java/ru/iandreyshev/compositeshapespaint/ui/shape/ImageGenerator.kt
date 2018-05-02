package ru.iandreyshev.compositeshapespaint.ui.shape

import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.*
import ru.iandreyshev.compositeshapespaint.model.shape.style.Style

object ImageGenerator {
    fun create(): MutableList<IShape> = with(mutableListOf<IShape>()) {

        val rect = Rectangle(
                leftTop = Vec2f(200, 100),
                rightBottom = Vec2f(450, 700),
                style = Style())

        val ellipse1 = Ellipse(
                center = Vec2f(200, 600),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                style = Style())

        val ellipse2 = Ellipse(
                center = Vec2f(200, 850),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                style = Style())

        val ellipse3 = Ellipse(
                center = Vec2f(450, 600),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                style = Style())

        val ellipse4 = Ellipse(
                center = Vec2f(450, 850),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                style = Style())

        val polygon = RegularPolygon(
                center = Vec2f(400, 200),
                vertexCount = 7,
                radius = 100f,
                style = Style()
        )

        val triangle = Triangle(
                vertex1 = Vec2f(),
                vertex2 = Vec2f(120, 30),
                vertex3 = Vec2f(24, 333),
                style = Style()
        )

        add(rect)
        add(polygon)
        add(triangle)
        add(CompositeShape("Balls", ellipse1, ellipse2, ellipse3, ellipse4))

        return@with this
    }
}
