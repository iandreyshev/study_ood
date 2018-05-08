package ru.iandreyshev.adobekiller.model.shape

import android.graphics.Bitmap
import ru.iandreyshev.adobekiller.model.canvas.ICanvas
import ru.iandreyshev.adobekiller.model.container.Vec2f
import ru.iandreyshev.adobekiller.model.shape.frame.Frame
import ru.iandreyshev.adobekiller.model.shape.frame.IFrame
import ru.iandreyshev.adobekiller.model.shape.style.FixedStyle

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
