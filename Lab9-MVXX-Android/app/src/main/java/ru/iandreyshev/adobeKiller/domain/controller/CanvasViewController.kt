package ru.iandreyshev.adobeKiller.domain.controller

import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.file.FileWrapper
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
        canvasEngine.deserialize(canvasSerializer)
        canvasEngine.observeUpdate { objects ->
            objects?.apply {
                presenter.clear()
                forEach { presenter.insert(it) }
                presenter.resetTarget()
            }
            presenter.redraw()
        }
    }

    override fun insert(shape: ShapeType) {
        canvasEngine.insert(shape)
    }

    override fun insert(image: File) {
        canvasEngine.insert(FileWrapper(image))
    }

    override fun resetTarget() {
        presenter.resetTarget()
    }

    override fun undo() {
        if (!commandQueue.canUndo) {
            return
        }

        commandQueue.undo()
        presenter.resetTarget()
        presenter.redraw()
    }

    override fun redo() {
        if (!commandQueue.canRedo) {
            return
        }

        commandQueue.redo()
        presenter.resetTarget()
        presenter.redraw()
    }

    override fun refresh() {
        commandQueue.clear()
        canvasEngine.clear()
        presenter.resetTarget()
        presenter.redraw()
    }

    override fun save() {
        canvasEngine.serialize(canvasSerializer)
    }

}
