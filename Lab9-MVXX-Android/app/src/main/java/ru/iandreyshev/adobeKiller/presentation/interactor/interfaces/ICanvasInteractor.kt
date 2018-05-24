package ru.iandreyshev.adobeKiller.presentation.interactor.interfaces

import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import java.io.File

interface ICanvasInteractor : IInteractor {

    fun insert(shapeType: ShapeType)
    fun insert(image: File)

    fun resize(id: Long, width: Float, height: Float)
    fun move(id: Long, x: Float, y: Float)

    fun resizeStroke(id: Long, size: Int)
    fun changeFillColor(id: Long, color: Color)
    fun changeStrokeColor(id: Long, color: Color)

    fun setTargetShape(id: Long?)

    fun deleteShape(id: Long)

    fun undo()
    fun redo()
    fun refresh()
    fun save()

}
