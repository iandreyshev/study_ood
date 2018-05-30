package ru.iandreyshev.adobeKiller.domain.controller

import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasObject
import ru.iandreyshev.adobeKiller.domain.canvasEngine.ShapeType
import ru.iandreyshev.adobeKiller.domain.canvasEngine.ICanvasSerializer
import ru.iandreyshev.adobeKiller.domain.canvasEngine.ICanvasEngine
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.ICanvasViewController
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.ICanvasPresenter
import java.io.File

class CanvasViewController(
        private var presenter: ICanvasPresenter,
        private val commandQueue: ICommandQueue,
        private val canvasEngine: ICanvasEngine,
        private val canvasSerializer: ICanvasSerializer
) : ICanvasViewController {

    init {
        canvasEngine.observeChanges { objects ->
            presenter.clear()
            objects.forEach { presenter.insert(it) }
            presenter.invalidate()
        }
    }

    private var mTarget: CanvasObject? = null

    override fun insert(shape: ShapeType) {
        canvasEngine.insert(shape)
    }

    override fun insert(image: File) {
        canvasEngine.insert(FileWrapper(image))
    }

    override fun setTarget(canvasObject: CanvasObject?) {
        presenter.setTarget(canvasObject)
    }

    override fun delete() {
        mTarget?.let { canvasEngine.delete(it) }
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
        canvasEngine.clear()
    }

    override fun save() {
        canvasEngine.serialize(canvasSerializer)
    }

}
