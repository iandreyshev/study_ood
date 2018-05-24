package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.domain.serialize.ISerializer
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class CanvasObjectData(
        var id: Long = 0,
        val frame: IFrame,
        val style: IStyle
) {

    abstract fun serialize(serializer: ISerializer)
    abstract fun toDrawable(): IDrawable

}
