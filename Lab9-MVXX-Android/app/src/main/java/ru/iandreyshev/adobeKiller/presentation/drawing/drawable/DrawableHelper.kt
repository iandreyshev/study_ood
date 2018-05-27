package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import ru.iandreyshev.adobeKiller.domain.model.ICanvasObjectVisitor
import ru.iandreyshev.adobeKiller.domain.model.ImageData
import ru.iandreyshev.adobeKiller.domain.model.ShapeData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas

class DrawableHelper(
        private val canvas: ICanvas
) : ICanvasObjectVisitor {

    override fun visit(shape: ShapeData) {
        val drawable = when (shape.type) {
            ShapeType.Rect -> DrawableRect(shape.frame, shape.style)
            ShapeType.Ellipse -> DrawableEllipse(shape.frame, shape.style)
            ShapeType.Triangle -> DrawableTriangle(shape.frame, shape.style)
        }

        drawable.draw(canvas)
    }

    override fun visit(image: ImageData) {
        val drawable = DrawableImage(
                width = image.frame.width,
                height = image.frame.height,
                image = image.image
        )

        drawable.draw(canvas)
    }

}
