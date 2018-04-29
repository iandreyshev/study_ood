package ru.iandreyshev.compositeshapespaint.ui.shapes

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.*

object ImageGenerator {
    fun create(): MutableList<IShape> = with(mutableListOf<IShape>()) {
        val rect = Rectangle(
                leftTop = Vec2f(200, 100),
                rightBottom = Vec2f(450, 700),
                strokeColor = Color.BLACK,
                fillColor = Color.RED)
        val ellipse1 = Ellipse(
                center = Vec2f(200, 700),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                fillColor = Color.WHITE,
                strokeColor = Color.BLACK,
                strokeSize = 20f)
        val ellipse2 = Ellipse(
                center = Vec2f(450, 700),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                fillColor = Color.WHITE,
                strokeColor = Color.BLACK,
                strokeSize = 20f)

        val ellipse3 = Ellipse(
                center = Vec2f(400, 300),
                horizontalRadius = 100f,
                verticalRadius = 100f,
                fillColor = Color.WHITE,
                strokeColor = Color.BLACK,
                strokeSize = 20f)

        val polygon = RegularPolygon(
                Vec2f(400, 200),
                6,
                100f
        )

        val triangle = Triangle(
                Vec2f(),
                Vec2f(120, 30),
                Vec2f(24, 333)
        )

        add(rect)
        add(ellipse3)
        add(polygon)
        add(triangle)
        add(CompositeShape("Balls", ellipse1, ellipse2))

        return@with this
    }
}
