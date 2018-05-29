package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame

interface ICanvasPresenter : IPresenter {

    fun setTarget(canvasObject: CanvasObject?)
    fun insert(canvasObject: CanvasObject)
    fun invalidate()
    fun clear()

}
