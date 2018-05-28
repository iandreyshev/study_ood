package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ShapeObject(
        frame: IFrame,
        style: IStyle,
        model: ICanvasObjectModel,
        val type: ShapeType
) : CanvasObject(frame = frame, style = style, model = model) {

    override fun accept(visitor: ICanvasObjectVisitor) =
            visitor.visit(this)

}
