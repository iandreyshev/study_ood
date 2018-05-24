package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.domain.factory.DrawableFactory
import ru.iandreyshev.adobeKiller.domain.serialize.ISerializer
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class ShapeData(
        val type: ShapeType,
        frame: IFrame,
        style: IStyle
) : CanvasObjectData(frame = frame, style = style) {

    override fun serialize(serializer: ISerializer) =
            serializer.serialize(this)

    override fun toDrawable(): IDrawable =
            DrawableFactory.create(type, Frame(frame), Style(style))

}
