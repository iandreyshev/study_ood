package ru.iandreyshev.adobekiller.useCase.interfaces

import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.shape.IShape
import java.io.File

interface IMainUseCase : IUseCase {
    fun addShape(shapeName: String)

    fun addShape(photo: File)

    fun setState(stateName: String)

    fun updateShape(shape: IShape)

    fun resizeStroke(shape: IShape, size: Int)

    fun changeFillColor(shape: IShape, color: Color)

    fun changeStrokeColor(shape: IShape, color: Color)

    fun setTargetShape(shape: IShape?)

    fun deleteShape(shape: IShape)

    fun refresh()
}
