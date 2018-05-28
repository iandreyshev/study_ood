package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject

interface ICanvasPresenter : IPresenter {

    fun setTitle(canvasName: String)
    fun setTarget(canvasObject: CanvasObject?)
    fun insert(canvasObject: CanvasObject)
    fun clear()

}
