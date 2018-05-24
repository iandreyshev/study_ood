package ru.iandreyshev.adobeKiller.presentation.interactor

import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File

class CanvasInteractor(
        private val useCase: ICanvasUseCase
) : ICanvasInteractor {

    override fun insert(shapeType: ShapeType) =
            useCase.insert(shapeType)

    override fun insert(image: File) =
            useCase.insert(image)

    override fun resize(id: Long, width: Float, height: Float) =
            useCase.resize(id, width, height)

    override fun move(id: Long, x: Float, y: Float) =
            useCase.move(id, x, y)

    override fun resizeStroke(id: Long, size: Int) =
            useCase.resizeStroke(id, size)

    override fun changeFillColor(id: Long, color: Color) =
            useCase.changeFillColor(id, color)

    override fun changeStrokeColor(id: Long, color: Color) =
            useCase.changeStrokeColor(id, color)

    override fun setTargetShape(id: Long?) =
            useCase.setTargetShape(id)

    override fun deleteShape(id: Long) =
            useCase.deleteShape(id)

    override fun undo() =
            useCase.undo()

    override fun redo() =
            useCase.redo()

    override fun refresh() =
            useCase.refresh()

    override fun save() =
            useCase.save()

}
