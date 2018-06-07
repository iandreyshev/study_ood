package ru.iandreyshev.canvas.presenter

import ru.iandreyshev.canvas.core.CanvasObject

interface ICanvasPresenter {

    fun insert(canvasObject: CanvasObject)
    fun redraw(newObjects: List<CanvasObject>)
    fun redraw()
    fun clear()

}
