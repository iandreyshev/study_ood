package ru.iandreyshev.adobekiller.ui.shape

import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.shape.*
import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.shape.style.Style

object ImageGenerator {
    fun create(): MutableList<IShape> = with(mutableListOf<IShape>()) {

        val groundMaxY = 900

        val ground = Rectangle(
                leftTop = Vec2f(0, groundMaxY),
                rightBottom = Vec2f(2000, 2000),
                name = "Ground",
                style = Style(
                        fillColor = Color.GREEN,
                        strokeColor = Color.NONE,
                        strokeSize = 0f))

        val sun = Ellipse(
                center = Vec2f(0, 0),
                horizontalRadius = 200f,
                verticalRadius = 200f,
                name = "Sun",
                style = Style(
                        fillColor = Color.YELLOW,
                        strokeColor = Color.NONE,
                        strokeSize = 1f))

        val cloud1 = Ellipse(
                center = Vec2f(100, 200),
                horizontalRadius = 200f,
                verticalRadius = 70f,
                name = "Left cloud",
                style = Style(
                        fillColor = Color.WHITE,
                        strokeColor = Color.BLUE,
                        strokeSize = 1f))

        val cloud2 = Ellipse(
                center = Vec2f(600, 350),
                horizontalRadius = 250f,
                verticalRadius = 120f,
                name = "Right cloud",
                style = Style(
                        fillColor = Color.WHITE,
                        strokeColor = Color.BLUE,
                        strokeSize = 1f))

        val homeBox = Rectangle(
                leftTop = Vec2f(100, 600),
                rightBottom = Vec2f(600, groundMaxY),
                style = Style(
                        fillColor = Color.RED,
                        strokeColor = Color.BLACK,
                        strokeSize = 5f))

        val homeDoor = Rectangle(
                leftTop = Vec2f(200, groundMaxY - 250),
                rightBottom = Vec2f(350, groundMaxY),
                style = Style(
                        fillColor = Color.WHITE,
                        strokeColor = Color.BLACK,
                        strokeSize = 5f))

        val homeDoorGrip = Ellipse(
                center = Vec2f(320, groundMaxY - 100),
                horizontalRadius = 10f,
                verticalRadius = 10f,
                style = Style(
                        fillColor = Color.BLACK,
                        strokeSize = 0f))

        val homeWindow = Rectangle(
                leftTop = Vec2f(400, groundMaxY - 250),
                rightBottom = Vec2f(550, groundMaxY - 50),
                style = Style(
                        fillColor = Color.BLUE,
                        strokeColor = Color.WHITE,
                        strokeSize = 2f))

        val homeRoof = Triangle(
                vertex1 = Vec2f(50, 600),
                vertex2 = Vec2f(350, 300),
                vertex3 = Vec2f(650, 600),
                style = Style(
                        fillColor = Color.YELLOW,
                        strokeColor = Color.BLACK,
                        strokeSize = 6f))

        val flower1 = RegularPolygon(
                center = Vec2f(100, 1000),
                vertexCount = 5,
                radius = 42f,
                style = Style(
                        fillColor = Color.WHITE,
                        strokeColor = Color.RED,
                        strokeSize = 10f))

        val flower2 = RegularPolygon(
                center = Vec2f(300, 980),
                vertexCount = 5,
                radius = 42f,
                style = Style(
                        fillColor = Color.YELLOW,
                        strokeColor = Color.RED,
                        strokeSize = 5f))

        val flower3 = RegularPolygon(
                center = Vec2f(550, 1020),
                vertexCount = 5,
                radius = 42f,
                style = Style(
                        fillColor = Color.RED,
                        strokeColor = Color.YELLOW,
                        strokeSize = 8f))

        add(sun)
        add(cloud1)
        add(cloud2)
        add(CompositeShape("Ground", ground, flower1, flower2, flower3))
        add(CompositeShape("Home", homeBox, homeDoor, homeDoorGrip, homeWindow, homeRoof))

        return@with this
    }
}
