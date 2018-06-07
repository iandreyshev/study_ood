package ru.iandreyshev.adobeKiller.model

import ru.iandreyshev.canvas.core.ICanvas
import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.canvas.file.FileWrapper
import ru.iandreyshev.command.ICommandQueue
import java.io.File

class CanvasApplicationModel(
        private val commandQueue: ICommandQueue,
        val canvas: ICanvas
) : ICanvasAppModel {

    private var mPresenter: ICanvasAppModel.IPresenter? = null

    override fun setPresenter(presenter: ICanvasAppModel.IPresenter) {
        mPresenter = presenter
    }

    override fun insert(shape: ShapeType) {
        canvas.insert(shape)
        mPresenter?.resetTarget()
    }

    override fun insert(image: File) {
        canvas.insert(FileWrapper(image))
        mPresenter?.resetTarget()
    }

    override fun undo() {
        if (!commandQueue.canUndo) {
            return
        }

        commandQueue.undo()
        canvas.update()
        mPresenter?.resetTarget()
    }

    override fun redo() {
        if (!commandQueue.canRedo) {
            return
        }

        commandQueue.redo()
        canvas.update()
        mPresenter?.resetTarget()
    }

    override fun refresh() {
        commandQueue.clear()
        canvas.clear()
        mPresenter?.resetTarget()
    }

    override fun save() {
        canvas.save()
        mPresenter?.resetTarget()
    }

}
