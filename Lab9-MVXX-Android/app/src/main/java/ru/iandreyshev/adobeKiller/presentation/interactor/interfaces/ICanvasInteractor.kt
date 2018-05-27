package ru.iandreyshev.adobeKiller.presentation.interactor.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import java.io.File

interface ICanvasInteractor : IInteractor {

    fun insert(shapeType: ShapeType)
    fun insert(image: File)

    fun setTarget(canvasObject: CanvasObject?)
    fun delete(canvasObject: CanvasObject)

    fun undo()
    fun redo()
    fun refresh()
    fun save()

}
