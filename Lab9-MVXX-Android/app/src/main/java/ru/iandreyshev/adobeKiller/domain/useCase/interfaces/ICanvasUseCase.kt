package ru.iandreyshev.adobeKiller.domain.useCase.interfaces

import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import java.io.File

interface ICanvasUseCase : IUseCase {

    fun addShape(shapeName: String)

    fun addShape(photo: File)

    fun updateShape(shape: IDrawable)

    fun resizeStroke(shape: IDrawable, size: Int)

    fun changeFillColor(shape: IDrawable, color: Color)

    fun changeStrokeColor(shape: IDrawable, color: Color)

    fun setTargetShape(shape: IDrawable?)

    fun deleteShape(shape: IDrawable)

    fun refresh()

}
