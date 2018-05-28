package ru.iandreyshev.adobeKiller.presentation.interactor

import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IMenuUseCase
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IMenuInteractor
import ru.iandreyshev.adobeKiller.domain.model.CanvasData

class MenuInteractor(private val useCase: IMenuUseCase) : IMenuInteractor {

    override fun createCanvas(name: String) =
            useCase.createCanvas(name)

    override fun openCanvas(canvasData: CanvasData) =
            useCase.openCanvas(canvasData)

    override fun deleteCanvas(canvasData: CanvasData) =
            useCase.deleteCanvas(canvasData)

}
