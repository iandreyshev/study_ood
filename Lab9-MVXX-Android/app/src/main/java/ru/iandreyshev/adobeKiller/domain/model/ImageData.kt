package ru.iandreyshev.adobeKiller.domain.model

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.serialize.ISerializer
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.DrawableImage
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ImageData(
        frame: IFrame,
        style: IStyle,
        val image: Bitmap
) : CanvasObjectData(frame = frame, style = style) {

    override fun serialize(serializer: ISerializer) =
            serializer.serialize(this)

    override fun toDrawable(): IDrawable =
            DrawableImage(
                    image = Bitmap.createBitmap(image),
                    width = frame.width,
                    height = frame.height
            )

}
