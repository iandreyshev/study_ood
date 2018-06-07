package ru.iandreyshev.adobeKiller.interactor

import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.adobeKiller.model.CanvasApplicationModel
import java.io.File

class CanvasActivityInteractor(
        val canvasAppModel: CanvasApplicationModel
) : ICanvasInteractor {

    override fun insert(shape: ShapeType) {
        canvasAppModel.insert(shape)
    }

    override fun insertPhoto(file: File) {
        canvasAppModel.insert(file)
    }

    override fun resetTarget() {
        canvasAppModel.resetTarget()
    }

    override fun undo() {
        canvasAppModel.undo()
    }

    override fun redo() {
        canvasAppModel.redo()
    }

    override fun refresh() {
        canvasAppModel.refresh()
    }

    override fun save() {
        canvasAppModel.save()
    }

}
