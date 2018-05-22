package ru.iandreyshev.adobeKiller.presentation.interactor

import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File

class CanvasInteractor(
        private val useCase: ICanvasUseCase
) : ICanvasInteractor {

    override fun save() =
            useCase.save()

    override fun refresh() =
            useCase.refresh()

    override fun addShape(id: String) =
            useCase.addShape(id)

    override fun addShape(photo: File) =
            useCase.addShape(photo)

    override fun updateShape(shape: IDrawable) =
            useCase.updateShape(shape)

    override fun deleteShape(shape: IDrawable) =
            useCase.deleteShape(shape)

    override fun setTargetShape(shape: IDrawable?) =
            useCase.setTargetShape(shape)

    override fun resizeStroke(shape: IDrawable, size: Int) =
            useCase.resizeStroke(shape, size)

    override fun changeFillColor(shape: IDrawable, color: Color) =
            useCase.changeFillColor(shape, color)

    override fun changeStrokeColor(shape: IDrawable, color: Color) =
            useCase.changeStrokeColor(shape, color)

}
