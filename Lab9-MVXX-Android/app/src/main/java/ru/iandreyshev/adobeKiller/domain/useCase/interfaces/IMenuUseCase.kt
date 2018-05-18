package ru.iandreyshev.adobeKiller.domain.useCase.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData

interface IMenuUseCase : IUseCase {

    fun createCanvas(name: String)

    fun openCanvas(canvasData: CanvasData)

}
