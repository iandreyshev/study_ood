package ru.iandreyshev.adobeKiller.presentation.drawing.drawable

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.ICanvas
import ru.iandreyshev.geometry.vector.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.bottom
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.left
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.right
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.top
import ru.iandreyshev.canvas.style.ImageStyle
import ru.iandreyshev.canvas.style.Style
import ru.iandreyshev.geometry.frame.Frame

class ImageView(
        private val image: Bitmap,
        override val frame: Frame
) : DrawableView() {

    override val style: Style = ImageStyle()

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
