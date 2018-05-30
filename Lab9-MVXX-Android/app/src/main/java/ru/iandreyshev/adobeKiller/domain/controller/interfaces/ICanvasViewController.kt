package ru.iandreyshev.adobeKiller.domain.controller.interfaces

import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasObject
import ru.iandreyshev.adobeKiller.domain.canvasEngine.ShapeType
import java.io.File

interface ICanvasViewController : IViewController {

    fun insert(shape: ShapeType)
    fun insert(image: File)
    fun delete()

    fun setTarget(canvasObject: CanvasObject?)

    fun undo()
    fun redo()
    fun refresh()
    fun save()

}
