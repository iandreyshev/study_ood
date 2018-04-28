package ru.iandreyshev.compositeshapespaint.interactor

import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.useCase.interfaces.IMainUseCase

class MainInteractor(
        private val mainUseCase: IMainUseCase
) : IMainInteractor {

    override fun refresh() =
            mainUseCase.refresh()

    override fun addShape(id: String) {
    }

    override fun updateShape(shape: IShape) =
            mainUseCase.updateShape(shape)

    override fun beginNormal() =
            mainUseCase.setState("normal")

    override fun beginResizing() =
            mainUseCase.setState("resizing")

    override fun beginMoving() =
            mainUseCase.setState("moving")

    override fun beginGrouping() =
            mainUseCase.setState("grouping")

    override fun showShapeInfo(shape: IShape) {}

    override fun deleteShape(shape: IShape) =
            mainUseCase.deleteShape(shape)

    override fun setTargetShape(shape: IShape) =
            mainUseCase.setTargetShape(shape)

    override fun resizeStroke(shape: IShape, size: Int) =
            mainUseCase.resizeStroke(shape, size)

    override fun changeFillColor(shape: IShape, color: Color) =
            mainUseCase.changeFillColor(shape, color)

    override fun changeStrokeColor(shape: IShape, color: Color) =
            mainUseCase.changeStrokeColor(shape, color)
}
