package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable

interface ICanvasViewModel : IViewModel {

    fun setCanvasName(canvasName: String)
    fun setTarget(id: Long?)
    fun insert(id: Long, drawable: IDrawable)
    fun clear()

}
