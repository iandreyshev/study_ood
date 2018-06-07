package ru.iandreyshev.adobeKiller.interactor.interfaces

import ru.iandreyshev.canvas.core.CanvasData

interface IMenuActivityInteractor {

    fun createCanvas(name: String)
    fun openCanvas(canvasData: CanvasData)
    fun deleteCanvas(canvasData: CanvasData)

}
