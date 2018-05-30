package ru.iandreyshev.adobeKiller.domain.canvasEngine

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class CanvasObject(
        val frame: Frame,
        val style: IStyle
) {

    private var mOnChangeAction: ((frame: Frame, style: IStyle) -> Unit)? = null

    fun update(frame: Frame, style: IStyle) {
        mOnChangeAction?.invoke(frame, style)
    }

    fun subscribeOnChange(action: ((frame: Frame, style: IStyle) -> Unit)?) {
        mOnChangeAction = action
    }

    abstract fun accept(visitor: ICanvasObjectVisitor)

    open fun onAddedToScene() = Unit
    open fun onRemovedFromScene() = Unit

}
