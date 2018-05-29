package ru.iandreyshev.adobeKiller.domain.useCase

import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.domain.presentationModel.ICanvasSerializer
import ru.iandreyshev.adobeKiller.domain.presentationModel.IPresentationModel

class CanvasUseCase(
        private var presenter: ICanvasPresenter,
        private val commandQueue: ICommandQueue,
        private val presentationModel: IPresentationModel,
        private val canvasSerializer: ICanvasSerializer
) : ICanvasUseCase {

    init {
        presentationModel.observeChanges {
            presenter.invalidate()
        }
    }

    private var mTarget: CanvasObject? = null

    override fun insert(shape: ShapeType) {
        presentationModel.insert(shape)
    }

    override fun insert(image: File) {
        presentationModel.insert(FileWrapper(image))
    }

    override fun setTarget(canvasObject: CanvasObject?) {
        presenter.setTarget(canvasObject)
    }

    override fun delete() {
        mTarget?.let { presentationModel.delete(it) }
        presenter.setTarget(null)
    }

    override fun undo() {
        commandQueue.undo()
    }

    override fun redo() {
        commandQueue.redo()
    }

    override fun refresh() {
        commandQueue.clear()
        presentationModel.clear()
    }

    override fun save() {
        presentationModel.serialize(canvasSerializer)
    }

}
