package ru.iandreyshev.adobeKiller.presentation.ui.viewModel.interfaces

import ru.iandreyshev.adobeKiller.domain.model.CanvasData

interface IMenuViewModel : IViewModel {

    fun open(canvasData: CanvasData)

    fun setCanvases(canvases: List<CanvasData>)

}
