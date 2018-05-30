package ru.iandreyshev.adobeKiller.domain.controller.interfaces

import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasData

interface IMenuViewController : IViewController {

    fun createCanvas(name: String)

    fun openCanvas(canvasData: CanvasData)

    fun deleteCanvas(canvasData: CanvasData)

}
