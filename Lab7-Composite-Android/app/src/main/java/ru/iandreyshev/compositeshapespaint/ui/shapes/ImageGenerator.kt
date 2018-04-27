package ru.iandreyshev.compositeshapespaint.ui.shapes

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.*

object ImageGenerator {
    fun create(): List<IShape> = with(mutableListOf<IShape>()) {
        val rect = Rectangle(
                leftTop = Vec2f(200, 500),
                rightBottom = Vec2f(600, 700),
                strokeColor = Color.BLACK,
                fillColor = Color.RED)
        val ellipse1 = Ellipse(
                center = Vec2f(200, 700),
                horizontalRadius = 250f,
                verticalRadius = 250f,
                fillColor = Color.WHITE,
                strokeColor = Color.BLACK,
                strokeSize = 20f)
        val ellipse2 = Ellipse(
                center = Vec2f(600, 700),
                horizontalRadius = 250f,
                verticalRadius = 250f,
                fillColor = Color.WHITE,
                strokeColor = Color.BLACK,
                strokeSize = 20f)

        add(CompositeShape("Car", rect, ellipse1, ellipse2))
        add(rect)
        add(RegularPolygon(
                center = Vec2f(320, 320),
                vertexCount = 7,
                radius = 100f,
                strokeSize = 15f,
                fillColor = Color.WHITE,
                strokeColor = Color.GREEN
        ))

        add(Triangle(
                vertex1 = Vec2f(100, 100),
                vertex2 = Vec2f(200, 100),
                vertex3 = Vec2f(323, 1)
        ))

        return@with this
    }
}
