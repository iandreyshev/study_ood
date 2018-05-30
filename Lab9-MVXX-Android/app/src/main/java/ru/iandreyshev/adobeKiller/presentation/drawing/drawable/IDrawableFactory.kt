package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.domain.canvasEngine.ShapeType

interface IDrawableFactory {

    fun create(shapeType: ShapeType): IDrawable

}
