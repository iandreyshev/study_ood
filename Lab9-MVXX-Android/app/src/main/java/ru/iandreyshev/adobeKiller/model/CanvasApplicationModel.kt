package ru.iandreyshev.adobeKiller.model

import ru.iandreyshev.adobeKiller.file.ImageFile
import ru.iandreyshev.canvas.core.ICanvas
import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.command.ICommandQueue
import java.io.File

class CanvasApplicationModel(
        private val commandQueue: ICommandQueue,
        val canvas: ICanvas
) : ICanvasAppModel {

    private var mPresenter: ICanvasAppModel.IPresenter? = null

    init {
        canvas.load()
    }

    override fun setPresenter(presenter: ICanvasAppModel.IPresenter) {
        mPresenter = presenter
    }

    override fun insert(shape: ShapeType) = menuAction {
        canvas.insert(shape)
    }

    override fun insert(image: File) = menuAction {
        canvas.insert(ImageFile(image))
    }

    override fun undo() = menuAction {
        if (!commandQueue.canUndo) {
            return@menuAction
        }

        commandQueue.undo()
        canvas.update()
    }

    override fun redo() = menuAction {
        if (!commandQueue.canRedo) {
            return@menuAction
        }

        commandQueue.redo()
        canvas.update()
    }

    override fun refresh() = menuAction {
        commandQueue.clear()
        canvas.clear()
    }

    override fun save() = menuAction {
        canvas.save()
    }

    private fun menuAction(action: () -> Unit) {
        action()
        mPresenter?.resetTarget()
    }

}
