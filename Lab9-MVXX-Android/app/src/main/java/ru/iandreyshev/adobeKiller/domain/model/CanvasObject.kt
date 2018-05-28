package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class CanvasObject(
        val frame: IFrame,
        val style: IStyle,
        private val model: ICanvasObjectModel
) {

    private var mPrevFrame: IFrame = frame.clone()
    private var mPrevStyle: IStyle = style.clone()

    abstract fun accept(visitor: ICanvasObjectVisitor)

    fun notifyDataChanges() {
        model.notifyDataChanges(this, mPrevFrame)
        model.notifyDataChanges(this, mPrevStyle)
        resetProperties()
    }

    fun resetProperties() {
        mPrevFrame = frame.clone()
        mPrevStyle = style.clone()
    }

}
