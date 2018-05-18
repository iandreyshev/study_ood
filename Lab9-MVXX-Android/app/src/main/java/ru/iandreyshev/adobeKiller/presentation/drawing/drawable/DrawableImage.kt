package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.FixedStyle

class DrawableImage(
        private val image: Bitmap,
        width: Float,
        height: Float
) : BaseDrawable(FixedStyle()) {

    override val frame: IFrame = Frame(Vec2f(), width, height)

    override fun onDrawShape(canvas: ICanvas) {
        canvas.drawImage(image, frame.position, frame.width, frame.height)
    }

    override fun onDrawStroke(canvas: ICanvas) {
        // skip
    }

}
