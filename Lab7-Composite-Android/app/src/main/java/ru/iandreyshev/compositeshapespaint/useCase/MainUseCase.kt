package ru.iandreyshev.compositeshapespaint.useCase

import android.graphics.Bitmap
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.presenter.interfaces.IMainPresenter
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase
import ru.iandreyshev.compositeshapespaint.model.shape.IShapeFactory
import java.io.File
import android.graphics.BitmapFactory
import android.util.Log
import ru.iandreyshev.compositeshapespaint.model.shape.ImageShape


class MainUseCase(
        private var presenter: IMainPresenter,
        private val shapesFactory: IShapeFactory,
        private val onRefresh: () -> List<IShape> = { listOf() }
) : IMainUseCase {

    private lateinit var mShapes: MutableList<IShape>

    init {
        refresh()
    }

    override fun addShape(shapeName: String) = processWrap {
        shapesFactory.create(shapeName).let { shape ->
            if (shape == null) {
                return@processWrap // TODO: Notify UI about invalid shape name
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

            val shape = ImageShape(
                    image = bitmap,
                    width = bitmap.width.toFloat(),
                    height = bitmap.height.toFloat())

            mShapes.add(shape)
            presenter.updateShapes(mShapes)

        } catch (ex: Exception) {
            // TODO: Notify UI about error
            Log.e("MainInteractor", Log.getStackTraceString(ex))
        }
    }

    override fun setState(stateName: String) =
            presenter.setState(stateName.trim().toLowerCase())

    override fun setTargetShape(shape: IShape?) {
        if (shape == null) {
            presenter.setTarget(null)
            return
        }

        if (!mShapes.contains(shape)) {
            return // TODO: Create notification about invalid target shape
        }

        presenter.setTarget(shape)
    }

    override fun updateShape(shape: IShape) {
        presenter.updateShapes(mShapes)
    }

    override fun resizeStroke(shape: IShape, size: Int) {
        shape.style.setStrokeSize(size.toFloat())
        updateShape(shape)
    }

    override fun changeFillColor(shape: IShape, color: Color) {
        shape.style.setFillColor(color)
        updateShape(shape)
    }

    override fun changeStrokeColor(shape: IShape, color: Color) {
        shape.style.setStrokeColor(color)
        updateShape(shape)
    }

    override fun deleteShape(shape: IShape) = processWrap {
        mShapes.remove(shape)
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
    }

    override fun refresh() = processWrap {
        mShapes = ArrayList(onRefresh())
        presenter.updateShapes(mShapes)
        presenter.setTarget(null)
        presenter.setState("normal")
    }

    private fun processWrap(process: () -> Unit) {
        presenter.startProcess()
        process.invoke()
        presenter.finishProcess()
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
