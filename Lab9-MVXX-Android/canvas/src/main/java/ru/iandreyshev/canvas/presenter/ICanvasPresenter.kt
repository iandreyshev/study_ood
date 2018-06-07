package ru.iandreyshev.canvas.presenter

import ru.iandreyshev.canvas.core.CanvasObject

interface ICanvasPresenter {

    fun update()
    fun update(objects: List<CanvasObject>)

}
