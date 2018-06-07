package ru.iandreyshev.adobeKiller.interactor

import ru.iandreyshev.canvas.core.ShapeType
import java.io.File

interface ICanvasInteractor {

    fun insert(shape: ShapeType)
    fun insertPhoto(file: File)

    fun resetTarget()

    fun undo()
    fun redo()
    fun save()
    fun refresh()

}
