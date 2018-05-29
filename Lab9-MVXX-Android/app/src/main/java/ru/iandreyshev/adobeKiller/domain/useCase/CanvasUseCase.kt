package ru.iandreyshev.adobeKiller.domain.useCase

import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.domain.presentationModel.IModelSerializer
import ru.iandreyshev.adobeKiller.domain.presentationModel.IPresentationModel

// TODO: Ask about dependency injections count

class CanvasUseCase(
        private var presenter: ICanvasPresenter,
        private val commandQueue: ICommandQueue,
        private val presentationModel: IPresentationModel, // TODO: Разделить доменную модель и Application - модель
        private val localStorage: IModelSerializer // TODO: Вынести логику создания модели из localStorage
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
        mTarget = canvasObject
        presenter.setTarget(mTarget)
    }

    override fun delete(canvasObject: CanvasObject) {
        presentationModel.delete(canvasObject)
        mTarget = null
        presenter.setTarget(mTarget)
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
        presentationModel.serialize(localStorage)
    }

}
