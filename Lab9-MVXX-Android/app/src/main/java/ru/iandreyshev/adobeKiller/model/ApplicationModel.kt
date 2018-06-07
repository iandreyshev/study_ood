package ru.iandreyshev.adobeKiller.model

import ru.iandreyshev.canvas.core.ICanvas
import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.canvas.file.FileWrapper
import ru.iandreyshev.command.ICommandQueue
import java.io.File

class ApplicationModel(
        private val commandQueue: ICommandQueue,
        private val canvas: ICanvas
) : ICanvasAppModel {

    override fun setPresenter(presenter: ICanvasAppModel.IPresenter) {
        canvas.setPresenter(presenter.canvasPresenter)
    }

    override fun insert(shape: ShapeType) {
        canvas.insert(shape)
    }

    override fun insert(image: File) {
        canvas.insert(FileWrapper(image))
    }

    override fun undo() {
        if (!commandQueue.canUndo) {
            return
        }

        commandQueue.undo()
    }

    override fun redo() {
        if (!commandQueue.canRedo) {
            return
        }

        commandQueue.redo()
    }

    override fun refresh() {
        commandQueue.clear()
        canvas.clear()
    }

    override fun save() {
        canvas.save()
    }

}
