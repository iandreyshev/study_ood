package ru.iandreyshev.compositeshapespaint.useCase.interfaces

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IMainUseCase : IUseCase {
    fun setState(stateName: String)

    fun updateShape(shape: IShape)

    fun resizeStroke(shape: IShape, size: Int)

    fun changeFillColor(shape: IShape, color: Color)

    fun changeStrokeColor(shape: IShape, color: Color)

    fun setTargetShape(shape: IShape)

    fun deleteShape(shape: IShape)

    fun refresh()
}
