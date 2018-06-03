package ru.iandreyshev.adobeKiller.domain.canvasEngine

import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.Style

class CanvasShape(
        frame: Frame,
        style: Style,
        val type: ShapeType
) : CanvasObject(frame = frame, style = style) {

    override fun accept(visitor: ICanvasObjectVisitor) =
            visitor.visit(this)

}
