package ru.iandreyshev.adobeKiller.presentation.presenter.interfaces

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable

interface ICanvasPresenter : IPresenter {

    fun setCanvasName(canvasName: String)
    fun setTarget(id: Long?)
    fun insert(drawable: IDrawable)
    fun clear()

}
