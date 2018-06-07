package ru.iandreyshev.adobeKiller.interactor

import ru.iandreyshev.adobeKiller.interactor.interfaces.ICanvasActivityInteractor
import ru.iandreyshev.canvas.core.ShapeType
import ru.iandreyshev.adobeKiller.model.ApplicationModel

class CanvasActivityInteractor(
        private val canvas: ApplicationModel
) : ICanvasActivityInteractor {

    override fun insert(shape: ShapeType) {
        canvas.insert(shape)
    }

    override fun insertPhoto() {
        TODO()
    }

    override fun resetTarget() {
    }

    override fun undo() {
        canvas.undo()
    }

    override fun redo() {
        canvas.redo()
    }

    override fun refresh() {
        canvas.refresh()
    }

    override fun save() {
        canvas.save()
    }

}
