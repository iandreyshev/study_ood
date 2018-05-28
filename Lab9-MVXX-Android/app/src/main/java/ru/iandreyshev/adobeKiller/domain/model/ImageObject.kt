package ru.iandreyshev.adobeKiller.domain.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.iandreyshev.adobeKiller.domain.extension.scaleToSize
import ru.iandreyshev.adobeKiller.domain.file.IFile
import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasObjectModel
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.ImageStyle

class ImageObject(
        frame: IFrame,
        model: ICanvasObjectModel,
        val imageFile: IFile
) : CanvasObject(frame = frame, style = ImageStyle(), model = model) {

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

    private fun loadImage() = doAsync {
        val tmpBitmap = Bitmap.createBitmap(IMAGE_SIZE, IMAGE_SIZE, CONFIG)

        uiThread {
            setImageBitmap(tmpBitmap)
        }

        val bytes = imageFile.bytes() ?: return@doAsync
        val imageBitmap = BitmapFactory
                .decodeByteArray(bytes, 0, bytes.size, OPTIONS)
                .scaleToSize(IMAGE_SIZE)

        uiThread {
            setImageBitmap(imageBitmap)
        }
    }

    private fun setImageBitmap(imageBitmap: Bitmap) {
        if (mIsOnScene) {
            image = imageBitmap
            notifyDataChanges()
        }
    }

}
