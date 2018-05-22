package ru.iandreyshev.adobeKiller.presentation.interactor.interfaces

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import java.io.File

interface ICanvasInteractor : IInteractor {

    fun save()

    fun refresh()

    fun addShape(id: String)

    fun addShape(photo: File)

    fun updateShape(shape: IDrawable)

    fun resizeStroke(shape: IDrawable, size: Int)

    fun changeFillColor(shape: IDrawable, color: Color)

    fun changeStrokeColor(shape: IDrawable, color: Color)

    fun setTargetShape(shape: IDrawable?)

    fun deleteShape(shape: IDrawable)

}
