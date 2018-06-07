package ru.iandreyshev.canvas.core

import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame

class CanvasShape(
        frame: Frame,
        style: Style,
        val type: ShapeType
) : CanvasObject(frame = frame, style = style) {

    override fun accept(visitor: ICanvasObjectVisitor) =
            visitor.visit(this)

}
