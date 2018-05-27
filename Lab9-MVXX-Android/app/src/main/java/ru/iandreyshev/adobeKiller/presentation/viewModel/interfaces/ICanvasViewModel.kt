package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasObject

interface ICanvasViewModel : IViewModel {

    fun setCanvasName(canvasName: String)
    fun setTarget(canvasObject: CanvasObject?)
    fun insert(canvasObject: CanvasObject)
    fun clear()

}
