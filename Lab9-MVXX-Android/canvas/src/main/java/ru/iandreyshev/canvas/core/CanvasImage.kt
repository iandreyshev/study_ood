package ru.iandreyshev.canvas.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.iandreyshev.canvas.extension.scaleToSize
import ru.iandreyshev.canvas.file.IFile
import ru.iandreyshev.canvas.style.ImageStyle
import ru.iandreyshev.geometry.frame.Frame

class CanvasImage(
        frame: Frame,
        val imageFile: IFile
) : CanvasObject(frame, ImageStyle()) {

    companion object {
        private const val IMAGE_SIZE = 400
        private val CONFIG = Bitmap.Config.ARGB_8888
        private val OPTIONS: BitmapFactory.Options = BitmapFactory.Options().apply {
            inPreferredConfig = CONFIG
        }
    }

    init {
        loadImage()
    }

    var image: Bitmap? = null
        private set

    override fun accept(visitor: ICanvasObjectVisitor) =
            visitor.visit(this)

    private fun loadImage() {
        val bytes = imageFile.bytes()
        image = BitmapFactory
                .decodeByteArray(bytes, 0, bytes.size, OPTIONS)
                .scaleToSize(IMAGE_SIZE)
    }

}
