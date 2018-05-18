package ru.iandreyshev.adobeKiller.presentation.drawing.factory

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.*
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

object ConcreteShapeFactory : IDrawableFactory {

    private val mInitFrame = Frame(
            position = Vec2f(),
            width = 100f,
            height = 100f
    )

    override fun create(shapeName: String): IDrawable? = when (shapeName) {

        "rect" -> DrawableRect(
                frame = mInitFrame,
                style = Style()
        )

        "circle" -> DrawableEllipse(
                frame = mInitFrame,
                style = Style()
        )

        "triangle" -> DrawableTriangle(
                frame = mInitFrame,
                style = Style()
        )

        else -> null

    }

}
