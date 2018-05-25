package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData

interface IMenuViewModel : IViewModel {

    fun openCanvas()

    fun setCanvases(canvases: List<CanvasData>)

}
