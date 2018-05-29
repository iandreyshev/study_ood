package ru.iandreyshev.adobeKiller.presentation.interactor

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.ICanvasUseCase
import java.io.File

class CanvasInteractor(
        private val useCase: ICanvasUseCase
) : ICanvasInteractor {

    override fun insert(shapeType: ShapeType) =
            useCase.insert(shapeType)

    override fun insert(image: File) =
            useCase.insert(image)

    override fun setTarget(canvasObject: CanvasObject?) =
            useCase.setTarget(canvasObject)

    override fun delete() =
            useCase.delete()

    override fun undo() =
            useCase.undo()

    override fun redo() =
            useCase.redo()

    override fun refresh() =
            useCase.refresh()

    override fun save() =
            useCase.save()

}
