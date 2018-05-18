package ru.iandreyshev.adobeKiller.presentation.interactor.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData

interface IMenuInteractor : IInteractor {

    fun createCanvas(name: String)

    fun openCanvas(canvasData: CanvasData)

}
