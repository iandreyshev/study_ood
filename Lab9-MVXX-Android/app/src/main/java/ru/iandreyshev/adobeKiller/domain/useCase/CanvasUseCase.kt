package ru.iandreyshev.adobeKiller.domain.useCase

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
//        try {
//            val options = BitmapFactory.Options()
//            options.inPreferredConfig = Bitmap.Config.ARGB_8888
//            val bitmap = BitmapFactory.decodeFile(image.path, options)
//                    .scaleToSize(250)
//            presentationModel.insert(bitmap)
//        } catch (ex: Exception) {
//            // TODO: Notify UI about error
//            Log.e("CanvasInteractor", Log.getStackTraceString(ex))
//        }
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
        presentationModel.data.forEach {
            it.accept(serializer)

            localStorage.saveShapes(canvas.id, serializer.shapes)
            localStorage.saveImages(canvas.id, serializer.images)
        }
    }

    private fun reDraw() {
        presenter.clear()
        presentationModel.data.forEach {
            presenter.insert(it)
        }
        presenter.setTarget(mTarget)
    }

}
