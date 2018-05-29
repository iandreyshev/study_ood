package ru.iandreyshev.adobeKiller.domain.useCase.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import java.io.File

interface ICanvasUseCase : IUseCase {

    fun insert(shape: ShapeType)
    fun insert(image: File)
    fun delete()

    fun setTarget(canvasObject: CanvasObject?)

    fun undo()
    fun redo()
    fun refresh()
    fun save()

}
