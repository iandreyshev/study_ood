package ru.iandreyshev.adobekiller.interactor

import ru.iandreyshev.adobekiller.interactor.interfaces.IMainInteractor
import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.model.shape.IShape
import ru.iandreyshev.adobekiller.useCase.interfaces.IMainUseCase
import java.io.File

class MainInteractor(
        private val mainUseCase: IMainUseCase
) : IMainInteractor {

    override fun refresh() =
            mainUseCase.refresh()

    override fun addShape(id: String) =
            mainUseCase.addShape(id)

    override fun addShape(photo: File) =
            mainUseCase.addShape(photo)

    override fun updateShape(shape: IShape) =
            mainUseCase.updateShape(shape)

    override fun beginNormal() =
            mainUseCase.setState("normal")

    override fun beginGrouping() =
            mainUseCase.setState("grouping")

    override fun deleteShape(shape: IShape) =
            mainUseCase.deleteShape(shape)

    override fun setTargetShape(shape: IShape?) =
            mainUseCase.setTargetShape(shape)

    override fun resizeStroke(shape: IShape, size: Int) =
            mainUseCase.resizeStroke(shape, size)

    override fun changeFillColor(shape: IShape, color: Color) =
            mainUseCase.changeFillColor(shape, color)

    override fun changeStrokeColor(shape: IShape, color: Color) =
            mainUseCase.changeStrokeColor(shape, color)
}
