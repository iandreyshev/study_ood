package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.domain.canvasEngine.CanvasObject

interface ICanvasPresenter : IPresenter {

    fun insert(canvasObject: CanvasObject)
    fun redraw()
    fun resetTarget()
    fun clear()

}
