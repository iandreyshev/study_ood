package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasData

interface IMenuPresenter : IPresenter {

    fun setCanvases(canvases: List<CanvasData>)

    fun openCanvas()

}
