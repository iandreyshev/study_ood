package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class CanvasObject(
        val frame: Frame,
        val style: IStyle
) {

    abstract fun accept(visitor: ICanvasObjectVisitor)

    open fun onAddedToScene() = Unit
    open fun onRemovedFromScene() = Unit

}
