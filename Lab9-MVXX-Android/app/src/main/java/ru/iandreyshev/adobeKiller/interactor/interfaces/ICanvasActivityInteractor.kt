package ru.iandreyshev.adobeKiller.interactor.interfaces

import ru.iandreyshev.canvas.core.ShapeType

interface ICanvasActivityInteractor {

    fun insert(shape: ShapeType)
    fun insertPhoto()

    fun resetTarget()

    fun undo()
    fun redo()
    fun save()
    fun refresh()

}
