package ru.iandreyshev.adobeKiller.domain.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.iandreyshev.adobeKiller.domain.extension.scaleToSize
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.ImageStyle

class CanvasImage(
        frame: Frame,
        val imageFile: IFile
) : CanvasObject(frame = frame, style = ImageStyle()) {

    companion object {
        private const val IMAGE_SIZE = 400
        private val CONFIG = Bitmap.Config.ARGB_8888
        private val OPTIONS: BitmapFactory.Options = BitmapFactory.Options().apply {
            inPreferredConfig = CONFIG
        }
    }

    var image: Bitmap? = null
        private set
    private var mIsOnScene: Boolean = false

    override fun onAddedToScene() {
        mIsOnScene = true
        loadImage()
    }

    override fun onRemovedFromScene() {
        image = null
        mIsOnScene = false
    }

    override fun accept(visitor: ICanvasObjectVisitor) =
            visitor.visit(this)

    private fun loadImage() {
        val bytes = imageFile.bytes() ?: return
        image = BitmapFactory
                .decodeByteArray(bytes, 0, bytes.size, OPTIONS)
                .scaleToSize(IMAGE_SIZE)
    }

}
