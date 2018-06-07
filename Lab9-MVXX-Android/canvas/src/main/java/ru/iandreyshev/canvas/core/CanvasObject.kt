package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame

abstract class CanvasObject(
        val frame: Frame,
        val style: Style
) {

    private var mOnUpdateListener: ((frame: Frame, style: Style) -> Unit)? = null
    private var mOnSelectListener: (() -> Unit)? = null

    fun setOnDeleteListener(listener: (() -> Unit)?) {
        mOnSelectListener = listener
    }

    fun setOnUpdateListener(listener: ((frame: Frame, style: Style) -> Unit)?) {
        mOnUpdateListener = listener
    }

    fun update(frame: Frame, style: Style) {
        mOnUpdateListener?.invoke(frame, style)
    }

    fun delete() {
        mOnSelectListener?.invoke()
    }

    abstract fun accept(visitor: ICanvasObjectVisitor)

    open fun onAddedToScene() = Unit
    open fun onRemovedFromScene() = Unit

}
