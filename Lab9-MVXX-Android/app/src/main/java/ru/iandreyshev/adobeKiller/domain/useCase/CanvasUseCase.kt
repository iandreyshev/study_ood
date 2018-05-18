package ru.iandreyshev.adobeKiller.domain.useCase

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawableFactory
import java.io.File
import android.graphics.BitmapFactory
import android.util.Log
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.DrawableImage

class CanvasUseCase(
        private var presenter: ICanvasPresenter,
        private val shapesFactory: IDrawableFactory,
        private val canvas: CanvasData
) : ICanvasUseCase {

    private lateinit var mShapes: MutableList<IDrawable>

    init {
        presenter.setCanvasData(canvas)
        refresh()
    }

    override fun addShape(shapeName: String) {
        shapesFactory.create(shapeName).let { shape ->
            if (shape == null) {
                return // TODO: Notify UI about invalid shape name
            }

            mShapes.add(shape)
            presenter.setTarget(shape)
            presenter.updateShapes(mShapes)
        }
    }

    override fun addShape(photo: File) {
        try {
            val options = BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.ARGB_8888
            }

            val bitmap = BitmapFactory.decodeFile(photo.path, options)
                    .scaleToSize(250)

            val shape = DrawableImage(
                    image = bitmap,
                    width = bitmap.width.toFloat(),
                    height = bitmap.height.toFloat())

            mShapes.add(shape)
            presenter.updateShapes(mShapes)

        } catch (ex: Exception) {
            // TODO: Notify UI about error
            Log.e("CanvasInteractor", Log.getStackTraceString(ex))
        }
    }

    override fun setTargetShape(shape: IDrawable?) {
        if (shape == null) {
            presenter.setTarget(null)
            return
        }

        if (!mShapes.contains(shape)) {
            return // TODO: Create notification about invalid target shape
        }

        presenter.setTarget(shape)
    }

    override fun updateShape(shape: IDrawable) {
        presenter.updateShapes(mShapes)
    }

    override fun resizeStroke(shape: IDrawable, size: Int) {
        shape.style.setStrokeSize(size.toFloat())
        updateShape(shape)
    }

    override fun changeFillColor(shape: IDrawable, color: Color) {
        shape.style.setFillColor(color)
        updateShape(shape)
    }

    override fun changeStrokeColor(shape: IDrawable, color: Color) {
        shape.style.setStrokeColor(color)
        updateShape(shape)
    }

    override fun deleteShape(shape: IDrawable) {
        mShapes.remove(shape)
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
    }

    override fun refresh() {
        mShapes = ArrayList(listOf())
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
    }

    fun Bitmap.scaleToSize(maxSize: Int): Bitmap {
        if (maxSize <= 0) {
            return this
        } else if (width <= maxSize && height <= maxSize) {
            return this
        }

        val currMaxSize = Math.max(width, height)
        val sizeFactor = maxSize.toFloat() / currMaxSize.toFloat()

        val newWidth = Math.max((width * sizeFactor).toInt(), 1)
        val newHeight = Math.max((height * sizeFactor).toInt(), 1)

        return Bitmap.createScaledBitmap(this, newWidth, newHeight, true)
    }
}
