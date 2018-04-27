package ru.iandreyshev.compositeshapespaint.interactor.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.gumballmachine.interactor.interfaces.IInteractor

interface IMainInteractor : IInteractor {
    fun refresh()

    fun resize(args: String)

    fun move(args: String)

    fun resizeStroke(args: String)

    fun changeFillColor(args: String)

    fun changeStrokeColor(args: String)

    fun selectShape(shape: IShape)
}
