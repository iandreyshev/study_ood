package ru.iandreyshev.adobeKiller.domain.useCase

import android.graphics.Bitmap
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File
import android.graphics.BitmapFactory
import android.util.Log
import ru.iandreyshev.adobeKiller.domain.extension.scaleToSize
import ru.iandreyshev.adobeKiller.domain.extension.toModel
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.domain.presentationModel.IPresentationModel
import ru.iandreyshev.adobeKiller.domain.serialize.LocalStorageSerializer
import ru.iandreyshev.localstorage.ILocalStorage

// TODO: Ask about dependency injections count

class CanvasUseCase(
        private var presenter: ICanvasPresenter,
        private val presentationModel: IPresentationModel,
        private val localStorage: ILocalStorage,
        private val canvas: CanvasData
) : ICanvasUseCase {

    init {
        Log.e("Canvas use case", "Open canvas: $canvas")

        presenter.setCanvasName(canvas.name)

        presentationModel.observe(::redraw)

        localStorage.getImages(canvas.id).forEach {
            Log.e("Canvas use case", "Load image: $it")
            presentationModel.fill(it.toModel())
        }

        localStorage.getShapes(canvas.id).forEach {
            Log.e("Canvas use case", "Load shape: $it")
            presentationModel.fill(it.toModel())
        }
    }

    override fun insert(shape: ShapeType) {
        presentationModel.insert(shape)
    }

    override fun insert(image: File) {
        try {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            val bitmap = BitmapFactory.decodeFile(image.path, options)
                    .scaleToSize(250)
            presentationModel.insert(bitmap)
        } catch (ex: Exception) {
            // TODO: Notify UI about error
            Log.e("CanvasInteractor", Log.getStackTraceString(ex))
        }
    }

    override fun resize(id: Long, width: Float, height: Float) =
            presentationModel.resize(id, width, height)

    override fun move(id: Long, x: Float, y: Float) =
            presentationModel.move(id, x, y)

    override fun resizeStroke(id: Long, size: Int) =
            presentationModel.resizeStroke(id, size)

    override fun changeFillColor(id: Long, color: Color) =
            presentationModel.changeFillColor(id, color)

    override fun changeStrokeColor(id: Long, color: Color) =
            presentationModel.changeStrokeColor(id, color)

    override fun deleteShape(id: Long) =
            presentationModel.delete(id)

    override fun undo() =
            presentationModel.undo()

    override fun redo() =
            presentationModel.redo()

    override fun refresh() =
            presentationModel.reset()

    override fun setTargetShape(id: Long?) {
        if (id == null) {
            presenter.setTarget(null)
            return
        }

        presenter.setTarget(id)
    }

    override fun save() {
        val serializer = LocalStorageSerializer()
        presentationModel.data.forEach {
            it.serialize(serializer)

            localStorage.saveShapes(canvas.id, serializer.shapes)
            localStorage.saveImages(canvas.id, serializer.images)
        }
    }

    private fun redraw() {
        presenter.clear()
        presentationModel.data.forEach {
            presenter.insert(it.toDrawable())
        }
    }

}
