package ru.iandreyshev.compositeshapespaint.interactor.interfaces

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import java.io.File

interface IMainInteractor : IInteractor {
    fun refresh()

    fun addShape(id: String)

    fun addShape(photo: File)

    fun beginNormal()

    fun beginGrouping()

    fun updateShape(shape: IShape)

    fun resizeStroke(shape: IShape, size: Int)

    fun changeFillColor(shape: IShape, color: Color)

    fun changeStrokeColor(shape: IShape, color: Color)

    fun setTargetShape(shape: IShape?)

    fun deleteShape(shape: IShape)
}
