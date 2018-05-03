package ru.iandreyshev.compositeshapespaint.model.shape

import android.graphics.Bitmap
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.frame.Frame
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.style.FixedStyle

class ImageShape(
        private val image: Bitmap,
        width: Float,
        height: Float,
        override val name: String = "Photo"
) : TermShape(FixedStyle()) {

    override val frame: IFrame = Frame(Vec2f(), width, height)

    override fun onDrawShape(canvas: ICanvas) {
        canvas.drawImage(image, frame.position, frame.width, frame.height)
    }

    override fun onDrawStroke(canvas: ICanvas) {
        // skip
    }
}
