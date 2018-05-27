package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

abstract class CanvasObject(
        val frame: IFrame,
        val style: IStyle,
        private val model: ICanvasObjectModel
) {

    fun notifyDataChanges() {
        model.notifyDataChanges(this, frame)
        model.notifyDataChanges(this, style)
    }

    abstract fun accept(serializer: ICanvasObjectVisitor)

}
