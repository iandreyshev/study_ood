package ru.iandreyshev.adobeKiller.domain.model

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class ImageData(
        frame: IFrame,
        style: IStyle,
        model: ICanvasObjectModel,
        val image: Bitmap
) : CanvasObject(frame = frame, style = style, model = model) {

    override fun accept(serializer: ICanvasObjectVisitor) =
            serializer.visit(this)

}
