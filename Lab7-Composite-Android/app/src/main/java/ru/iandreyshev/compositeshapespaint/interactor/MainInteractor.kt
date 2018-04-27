package ru.iandreyshev.compositeshapespaint.interactor

import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase

class MainInteractor(
        private val useCase: IMainUseCase
) : IMainInteractor {

    override fun selectShape(shape: IShape) =
            useCase.selectShape(shape)

    override fun refresh() =
            useCase.refresh()

    override fun resize(args: String) {}

    override fun move(args: String) {}

    override fun resizeStroke(args: String) {}

    override fun changeFillColor(args: String) {}

    override fun changeStrokeColor(args: String) {}
}
