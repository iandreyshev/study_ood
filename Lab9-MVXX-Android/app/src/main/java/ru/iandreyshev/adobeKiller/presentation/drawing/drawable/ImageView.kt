package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.ICanvas
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.bottom
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.left
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.right
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.top
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.ImageStyle

class ImageView(
        private val image: Bitmap,
        override val frame: Frame
) : DrawableView(style = ImageStyle()) {

    // onDraw
    private val mPosition = Vec2f()
    // onDraw

    override fun onDrawShape(canvas: ICanvas) {
        mPosition.x = frame.x
        mPosition.y = frame.y
        canvas.drawImage(image, mPosition, frame.width, frame.height)
    }

    override fun onDrawStroke(canvas: ICanvas) = Unit

    override fun hitTest(x: Float, y: Float): Boolean {
        return x >= frame.left && x <= frame.right
                && y >= frame.top && y <= frame.bottom
    }

}
