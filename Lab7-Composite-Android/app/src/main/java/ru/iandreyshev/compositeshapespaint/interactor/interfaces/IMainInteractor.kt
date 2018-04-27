package ru.iandreyshev.compositeshapespaint.interactor.interfaces

import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor

interface IMainInteractor : IInteractor {
    fun refresh()

    fun resize(size: Int)

    fun move(args: String)

    fun resizeStroke(size: Int)

    fun changeFillColor(color: Color)

    fun changeStrokeColor(color: Color)

    fun selectShape(shape: IShape)
}
