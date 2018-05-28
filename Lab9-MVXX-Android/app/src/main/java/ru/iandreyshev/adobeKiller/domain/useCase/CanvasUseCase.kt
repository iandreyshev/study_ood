package ru.iandreyshev.adobeKiller.domain.useCase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.iandreyshev.adobeKiller.domain.extension.scaleToSize
import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
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
        presenter.setTitle(canvas.name)

        presentationModel.observeChanges(::reDraw)
        localStorage.getImages(canvas.id).forEach { presentationModel.fill(it) }
        localStorage.getShapes(canvas.id).forEach { presentationModel.fill(it) }
        reDraw()
    }

    private var mTarget: CanvasObject? = null

    override fun insert(shape: ShapeType) {
        presentationModel.insert(shape)
    }

    override fun insert(image: File) {
        presentationModel.insert(FileWrapper(image))
    }

    override fun setTarget(canvasObject: CanvasObject?) {
        mTarget = canvasObject
        presenter.setTarget(mTarget)
    }

    override fun delete(canvasObject: CanvasObject) {
        presentationModel.delete(canvasObject)
        mTarget = null
        presenter.setTarget(mTarget)
    }

    override fun undo() {
        presentationModel.undo()
        mTarget?.resetProperties()
    }

    override fun redo() {
        presentationModel.redo()
        mTarget?.resetProperties()
    }

    override fun refresh() =
            presentationModel.clear()

    override fun save() {
        val serializer = LocalStorageSerializer()
        presentationModel.sceneData.forEach {
            it.accept(serializer)

            localStorage.saveShapes(canvas.id, serializer.shapes)
            localStorage.saveImages(canvas.id, serializer.images)
        }
    }

    private fun reDraw() {
        presenter.clear()
        presentationModel.sceneData.forEach {
            presenter.insert(it)
        }
        presenter.setTarget(mTarget)
    }

}
