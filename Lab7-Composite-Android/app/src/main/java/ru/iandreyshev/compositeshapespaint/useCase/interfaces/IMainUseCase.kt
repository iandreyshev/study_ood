package ru.iandreyshev.compositeshapespaint.useCase.interfaces

import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.gumballmachine.useCase.interfaces.IUseCase

interface IMainUseCase : IUseCase {
    fun refresh()

    fun resize(args: String)

    fun move(args: String)

    fun resizeStroke(args: String)

    fun changeFillColor(args: String)

    fun changeStrokeColor(args: String)

    fun selectShape(shape: IShape)
}
