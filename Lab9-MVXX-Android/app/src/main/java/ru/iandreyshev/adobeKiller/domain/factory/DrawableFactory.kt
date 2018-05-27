package ru.iandreyshev.adobeKiller.domain.factory

import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.DrawableEllipse
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.DrawableRect
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.DrawableTriangle
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

object DrawableFactory {

    fun create(shapeType: ShapeType, frame: IFrame = Frame(), style: IStyle = Style()): IDrawable =
            when (shapeType) {
                ShapeType.Rect -> DrawableRect(
                        frame = frame,
                        style = style
                )
                ShapeType.Ellipse -> DrawableEllipse(
                        frame = frame,
                        style = style
                )
                ShapeType.Triangle -> DrawableTriangle(
                        frame = frame,
                        style = style
                )
            }

}
